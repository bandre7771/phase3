package cs5530;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import sun.invoke.empty.Empty;
import sun.reflect.annotation.ExceptionProxy;
import sun.text.resources.et.FormatData_et_EE;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application {

	public String _currentUser;

	public List<List<String>> reservations = new ArrayList<>();
	public List<List<String>> visits = new ArrayList<>();

	public Application() {

	}

	public Application(String currentUser){
		_currentUser = currentUser;
	}

	public String mostUsefulFeedbacksForTh(String n, String hid, Statement stmt)
	{
		String sql="SELECT f.fid, f.hid, f.login, f.text, f.score, f.fbdate, AVG(r.rating) as usefulness\n" +
				"FROM Feedback f, Rates r\n" +
				"WHERE f.fid = r.fid\n" +
				"  AND hid = "+hid+"\n" +
				"GROUP BY f.fid\n" +
				"ORDER BY usefulness DESC LIMIT "+n;
		String output="<table>";
		output += "<tr> <th> fid </th> <th> hid </th> <th> login </th> <th> text </th> <th> score </th> <th> fbdate </th> <th> usefulness </th> </tr>";
		ResultSet rs=null;
//		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="<tr><td>"+rs.getString("fid")+"</td>"
						+ "<td>"+rs.getString("hid")+"</td>"
						+ "<td>"+rs.getString("login")+"</td>"
						+ "<td>"+rs.getString("text")+"</td>"
						+ "<td>"+rs.getString("score")+"</td>"
						+ "<td>"+rs.getString("fbdate")+"</td>"
						+ "<td>"+rs.getString("usefulness")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String mostPopularThsPerCategory(String m, Statement stmt)
	{
		String sql="SELECT a.category, a.hid, a.hname, a.address, a.login, a.phone_number, a.year_built, a.url, a.picture, a.visit_count\n" +
				"FROM    (SELECT th1.category, th1.hid, th1.hname, th1.address, th1.login, th1.phone_number, th1.year_built, th1.url, th1.picture, count(*) as visit_count\n" +
				"        FROM TH th1, Visit v1\n" +
				"        WHERE th1.hid = v1.hid\n" +
				"        GROUP BY th1.category, th1.hid\n" +
				"        ORDER BY category, visit_count DESC) AS a\n" +
				"WHERE (SELECT count(*)\n" +
				"        FROM (SELECT th1.category, th1.hid, th1.hname, th1.address, th1.login, th1.phone_number, th1.year_built, th1.url, th1.picture, count(*) as visit_count\n" +
				"                FROM TH th1, Visit v1\n" +
				"                WHERE th1.hid = v1.hid\n" +
				"                GROUP BY th1.category, th1.hid\n" +
				"                ORDER BY category, visit_count DESC) as b\n" +
				"        WHERE b.category=a.category and b.visit_count >= a.visit_count) <= "+m;
		String output="<table>";
		output += "<tr> <th> category </th> <th> hid </th> <th> hname </th> <th> address </th> <th> login </th> <th> phone number </th> <th> year built </th> <th> url </th> <th> picture </th> <th> visit count </th>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("category")+"</td>"
						+ "<td>"+rs.getString("hid")+"</td>"
						+ "<td>"+rs.getString("hname")+"</td>"
						+ "<td>"+rs.getString("address")+"</td>"
						+ "<td>"+rs.getString("login")+"</td>"
						+ "<td>"+rs.getString("phone_number")+"</td>"
						+ "<td>"+rs.getString("year_built")+"</td>"
						+ "<td>"+rs.getString("url")+"</td>"
						+ "<td>"+rs.getString("picture")+"</td>"
						+ "<td>"+rs.getString("visit_count")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String mostExpensiveThsPerCategory(String m, Statement stmt)
	{
		String sql="SELECT a.category, a.hid, a.hname, a.address, a.login, a.phone_number, a.year_built, a.url, a.picture, a.average_cost\n" +
				"FROM    (SELECT th.category, th.hid, th.hname, th.address, th.login, th.phone_number, th.year_built, th.url, th.picture, AVG(v.cost) as average_cost\n" +
				"        FROM TH th, Visit v\n" +
				"        WHERE th.hid = v.hid\n" +
				"        GROUP BY th.category, th.hid\n" +
				"        ORDER BY category, average_cost DESC) AS a\n" +
				"WHERE (SELECT count(*)\n" +
				"        FROM (SELECT th.category, th.hid, th.hname, th.address, th.login, th.phone_number, th.year_built, th.url, th.picture, AVG(v.cost) as average_cost\n" +
				"                FROM TH th, Visit v\n" +
				"                WHERE th.hid = v.hid\n" +
				"                GROUP BY th.category, th.hid\n" +
				"                ORDER BY category, average_cost DESC) as b\n" +
				"        WHERE b.category=a.category and b.average_cost >= a.average_cost) <= "+m;
		String output="<table>";
		output += "<tr> <th> category </th> <th> hid </th> <th> hname </th> <th> address </th> <th> login </th> <th> phone number </th> <th> year built </th> <th> url </th> <th> picture </th> <th> average cost </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("category")+"</td>"
						+ "<td>"+rs.getString("hid")+"</td>"
						+ "<td>"+rs.getString("hname")+"</td>"
						+ "<td>"+rs.getString("address")+"</td>"
						+ "<td>"+rs.getString("login")+"</td>"
						+ "<td>"+rs.getString("phone_number")+"</td>"
						+ "<td>"+rs.getString("year_built")+"</td>"
						+ "<td>"+rs.getString("url")+"</td>"
						+ "<td>"+rs.getString("picture")+"</td>"
						+ "<td>"+rs.getString("average_cost")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String mostHighlyRatedThsPerCategory(String m, Statement stmt)
	{
		String sql="SELECT a.category, a.hid, a.hname, a.address, a.login, a.phone_number, a.year_built, a.url, a.picture, a.average_score\n" +
				"FROM    (SELECT th.category, th.hid, th.hname, th.address, th.login, th.phone_number, th.year_built, th.url, th.picture, AVG(f.score) as average_score\n" +
				"                FROM TH th, Feedback f\n" +
				"                WHERE th.hid = f.hid\n" +
				"                GROUP BY th.category, th.hid\n" +
				"                ORDER BY category, average_score DESC) AS a\n" +
				"WHERE (SELECT count(*)\n" +
				"        FROM (SELECT th.category, th.hid, th.hname, th.address, th.login, th.phone_number, th.year_built, th.url, th.picture, AVG(f.score) as average_score\n" +
				"                FROM TH th, Feedback f\n" +
				"                WHERE th.hid = f.hid\n" +
				"                GROUP BY th.category, th.hid\n" +
				"                ORDER BY category, average_score DESC) as b\n" +
				"        WHERE b.category=a.category and b.average_score >= a.average_score) <= "+m;
		String output="<table>";
		output += "<tr> <th> category </th> <th> hid </th> <th> hname </th> <th> address </th> <th> login </th> <th> phone number </th> <th> year built </th> <th> url </th> <th> picture </th> <th> average score </th>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("category")+"</td>"
						+ "<td>"+rs.getString("hid")+"</td>"
						+ "<td>"+rs.getString("hname")+"</td>"
						+ "<td>"+rs.getString("address")+"</td>"
						+ "<td>"+rs.getString("login")+"</td>"
						+ "<td>"+rs.getString("phone_number")+"</td>"
						+ "<td>"+rs.getString("year_built")+"</td>"
						+ "<td>"+rs.getString("url")+"</td>"
						+ "<td>"+rs.getString("picture")+"</td>"
						+ "<td>"+rs.getString("average_score")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public static String browsingTH(String whereQuery, Boolean orderByPriceActive, Boolean orderByScoreActive, Boolean onlyTrustedIsActive, Statement stmt)
	{
		String semiColon = "";
		String whereActive = "";
		String orderPriceActive = orderByPriceActive ? "" : "#" ;
		String orderScoreActive = orderByScoreActive ? "" : "#" ;
		String onlyTrustedActive = onlyTrustedIsActive ? "" : "#";
		if(whereQuery.isEmpty())
		{
			whereActive = "#";
		}
		if(orderPriceActive.equals("#") && orderScoreActive.equals("#"))
		{
			semiColon = ";";
		}
		String sql="SELECT browsingTH1.hid, browsingTH1.hname, browsingTH1.category, browsingTH1.address, browsingTH1.login, AVG(browsingTH1.fbscore) AS average_fbscore, AVG(browsingTH1.price) average_price #START OF FULL OUTER JOINS\n" +
				"FROM (SELECT browsingTH.hid1 as hid, browsingTH.hname1 as hname, browsingTH.category1 as category, browsingTH.address1 as address, browsingTH.login1 as login, browsingTH.score1 fbscore, browsingTH.cost as price\n" +
				"FROM (SELECT * FROM ((SELECT * FROM ((SELECT th.hid as hid1, th.hname as hname1, th.category as category1, th.address address1, th.login as login1, th.phone_number as phone_number1, th.year_built as year_built1, th.url as url1, th.picture as picture1, f.fid as fid1, f.hid as hid2, f.text as text1, f.score as score1, f.fbdate as fbdate1 FROM TH th\n" +
				"LEFT JOIN Feedback f ON th.hid = f.hid)\n" +
				"UNION (SELECT th.hid as hid1, th.hname as hname1, th.category as category1, th.address address1, th.login as login1, th.phone_number as phone_number1, th.year_built as year_built1, th.url as url1, th.picture as picture1, f.fid as fid1, f.hid as hid2, f.text as text1, f.score as score1, f.fbdate as fbdate1 FROM TH th\n" +
				"RIGHT JOIN Feedback f ON th.hid = f.hid)) as tf LEFT JOIN Visit v ON tf.hid1 = v.hid)\n" +
				"UNION (SELECT * FROM ((SELECT th.hid as hid1, th.hname as hname1, th.category as category1, th.address address1, th.login as login1, th.phone_number as phone_number1, th.year_built as year_built1, th.url as url1, th.picture as picture1, f.fid as fid1, f.hid as hid2, f.text as text1, f.score as score1, f.fbdate as fbdate1 FROM TH th\n" +
				"LEFT JOIN Feedback f ON th.hid = f.hid)\n" +
				"UNION (SELECT th.hid as hid1, th.hname as hname1, th.category as category1, th.address address1, th.login as login1, th.phone_number as phone_number1, th.year_built as year_built1, th.url as url1, th.picture as picture1, f.fid as fid1, f.hid as hid2, f.text as text1, f.score as score1, f.fbdate as fbdate1 FROM TH th\n" +
				"RIGHT JOIN Feedback f ON th.hid = f.hid)) as tf RIGHT JOIN Visit v ON tf.hid1 = v.hid)) tfv\n" +
				"LEFT JOIN (SELECT login1 as loginA, login2 as loginB, is_trusted FROM Trust) as t ON tfv.login1 = t.loginB\n" +
				"UNION (SELECT * FROM ((SELECT * FROM ((SELECT th.hid as hid1, th.hname as hname1, th.category as category1, th.address address1, th.login as login1, th.phone_number as phone_number1, th.year_built as year_built1, th.url as url1, th.picture as picture1, f.fid as fid1, f.hid as hid2, f.text as text1, f.score as score1, f.fbdate as fbdate1 FROM TH th\n" +
				"LEFT JOIN Feedback f ON th.hid = f.hid)\n" +
				"UNION (SELECT th.hid as hid1, th.hname as hname1, th.category as category1, th.address address1, th.login as login1, th.phone_number as phone_number1, th.year_built as year_built1, th.url as url1, th.picture as picture1, f.fid as fid1, f.hid as hid2, f.text as text1, f.score as score1, f.fbdate as fbdate1 FROM TH th\n" +
				"RIGHT JOIN Feedback f ON th.hid = f.hid)) as tf LEFT JOIN Visit v ON tf.hid1 = v.hid)\n" +
				"UNION (SELECT * FROM ((SELECT th.hid as hid1, th.hname as hname1, th.category as category1, th.address address1, th.login as login1, th.phone_number as phone_number1, th.year_built as year_built1, th.url as url1, th.picture as picture1, f.fid as fid1, f.hid as hid2, f.text as text1, f.score as score1, f.fbdate as fbdate1 FROM TH th\n" +
				"LEFT JOIN Feedback f ON th.hid = f.hid)\n" +
				"UNION (SELECT th.hid as hid1, th.hname as hname1, th.category as category1, th.address address1, th.login as login1, th.phone_number as phone_number1, th.year_built as year_built1, th.url as url1, th.picture as picture1, f.fid as fid1, f.hid as hid2, f.text as text1, f.score as score1, f.fbdate as fbdate1 FROM TH th\n" +
				"RIGHT JOIN Feedback f ON th.hid = f.hid)) as tf RIGHT JOIN Visit v ON tf.hid1 = v.hid)) tfv\n" +
				"RIGHT JOIN (SELECT login1 as loginA, login2 as loginB, is_trusted FROM Trust) as t ON tfv.login1 = t.loginB)) as browsingTH\n" +
				"WHERE browsingTH.hid IS NOT NULL\n" +
				"      "+onlyTrustedActive+"AND browsingTH.is_trusted = 1 \n" +
				"     ) as browsingTH1 #END OF FULL OUTER JOINS \n" +
				whereActive+"WHERE "+whereQuery+"\n" +
				"GROUP BY browsingTH1.hid, browsingTH1.hname, browsingTH1.category, browsingTH1.address, browsingTH1.login"+semiColon+"\n" +
				orderPriceActive+"ORDER BY average_price DESC;\n" +
				orderScoreActive+"ORDER BY average_fbscore DESC;";
		String output="<table>";
		output += "<tr> <th> hid </th> <th> hname </th> <th> category </th> <th> address </th> <th> login </th> <th> average fbscore </th> <th> average price </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("hid")+"</td>"
						+ "<td>"+rs.getString("hname")+"</td>"
						+ "<td>"+rs.getString("category")+"</td>"
						+ "<td>"+rs.getString("address")+"</td>"
						+ "<td>"+rs.getString("login")+"</td>"
						+ "<td>" +rs.getString("average_fbscore")+"</td>"
						+ "<td>" +rs.getString("average_price")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String getAllHasKeyWordDescription(Statement stmt)
	{
		String sql="SELECT hk.hid, th.hname, hk.wid, k.word FROM Keywords k NATURAL JOIN HasKeywords hk NATURAL JOIN TH th ORDER BY hk.hid;";
		String output="<table>";
		output += "<tr> <th> hid </th> <th> hname </th> <th> wid </th> <th> word </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("hid")+"</td>"
						+ "<td>"+rs.getString("hname")+"</td>"
						+ "<td>"+rs.getString("wid")+"</td>"
						+ "<td>"+rs.getString("word")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String getHasKeyWordDescription(String login, Statement stmt)
	{
		String sql="SELECT hk.hid, th.hname, hk.wid, k.word FROM Keywords k NATURAL JOIN HasKeywords hk NATURAL JOIN TH th WHERE login = '"+login+"' ORDER BY hk.hid;";
		String output="<table>";
		output += "<tr> <th> hid </th> <th> hname </th> <th> wid </th> <th> word </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("hid")+"</td>"
						+ "<td>"+rs.getString("hname")+"</td>"
						+ "<td>"+rs.getString("wid")+"</td>"
						+ "<td>"+rs.getString("word")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String getAllAvailableForLoginTH(String login, Statement stmt)
	{
		String sql="SELECT hid, hname, pid, from_date, to_date, price_per_night FROM Available NATURAL JOIN TH NATURAL JOIN Period\n" +
				"WHERE login = '"+login+"'";
		String output = "<table>";
		output += "<tr> <th> hid </th> <th> hname </th> <th> pid </th> <th> from date </th> <th> to date </th> <th> price per night </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("hid")+"</td>"
						+ "<td>"+rs.getString("hname")+"</td>"
						+ "<td>"+rs.getString("pid")+"</td>"
						+ "<td>"+rs.getString("from_date")+"</td>"
						+ "<td>"+rs.getString("to_date")+"</td>"
						+ "<td>"+rs.getString("price_per_night")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	/*****This is where Ben's code starts*****/
	public void recordVisit(List<List<String>> chosenPeriods, Connector con)
	{
		String thid = "";
		String fromDate = "";
		String toDate = "";
		List<String> chosenPeriod = new ArrayList<>();
		try {
			for(int i = 0; i < visits.size(); i+=1){
				thid = visits.get(i).get(0);
				chosenPeriod = chosenPeriods.get(i);
				fromDate = visits.get(i).get(2);
				toDate = visits.get(i).get(3);
				addStay(thid, chosenPeriod, fromDate, toDate, con.stmt);
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 *
	 * Will print out all the THs that customers have visited who have visited what the user just reserved
	 * @param in
	 * @param con
	 */
	public String suggestTH(List<List<String>> reservations, Connector con)
	{
		Visit visit = new Visit();
		Set<String> ths = new HashSet<String>();
		for(int j = 0; j < reservations.size(); j+=1) {
			ths.add(reservations.get(j).get(0));
		}
		String output = "";
		for(String th: ths){
			output += "<caption> Based on your reservation for " + th + ", here are some other THs we would suggest</caption>";
			output += "<table>";
			output += "<tr> <th> Hid </th> <th> Hname </th> <th> Category </th> <th> Address </th> <th> Year Built </th> <th> Phone Number </th> <th> Url </th> <th> Number of Visits </th>";
			List<List<String>> suggestedTHs = visit.getSuggestedTHs(th, con.stmt);
			for(List<String> suggestedTH : suggestedTHs) {
				if(suggestedTH.get(0).equals(th)){
					continue;
				}
				output += "<tr>";
				for(int i = 0; i < suggestedTH.size(); i++) {
					String attribute = suggestedTH.get(i);
					output += "<td>" + attribute + "</td>";
				}
				output += "</tr>";

			}
			output += "</table> <BR>";

		}
		return output;
	}

	private void addStay(String thid, List<String> chosenPeriod, String fromDate, String toDate, Statement stmt)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Available available = new Available();
		Visit visit = new Visit();
		try {
			String newPid = "";
			Date givenFromDate = convertToDate(fromDate, formatter);//new Date(formatter.parse(fromDate).getTime());
			Date givenToDate = convertToDate(toDate, formatter);//new Date(formatter.parse( toDate).getTime());

			Date periodFromDate = convertToDate(chosenPeriod.get(1), sqlDateFormatter);//new Date(sqlDateFormatter.parse(chosenPeriod.get(1)).getTime());
			Date periodToDate = convertToDate(chosenPeriod.get(2), sqlDateFormatter);//new Date(sqlDateFormatter.parse(chosenPeriod.get(2)).getTime());
			if((periodFromDate.before(givenFromDate) || periodFromDate.equals(givenFromDate))
					&& (periodToDate.after(givenFromDate) || periodToDate.equals(periodToDate))) {

				// If the visit period is the same as the available period
				if (periodFromDate.equals(givenFromDate) && periodToDate.equals(givenToDate)) {
					available.deleteAvailable(thid, chosenPeriod.get(0), stmt);
					visit.addVisit(_currentUser, thid, chosenPeriod.get(0), stmt);

				}

				// If the visit's to date is the same as the available periods to date but has a different from date.
				else if (periodFromDate.before(givenFromDate) && periodToDate.equals(givenToDate)) {
					addVisit(thid, givenFromDate, givenToDate, chosenPeriod, stmt);

					// Get the period that is leftover before the visit and make it available
					addLeftoverPeriodBeforeStay(givenFromDate, periodFromDate,thid, chosenPeriod, stmt);


				} else if (periodFromDate.equals(givenFromDate) && periodToDate.after(givenToDate)) {
					addVisit(thid, givenFromDate, givenToDate, chosenPeriod, stmt);

					// Get the period that is leftover after the visit and make it available
					addLeftoverPeriodAfterStay(givenToDate,periodToDate, thid, chosenPeriod, stmt);

				} else {
					addVisit(thid, givenFromDate, givenToDate, chosenPeriod, stmt);

					addLeftoverPeriodBeforeStay(givenFromDate, periodFromDate,thid, chosenPeriod, stmt);
					addLeftoverPeriodAfterStay(givenToDate,periodToDate, thid, chosenPeriod, stmt);
				}
			}
			else{
				System.out.println("Please enter valid start date");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void addVisit(String thid, Date givenFromDate, Date givenToDate, List<String> chosenPeriod, Statement stmt)
	{
		Period period = new Period();
		Visit visit = new Visit();
		Available available = new Available();
		List<List<String>> firstPeriod = period.getPeriod(givenFromDate.toString(), givenToDate.toString(), stmt);
		String newPid;

		// Checks if a period already exists that meets the needs of the visit period
		// If not then we will make one.
		if(firstPeriod.isEmpty()){
			period.addPeriod(givenFromDate.toString(), givenToDate.toString(), stmt);
			newPid = period.getLastPeriod(stmt);
			visit.addVisit(_currentUser, thid, newPid, stmt);
		}
		else {
			newPid = firstPeriod.get(0).get(0);
			visit.addVisit(_currentUser, thid, newPid, stmt);
		}
	}

	public void addLeftoverPeriodBeforeStay(Date givenFromDate, Date periodFromDate, String thid, List<String> chosenPeriod, Statement stmt)
	{
		Period period = new Period();
		Available available = new Available();
		String newPid;

		// Get the period that is leftover before the reservation and make it available
		Calendar cal = Calendar.getInstance();
		cal.setTime(givenFromDate);
		cal.add(Calendar.DATE, -1);
		Date dateBeforeReservation = new Date(cal.getTime().getTime());
		List<List<String>> secondPeriod = period.getPeriod(periodFromDate.toString(), dateBeforeReservation.toString(), stmt);
		if (secondPeriod.isEmpty()) {
			period.addPeriod(periodFromDate.toString(), dateBeforeReservation.toString(), stmt);
			newPid = period.getLastPeriod(stmt);
			available.addAvailable(thid, newPid, chosenPeriod.get(2), stmt);
		} else {
			newPid = secondPeriod.get(0).get(0);
			available.addAvailable(thid, newPid, chosenPeriod.get(2),stmt);
		}
	}

	public void addLeftoverPeriodAfterStay(Date givenToDate, Date periodToDate, String thid, List<String> chosenPeriod, Statement stmt)
	{
		Period period = new Period();
		Available available = new Available();
		String newPid;

		// Get the period that is leftover after the reservation and make it available
		Calendar cal = Calendar.getInstance();
		cal.setTime(givenToDate);
		cal.add(Calendar.DATE, 1);
		Date dateAfterReservation = new Date(cal.getTime().getTime());
		List<List<String>> secondPeriod = period.getPeriod(dateAfterReservation.toString(), periodToDate.toString(), stmt);
		if (secondPeriod.isEmpty()) {
			period.addPeriod(dateAfterReservation.toString(), periodToDate.toString(), stmt);
			newPid = period.getLastPeriod(stmt);
			available.addAvailable(thid, newPid, chosenPeriod.get(2), stmt);
		}
		else {
			newPid = secondPeriod.get(0).get(0);
			available.addAvailable(thid, newPid, chosenPeriod.get(2), stmt);
		}
	}

	public String getVisitedTHS(Statement stmt)
	{
		String sql="select DISTINCT(th.hid), category, hname\n" +
				"from Visit v, TH th\n" +
				"where v.hid = th.hid\n" +
				"and v.login = '" + _currentUser + "'";
		String output = "<table>";
		output += "<tr> <th> hid </th> <th> category </th> <th> hname </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output += "<tr><td>"+rs.getString("hid")+"</td>"
							+ "<td>"+rs.getString("category")+"</td>"
							+ "<td>"+rs.getString("hname")+"</td>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public void leaveFeedback(String th, String score, String shortText, Connector con)
	{

		try {

			int thid = Integer.parseInt(th);



			Feedback feedback = new Feedback();
			List<List<String>> alreadyHaveFeedback = feedback.getFeedback(th, _currentUser, con.stmt);
			Visit stay = new Visit();
			if (stay.getVisit(_currentUser, thid, con.stmt) != "") {
				//System.out.println("That username has already been used");
				feedback.addFeedback(th,_currentUser, shortText,  Integer.parseInt(score), con.stmt);
				if(alreadyHaveFeedback.size() > 0){
					feedback.deleteFeedback(alreadyHaveFeedback.get(0).get(0), con.stmt);
				}
			} else {
				System.out.println("We have no record of that visit");

			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public boolean declareTHAsFavorite(String thid, Connector con)
	{
		Favorites favorite = new Favorites();
		favorite.addFavorite(Integer.parseInt(thid), _currentUser, con.stmt);
		return true;
	}

	public List<List<String>> makeReservation(List<List<String>> chosenPeriods,Connector con)
	{
		String thid = "";
		String pid = "";
		String fromDate = "";
		String toDate = "";
		//List<List<String>> reservations = new ArrayList<List<String>>();
		List<String> chosenPeriod = new ArrayList<String>();
		boolean readyForCheckout = false;
		boolean makeAnotherReservation = false;
		try {
//			while(!(readyForCheckout)) {
//				List<String> reservation = new ArrayList<String>();
//				System.out.println(getTHAvailableTimes(in, con));
//				System.out.println("please choose the TH you want to Reserve (hid):");
//				while ((thid = in.readLine()) == null && thid.length() == 0) ;
//				reservation.add(thid);
//
//				System.out.println("please choose a period of time you would like to reserve (pid):");
//				while ((pid = in.readLine()) == null && pid.length() == 0) ;
//				//reservation.add(pid);
//				Period period = new Period();
//				chosenPeriod = period.getPeriod(pid, con.stmt).get(0);
//				System.out.println("Your Chosen Period for TH: " + thid);
//				System.out.println("\t\t" + chosenPeriod.get(0) + "\t\t" + chosenPeriod.get(1) + "\t\t" + chosenPeriod.get(2));
//				//getTHAndPeriod(thid, pid, in, con);
//
//				System.out.println("please choose a start date for your reservation (date format: mm/dd/yyyy ex: 01/23/1994):");
//				while ((fromDate = in.readLine()) == null && fromDate.length() == 0) ;
//				reservation.add(fromDate);
//
//				System.out.println("please choose an end date for your reservation (date format: mm/dd/yyyy ex: 01/23/1994):");
//				while ((toDate = in.readLine()) == null && toDate.length() == 0) ;
//				reservation.add(toDate);
//
//				reservations.add(reservation);
//				reservations.add(chosenPeriod);
//
//				String makeAnother;
//				System.out.println("Would you like to make another reservation? (Please answer yes or no)");
//				while(true) {
//					String choice;
//					int c;
//					System.out.println("\nWould you like to make another reservation? \n 1: yes \n 2: no");
//					while ((choice = in.readLine()) == null && choice.length() == 0) ;
//					try {
//						c = Integer.parseInt(choice);
//					} catch (Exception e) {
//						continue;
//					}
//					if (c < 1 | c > 5) {
//						continue;
//					}
//					// user chooses to make another reservation
//					if (c == 1) {
//						break;
//					}
//					// user does not want to make another reservation
//					else if (c == 2) {
//						readyForCheckout = true;
//						break;
//					}
//				}

//			}

			// Display all reservations and ask if they would like to checkout
//			System.out.println("\nList of made reservations:\nthid\t\tpid\t\tfrom_date\t\tto_date");
//			for(int i = 0; i < reservations.size(); i+=2){
//				String reservation = "";
//				reservation += reservations.get(i).get(0) + "\t\t";
//				reservation += reservations.get(i).get(1) + "\t\t";
//				reservation += reservations.get(i).get(2);
//				System.out.println(reservation);
//			}
//			while(true) {
//				String choice;
//				int c;
//				System.out.println("\nWould you like to Checkout? \n 1: Checkout \n 2: quit");
//				while ((choice = in.readLine()) == null && choice.length() == 0) ;
//				try {
//					c = Integer.parseInt(choice);
//				} catch (Exception e) {
//					continue;
//				}
//				if (c < 1 | c > 5) {
//					continue;
//				}
//				// User makes a reservation
//				if (c == 1) {
//					break;
//				}
//				// Leave feedback on stay
//				else if (c == 2) {
//					return new ArrayList<List<String>>();
//				}
//			}


			for(int i = 0; i < reservations.size(); i+=1){
//				for(int j = 0; j < reservations.get(i).size(); j++){
				thid = reservations.get(i).get(0);
				chosenPeriod = chosenPeriods.get(i);
				fromDate = reservations.get(i).get(2);
				toDate = reservations.get(i).get(3);
				addReservation(thid, chosenPeriod, fromDate, toDate, con.stmt);
				//}
			}
			return reservations;
//			Reserve reservation = new Reserve();
//			if (reservation.getReserve(Integer.parseInt(thid), Integer.parseInt(pid), con.stmt) != "") {
//				System.out.println("This TH has already been reserved for that time period");
//			} else {
//				reservation.addReservation(_currentUser, Integer.parseInt(thid), Integer.parseInt(pid), con.stmt);
//			}
		}
		catch (Exception e){
			e.printStackTrace();
			return new ArrayList<List<String>>();
		}
	}

	private void addReservation(String thid, List<String> chosenPeriod, String fromDate, String toDate, Statement stmt)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Available available = new Available();
		Reserve reservation = new Reserve();
		try {

			String newPid = "";
			Date givenFromDate = convertToDate(fromDate, formatter);//new Date(formatter.parse(fromDate).getTime());
			Date givenToDate = convertToDate(toDate, formatter);//new Date(formatter.parse( toDate).getTime());

			Date periodFromDate = convertToDate(chosenPeriod.get(1), sqlDateFormatter);//new Date(sqlDateFormatter.parse(chosenPeriod.get(1)).getTime());
			Date periodToDate = convertToDate(chosenPeriod.get(2), sqlDateFormatter);//new Date(sqlDateFormatter.parse(chosenPeriod.get(2)).getTime());
			if((periodFromDate.before(givenFromDate) || periodFromDate.equals(givenFromDate))
					&& (periodToDate.after(givenFromDate) || periodToDate.equals(periodToDate))) {

				// If the reservation period is the same as the available period
				if (periodFromDate.equals(givenFromDate) && periodToDate.equals(givenToDate)) {
					available.deleteAvailable(thid, chosenPeriod.get(0), stmt);
					reservation.addReservation(_currentUser, thid, chosenPeriod.get(0), stmt);

				}

				// If the reservation's to date is the same as the available periods to date but has a different from date.
				else if (periodFromDate.before(givenFromDate) && periodToDate.equals(givenToDate)) {
					addReservationAndRemoveAvailable(thid, givenFromDate, givenToDate, chosenPeriod, stmt);

					// Get the period that is leftover before the reservation and make it available
					addLeftoverPeriodBeforeReservation(givenFromDate, periodFromDate,thid, chosenPeriod, stmt);


				} else if (periodFromDate.equals(givenFromDate) && periodToDate.after(givenToDate)) {
					addReservationAndRemoveAvailable(thid, givenFromDate, givenToDate, chosenPeriod, stmt);

					// Get the period that is leftover after the reservation and make it available
					addLeftoverPeriodAfterReservation(givenToDate,periodToDate, thid, chosenPeriod, stmt);

				} else {
					addReservationAndRemoveAvailable(thid, givenFromDate, givenToDate, chosenPeriod, stmt);

					addLeftoverPeriodBeforeReservation(givenFromDate, periodFromDate,thid, chosenPeriod, stmt);
					addLeftoverPeriodAfterReservation(givenToDate,periodToDate, thid, chosenPeriod, stmt);
				}
			}
			else{
				System.out.println("Please enter valid start date");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Adds the users requested reservation and removes it from available for that TH
	 * @param thid
	 * @param givenFromDate
	 * @param givenToDate
	 * @param chosenPeriod
	 * @param stmt
	 */
	public void addReservationAndRemoveAvailable(String thid, Date givenFromDate, Date givenToDate, List<String> chosenPeriod, Statement stmt)
	{
		Period period = new Period();
		Reserve reservation = new Reserve();
		Available available = new Available();
		List<List<String>> firstPeriod = period.getPeriod(givenFromDate.toString(), givenToDate.toString(), stmt);
		String newPid;

		// Checks if a period already exists that meets the needs of the reservation period
		// If not then we will make one.
		if(firstPeriod.isEmpty()){
			period.addPeriod(givenFromDate.toString(), givenToDate.toString(), stmt);
			newPid = period.getLastPeriod(stmt);
			reservation.addReservation(_currentUser, thid, newPid, stmt);
		}
		else {
			newPid = firstPeriod.get(0).get(0);
			reservation.addReservation(_currentUser, thid, newPid, stmt);
		}

		// Removes the old period from available because it is no longer available
		available.deleteAvailable(thid, chosenPeriod.get(0), stmt);
	}

	public void addLeftoverPeriodBeforeReservation(Date givenFromDate, Date periodFromDate, String thid, List<String> chosenPeriod, Statement stmt)
	{
		Period period = new Period();
		Available available = new Available();
		String newPid;

		// Get the period that is leftover before the reservation and make it available
		Calendar cal = Calendar.getInstance();
		cal.setTime(givenFromDate);
		cal.add(Calendar.DATE, -1);
		Date dateBeforeReservation = new Date(cal.getTime().getTime());
		List<List<String>> secondPeriod = period.getPeriod(periodFromDate.toString(), dateBeforeReservation.toString(), stmt);
		if (secondPeriod.isEmpty()) {
			period.addPeriod(periodFromDate.toString(), dateBeforeReservation.toString(), stmt);
			newPid = period.getLastPeriod(stmt);
			available.addAvailable(thid, newPid, chosenPeriod.get(2), stmt);
		}
		else {
			newPid = secondPeriod.get(0).get(0);
			available.addAvailable(thid, newPid, chosenPeriod.get(2),stmt);
		}
	}

	public void addLeftoverPeriodAfterReservation(Date givenToDate, Date periodToDate, String thid, List<String> chosenPeriod, Statement stmt)
	{
		Period period = new Period();
		Available available = new Available();
		String newPid;

		// Get the period that is leftover after the reservation and make it available
		Calendar cal = Calendar.getInstance();
		cal.setTime(givenToDate);
		cal.add(Calendar.DATE, 1);
		Date dateAfterReservation = new Date(cal.getTime().getTime());
		List<List<String>> secondPeriod = period.getPeriod(dateAfterReservation.toString(), periodToDate.toString(), stmt);
		if (secondPeriod.isEmpty()) {
			period.addPeriod(dateAfterReservation.toString(), periodToDate.toString(), stmt);
			newPid = period.getLastPeriod(stmt);
			available.addAvailable(thid, newPid, chosenPeriod.get(2), stmt);
		}
		else {
			newPid = secondPeriod.get(0).get(0);
			available.addAvailable(thid, newPid, chosenPeriod.get(2), stmt);
		}
	}

	public Date convertToDate(String date, SimpleDateFormat formatter)
	{
		Date givenDate = null;
		try
		{
			givenDate = new Date(formatter.parse(date).getTime());
		}
		catch (Exception e){
			System.out.println("Invalid Date was given");
		}
		return givenDate;
	}

	public String getTHAvailableTimes(Connector con)
	{
		String sql="select a.hid, p.pid, p.from_date, p.to_date\n" +
				"from Available a, Period p\n" +
				"where a.pid = p.pid\n" +
				"GROUP BY a.hid, p.pid, p.from_date, p.to_date";
		String output = "<table>";
		output += "<tr> <th> hid </th> <th> pid </th> <th> from date </th> <th> to date </th> </tr>";
		ResultSet rs=null;
		try{
			rs=con.stmt.executeQuery(sql);
			int count = 0;
			while (rs.next())
			{
				output += "<tr><td>"+rs.getString("hid")+"</td>"
							+"<td>"+rs.getString("pid")+"</td>"
							+"<td>"+rs.getString("from_date")+"</td>"
							+"<td>"+rs.getString("to_date")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String getUsersReservations(Connector con)
	{
		String sql="select r.login, r.hid, p.pid, p.from_date, p.to_date\n" +
				"from Reserve r, Period p\n" +
				"where r.pid = p.pid\n" +
				"and r.login = '" + _currentUser + "'";
		String output = "<table>";
		output += "<tr> <th> login </th> <th> hid </th> <th> pid </th> <th> from date </th> <th> to date </th> </tr>";
		ResultSet rs=null;
		try{
			rs=con.stmt.executeQuery(sql);
			while (rs.next())
			{
				output += "<tr><td>"+rs.getString("login")+"</td>"
						+"<td>"+rs.getString("hid")+"</td>"
						+"<td>"+rs.getString("pid")+"</td>"
						+"<td>"+rs.getString("from_date")+"</td>"
						+"<td>"+rs.getString("to_date")+"</td></tr>";
			}
			output += "</table>";
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}
}
