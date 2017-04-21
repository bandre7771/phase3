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
	///Display Menu
	public void displayLoginMenu()
	{
		System.out.println("       Welcome to Uotel     ");
		System.out.println("1. Login:");
		System.out.println("2. Register:");
		System.out.println("3. exit: ");
		System.out.println( "please enter your choice:");
    }

	public void displayUserMenu()
	{
		System.out.println("       Welcome: "+ _currentUser+"     ");
		System.out.println("1. reserve:");
		System.out.println("2. th:");
		System.out.println("3. stay recordings:");
		System.out.println("4. favorite recordings:");
		System.out.println("5. feedback recordings:");
		System.out.println("6. usefulness ratings:");
		System.out.println("7. trust recordings:");
		System.out.println("8. useful feedbacks:");
		System.out.println("9. two degrees of separation:");
		System.out.println("10. statistics:");
		System.out.println("11. user awards (Admin Only):");
		System.out.println("12. logout:");
		System.out.println( "please enter your choice:");
    }

	public void displayTHMenu()
	{
		System.out.println("       TH     ");
		System.out.println("1. add TH:");
		System.out.println("2. update TH:");
		System.out.println("3. browse TH:");
		System.out.println("4. keywords TH:");
		System.out.println("5. availability TH:");
		System.out.println("6. exit:");
		System.out.println( "please enter your choice:");
	}

	public void displayTHBrowsingMenu()
	{
		System.out.println("       TH Browsing     ");
		System.out.println("1. search & sort by:");
		System.out.println("2. exit:");
		System.out.println( "please enter your choice:");
	}

	public void displaySearchByTHMenu()
	{
		System.out.println("       TH Browsing     ");
		System.out.println("search by");
		System.out.println("1. all (clears current search):");
		System.out.println("2. price:");
		System.out.println("3. address:");
		System.out.println("4. name:");
		System.out.println("5. category:");
		System.out.println( "please enter your choice:");
	}

	public void displayKeywordsTHMenu()
	{
		System.out.println("       TH Keywords     ");
		System.out.println("1. add keyword:");
		System.out.println("2. update keyword:");
		System.out.println("3. show all TH keywords:");
		System.out.println("4. assign TH keyword:");
		System.out.println("5. unassign TH keyword:");
		System.out.println("6. exit:");
		System.out.println("please enter your choice:");
	}

	public void displayAvailabilityTHMenu()
	{
		System.out.println("       Availability TH     ");
		System.out.println("1. add TH period of availability:");
		System.out.println("2. update TH period of availability:");
		System.out.println("3. show all periods of availability:");
		System.out.println("4. exit:");
	}

	public void displayusefulFeedbacksMenu(Statement stmt)
	{
		System.out.println("       Useful Feedbacks     ");
		TH th = new TH();
		System.out.println(th.getAllTH(stmt));
		System.out.println("please enter a TH id (hid):");
	}

	public void displayTwoDegreesOfSeparationMenu(Statement stmt)
	{
		System.out.println("       Two Degrees of Separation     ");
		Users users = new Users();
		System.out.println(users.getAllUsers(stmt));
	}

//	public static void displayStaysMenu(Statement stmt)
//	{
//		System.out.println("       Stays     ");
//		Reserve reserve = new Reserve();
//		System.out.println(reserve.getReserve(_currentUser, stmt));
//		System.out.println("please enter the TH id (hid)");
//	}

	public void displayUsefulnessRatingsMenu(Statement stmt)
	{
		System.out.println("       Usefulness Recording     ");
		Feedback feedback = new Feedback();
		System.out.println(feedback.getAllOtherUserFeedback(_currentUser, stmt));
		System.out.println( "please enter the Feedback ID (fid):");
	}

	public void displayTrustedRecordingsMenu(Statement stmt)
	{
		System.out.println("       Trusted Rercordings     ");
		Users users = new Users();
		System.out.println(users.getAllOtherUsers(_currentUser, stmt));
		System.out.println( "please enter the User login:");
	}

	public void displayStatisticsMenu()
	{
		System.out.println("       Statistics     ");
		System.out.println("1. Show top most popular TH's for each category:");
		System.out.println("2. Show top most expensive TH's for each category:");
		System.out.println("3. Show top most highly rated TH's for each category:");
		System.out.println("4. exit");
	}

	public void displayUserAdminMenu()
	{
		System.out.println("       User Awards     ");
		System.out.println("1. show top most trusted users:");
		System.out.println("2. show top most useful users:");
		System.out.println("3. exit:");
		System.out.println("please enter a choice:");
	}

//	public static void main(String[] args)
//	{
//		System.out.println("5530u16");
//		Connector con=null;
//        String sql=null;
//         try
//		 {
//		 		 con = new Connector();
//	             System.out.println ("Database connection established");
//	             loginMenu(con);
//		 }
//         catch (Exception e)
//         {
//        	 e.printStackTrace();
//        	 System.err.println ("Connection error");
//         }
//         finally
//         {
//        	 if (con != null)
//        	 {
//        		 try
//        		 {
//        			 con.closeConnection();
//        			 System.out.println ("Database connection terminated");
//        		 }
//
//        		 catch (Exception e) { /* ignore close errors */ }
//        	 }
//         }
//
//	}
//
//	///Menus
//	public static void loginMenu(Connector con)
//	{
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		String choice;
//		boolean exit = false;
//		int c=0;
//
//		try
//		{
//			while(!exit)
//			{
//				displayLoginMenu();
//				while ((choice = in.readLine()) == null && choice.length() == 0);
//				try
//				{
//					c = Integer.parseInt(choice);
//				}
//				catch (Exception e)
//				{
//					continue;
//				}
//				switch (c)
//				{
//					case 1: //login
//						if(loginUser(in, con)) {
//							userMenu(con);
//						}
//						break;
//					case 2: //Register
//						registerUser(in, con);
//						break;
//					case 3:
//						exit = true;
//					default:
//						continue;
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			System.err.println ("Query Error");
//		}
//	}
//
//	public static void userMenu(Connector con)
//	{
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		String choice;
//		int c=0;
//		try
//		{
//			boolean exit = false;
//			while(!exit)
//			{
//				displayUserMenu();
//				while ((choice = in.readLine()) == null && choice.length() == 0);
//				try
//				{
//					c = Integer.parseInt(choice);
//				}
//				catch (Exception e)
//				{
//					continue;
//				}
//				switch (c)
//				{
//					case 1:
//						List<List<String>> reservations = makeReservation(in, con);
//						suggestTH(reservations, in, con);
//						break;
//                    case 2:
//                        tHMenu(con);
//                        break;
//					case 3:
//						recordVisit(in, con);
//						break;
//					case 4:
//						String thid;
//						System.out.println(getVisitedTHS(con.stmt));
//						System.out.println("Please enter the TH id you would like to favorite (hid)");
//						while ((thid = in.readLine()) == null && thid.length() == 0) ;
//						if(declareTHAsFavorite(thid, in, con))
//						{
//							System.out.println("Th: " + thid + " has successfully been favorited");
//						}
//						break;
//					case 5:
//						leaveFeedback(in, con);
//						break;
//					case 6:
//						usefulnessRatingsMenu(con);
//						break;
//					case 7:
//						trustRecordingsMenu(con);
//						break;
//					case 8:
//						usefulFeedbacksMenu(con);
//						break;
//					case 9:
//						twoDegreesOfSeparationMenu(con);
//						break;
//					case 10:
//						statisticsMenu(con);
//						break;
//					case 11:
//						if(isAdmin(_currentUser, con.stmt))
//						{
//							System.out.println("Access Granted.");
//							userAwardsMenu(con);
//						}
//						else
//						{
//							System.out.println("Access Denied! You must be an administrator to access this menu.");
//						}
//						break;
//					case 12:
//						exit = true;
//						break;
//					default:
//						continue;
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			System.err.println ("Query Error");
//		}
//	}
//
//    public static void tHMenu(Connector con)
//    {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String choice;
//        String hname;
//        String category;
//        String url;
//        String phone_number;
//        String year;
//        String picture;
//		String hid;
//		String address;
//        TH th;
//        int c=0;
//        try
//        {
//			boolean exit = false;
//            while(!exit)
//            {
//                displayTHMenu();
//                while ((choice = in.readLine()) == null && choice.length() == 0);
//                try
//                {
//                    c = Integer.parseInt(choice);
//                }
//                catch (Exception e)
//                {
//                    continue;
//                }
//                switch (c)
//                {
//                    case 1: // Add TH
//                        th = new TH();
//						System.out.println("       Add TH     ");
//
//						System.out.println("please enter TH name:");
//                        while ((hname = in.readLine()) == null && hname.length() == 0);
//
//                        System.out.println("please enter category name:");
//                        while ((category = in.readLine()) == null && category.length() == 0);
//
//						System.out.println("please enter address:");
//						while ((address = in.readLine()) == null && address.length() == 0) ;
//
//						System.out.println("please enter phone number:");
//                        while ((phone_number =  in.readLine()) == null && phone_number.length() == 0);
//
//                        System.out.println("please enter year built:");
//                        while ((year =  in.readLine()) == null && year.length() == 0);
//
//                        System.out.println("please add picture:");
//                        while ((picture =  in.readLine()) == null && picture.length() == 0);
//
//                        System.out.println("please enter url:");
//                        while ((url =  in.readLine()) == null && url.length() == 0);
//                        th.addTH(category,_currentUser,hname,address,url,phone_number,year,picture,con.stmt);
//                        break;
//                    case 2:
//						th = new TH();
//						System.out.println("       Update TH     ");
//						System.out.println(th.getTHForLogin(_currentUser, con.stmt));
//						System.out.println("please enter TH id (hid):");
//						while ((hid = in.readLine()) == null && hid.length() == 0);
//						if (!th.isOwnerOfTH(_currentUser, hid, con.stmt)) {
//							System.out.println("You do not own the TH with id:"+hid);
//							break;
//						}
//						System.out.println("please enter TH name:");
//						while ((hname = in.readLine()) == null && hname.length() == 0);
//
//						System.out.println("please enter category name:");
//						while ((category = in.readLine()) == null && category.length() == 0);
//
//						System.out.println("please enter address:");
//						while ((address = in.readLine()) == null && address.length() == 0) ;
//
//						System.out.println("please enter phone number:");
//						while ((phone_number =  in.readLine()) == null && phone_number.length() == 0);
//
//						System.out.println("please enter year built:");
//						while ((year =  in.readLine()) == null && year.length() == 0);
//
//						System.out.println("please add picture:");
//						while ((picture =  in.readLine()) == null && picture.length() == 0);
//
//						System.out.println("please enter url:");
//						while ((url =  in.readLine()) == null && url.length() == 0);
//
//						th.updateTH(hid, category, _currentUser, hname, address, url, phone_number, year, picture, con.stmt);
//                        break;
//					case 3: //browse TH
//						tHBrowsingMenu(con);
//						break;
//					case 4: //keywords TH
//						keywordsTHMenu(con);
//						break;
//					case 5: //availability TH
//						availabilityTHMenu(con);
//						break;
//					case 6:
//						exit = true;
//						break;
//                    default:
//                    	continue;
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            System.err.println ("Query Error");
//        }
//    }

//	public static void staysMenu(Connector con)
//	{
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		String choice;
//		String hid;
//		String pid;
//		String cost;
//		ArrayList<String> pids = new ArrayList<String>();
//		ArrayList<String> hids = new ArrayList<String>();
//		ArrayList<String> costs = new ArrayList<String>();
//		Visit visit;
//		int c=0;
//		try
//		{
//			boolean exit = false;
//			while(!exit)
//			{
//				displayStaysMenu(con.stmt);
//				while ((hid = in.readLine()) == null && hid.length() == 0);
//				System.out.println("please enter the period id (pid)");
//				while ((pid = in.readLine()) == null && pid.length() == 0);
//				System.out.println("please enter the cost of the stay");
//				while ((cost = in.readLine()) == null && cost.length() == 0);
//				hids.add(hid);
//				pids.add(pid);
//				costs.add(cost);
//				System.out.println("1. record another stay");
//				System.out.println("2. exit:");
//				while ((choice = in.readLine()) == null && choice.length() == 0);
//				try
//				{
//					c = Integer.parseInt(choice);
//				}
//				catch (Exception e)
//				{
//					continue;
//				}
//				switch (c)
//				{
//					case 1:
//						break;
//					case 2:
//						exit = false;
//						while(!exit) {
//							System.out.println("You have recorded stays for the following:");
//							System.out.println("hid\t\tpid\t\tcost");
//							for (int i = 0; i < hids.size(); i++)
//							{ //print out all the stays recorded
//								System.out.print(hids.get(i)+"\t\t");
//								System.out.print(pids.get(i)+"\t\t");
//								System.out.println("$"+costs.get(i));
//							}
//							System.out.println("1. confirm and exit:");
//							System.out.println("2. cancel and exit:");
//							System.out.println("please enter your choice:");
//							String subChoice;
//							while ((subChoice = in.readLine()) == null && subChoice.length() == 0);
//							try
//							{
//								c = Integer.parseInt(subChoice);
//							}
//							catch (Exception e)
//							{
//								continue;
//							}
//							switch (c) {
//								case 1: //save and exit
//									for (int i = 0; i < hids.size(); i++)
//									{ //all the stays recorded
//										visit = new Visit();
//										hid = hids.get(i);
//										pid = pids.get(i);
//										cost = costs.get(i);
//										visit.addVisit(_currentUser, hid, pid, cost, con.stmt);
//									}
//									exit = true;
//									break;
//								case 2: //cancel and exit
//									exit = true;
//									break;
//								default:
//									continue;
//							}
//						}
//						break;
//					default:
//						continue;
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			System.err.println ("Query Error");
//		}
//	}

	public String mostUsefulUsers(String m, Statement stmt)
	{
		String sql="SELECT  f.login, avg(r.rating) as usefulness_score\n" +
				"FROM Feedback f, Rates r\n" +
				"WHERE f.fid = r.fid\n" +
				"GROUP BY f.login\n" +
				"ORDER BY usefulness_score DESC LIMIT "+m;
		String output="";
		ResultSet rs=null;
		//System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="login: "+rs.getString("login")+"   "
						+ "usefulness: "+rs.getString("usefulness_score")+"\n";
			}

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

	public void usefulFeedbacksMenu(Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String n; // amount displayed
		String hid;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayusefulFeedbacksMenu(con.stmt);
				while ((hid = in.readLine()) == null && hid.length() == 0);
				System.out.println("please choose a number of top useful feedbacks to display for this TH:");
				while ((n = in.readLine()) == null && n.length() == 0);
				System.out.println(mostUsefulFeedbacksForTh(n,hid,con.stmt));
				System.out.println("press enter to when continue");
				in.readLine();
				exit = true;
			}
		}
		catch (Exception e)
		{
			System.err.println ("Query Error");
		}
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
				output+="<tr>"+rs.getString("fid")+"</td>"
						+ "<td>"+rs.getString("hid")+"</td>"
						+ "<td>"+rs.getString("login")+"</td>"
						+ "<td>"+rs.getString("text")+"</td>"
						+ "<td>"+rs.getString("score")+"</td>"
						+ "<td>"+rs.getString("fbdate")+"</td>"
						+ "<td>"+rs.getString("usefulness")+"</tr>";
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

	public void statisticsMenu(Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String m; // amount displayed
		String choice;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayStatisticsMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0);
				try
				{
					c = Integer.parseInt(choice);
				}
				catch (Exception e)
				{
					continue;
				}
				switch (c)
				{
					case 1:
						System.out.println("please enter the amount of TH’s you’d like to display for each category:");
						while ((m = in.readLine()) == null && m.length() == 0);
//						System.out.println(mostPopularThsPerCategory(m, con.stmt));
						break;
					case 2:
						System.out.println("please enter the amount of TH’s you’d like to display for each category:");
						while ((m = in.readLine()) == null && m.length() == 0);
//						System.out.println(mostExpensiveThsPerCategory(m, con.stmt));
						break;
					case 3:
						System.out.println("please enter the amount of TH’s you’d like to display for each category:");
						while ((m = in.readLine()) == null && m.length() == 0);
//						System.out.println(mostHighlyRatedThsPerCategory(m, con.stmt));
						break;
					case 4:
						exit = true;
						break;
					default:
						break;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println ("Query Error");
		}
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
		output += "<tr> <th> category </th> <th> hid </th> <th> hname </th> <th> address </th> <th> login </th> <th> phone number </th> <th> year built </th> <th> url </th> <th> picture </th> <th> average cost </th>";
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

	public void keywordsTHMenu(Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice = "";
		String word = "";
		String hid = "";
		String language = "";
		String wid = "";
		TH th;
		Keywords keywords;
		HasKeywords hasKeywords;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayKeywordsTHMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0);
				try
				{
					c = Integer.parseInt(choice);
				}
				catch (Exception e)
				{
					continue;
				}
				switch (c)
				{
					case 1: //add keyword
						System.out.println("       Adding keyword     ");
						keywords = new Keywords();
						System.out.println("please enter a language:");
						while ((language = in.readLine()) == null && language.length() == 0);
						System.out.println("please enter a keyword:");
						while ((word = in.readLine()) == null && word.length() == 0);
						keywords.addKeyword(word, language, con.stmt);
						break;
					case 2: //update keyword
						System.out.println("       Update keyword     ");
						keywords = new Keywords();
						System.out.println(keywords.getAllKeyWords(con.stmt));
						System.out.println("please enter a Keyword id (wid):");
						while ((wid = in.readLine()) == null && wid.length() == 0);
						System.out.println("please enter a language:");
						while ((language = in.readLine()) == null && language.length() == 0);
						System.out.println("please enter a word:");
						while ((word = in.readLine()) == null && word.length() == 0);
						keywords.updateKeyword(wid, word, language, con.stmt);
						break;
					case 3:
						System.out.println("       All TH keywords     ");
						System.out.println(getAllHasKeyWordDescription(con.stmt));
						System.out.println("press enter to continue:");
						in.readLine();
						break;
					case 4: //assign TH keyword
						System.out.println("       Assign TH keyword     ");
						th = new TH();
						System.out.println(th.getTHForLogin(_currentUser, con.stmt));
						System.out.println("please enter a TH id (hid):");
						while ((hid = in.readLine()) == null && hid.length() == 0);
						if (!th.isOwnerOfTH(_currentUser, hid, con.stmt)) {
							System.out.println("You don't own this TH or it doesn't exist");
							break;
						}
						keywords = new Keywords();
						System.out.println(keywords.getAllKeyWords(con.stmt));
						System.out.println("please enter a Keyword id (wid):");
						while ((wid = in.readLine()) == null && wid.length() == 0);
						hasKeywords = new HasKeywords();
						hasKeywords.addHasKeyword(hid,wid,con.stmt);
						break;
					case 5: //unassign TH keyword
						System.out.println("       Unassign TH keyword     ");
						hasKeywords = new HasKeywords();
						System.out.println(getHasKeyWordDescription(_currentUser, con.stmt));
						System.out.println("please enter a TH id (hid):");
						th = new TH();
						while ((hid = in.readLine()) == null && hid.length() == 0);
						if (!th.isOwnerOfTH(_currentUser, hid, con.stmt)) {
							System.out.println("You don't own this TH or it doesn't exist");
							break;
						}
						System.out.println("please enter a Keyword id (wid):");
						while ((wid = in.readLine()) == null && wid.length() == 0);
						hasKeywords.removeHasKeyword(hid, wid, con.stmt);
						break;
					case 6:
						exit = true;
						break;
					default:
						continue;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println ("Query Error");
		}
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

	public void availabilityTHMenu(Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice = "";
		String hid = "";
		String startDate = "";
		String endDate = "";
		String pid = "";
		String ppn = "";
		List<List<String>> pids = new ArrayList<List<String>>();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		TH th;
		Period period;
		Available available;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayAvailabilityTHMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0);
				try
				{
					c = Integer.parseInt(choice);
				}
				catch (Exception e)
				{
					continue;
				}
				switch (c)
				{
					case 1: //add TH period of availability
						System.out.println("       Add TH Period of Availability     ");
						period = new Period();
						th = new TH();
						available = new Available();
						System.out.println(th.getTHForLogin(_currentUser, con.stmt));
						System.out.println("please enter a TH id (hid):");
						while ((hid = in.readLine()) == null && hid.length() == 0);
						if (!th.isOwnerOfTH(_currentUser, hid, con.stmt)) {
							System.out.println("You don't own this TH or it doesn't exist");
							break;
						}
						System.out.println("please enter a start date (format: mm/dd/yyyy ex: 01/23/1994):");
						while ((startDate = in.readLine()) == null && startDate.length() == 0);
						Date sDate = convertToDate(startDate, formatter);

						System.out.println("please enter a end date (format: mm/dd/yyyy ex: 01/23/1994)::");
						while ((endDate = in.readLine()) == null && endDate.length() == 0);
						Date eDate = convertToDate(endDate, formatter);

						if(sDate.after(eDate))
						{
							System.out.println("start date must be before end date.");
							break;
						}

						pids = period.getPeriod(sDate.toString(), eDate.toString(), con.stmt);
						if(pids.isEmpty())
						{
							period.addPeriod(sDate.toString(), eDate.toString(), con.stmt);
							pid = period.getLastPeriod(con.stmt);
						}
						else
						{
							pid = pids.get(0).get(0);
						}
						System.out.println("please enter a to price per night:");
						while ((ppn = in.readLine()) == null && ppn.length() == 0);
						available.addAvailable(hid, pid, ppn, con.stmt);
						pids.clear();
						break;
					case 2: //update TH period of availability
						System.out.println("       Update TH Period of Availability     ");
						available = new Available();
						th = new TH();
						period = new Period();
						System.out.println(getAllAvailableForLoginTH(_currentUser, con.stmt));
						System.out.println("please enter a TH id (hid):");
						while ((hid = in.readLine()) == null && hid.length() == 0);
						if (!th.isOwnerOfTH(_currentUser, hid, con.stmt)) {
							System.out.println("You don't own this TH or it doesn't exist");
							break;
						}

						System.out.println("please enter a Period id (pid):");
						while ((pid = in.readLine()) == null && pid.length() == 0);
						if(period.getPeriod(pid, con.stmt).isEmpty())
						{
							System.out.println("invalid pid");
							break;
						}
						pids.clear();

						System.out.println("please enter a start date (format: mm/dd/yyyy ex: 01/23/1994):");
						while ((startDate = in.readLine()) == null && startDate.length() == 0);
						Date sDate2 = convertToDate(startDate, formatter);

						System.out.println("please enter a end date (format: mm/dd/yyyy ex: 01/23/1994)::");
						while ((endDate = in.readLine()) == null && endDate.length() == 0);
						Date eDate2 = convertToDate(endDate, formatter);

						if(sDate2.after(eDate2))
						{
							System.out.println("start date must be before end date.");
							break;
						}
						String newPid = "";
						pids = period.getPeriod(sDate2.toString(), eDate2.toString(), con.stmt);
						if(pids.isEmpty())
						{
							period.addPeriod(sDate2.toString(), eDate2.toString(), con.stmt);
							newPid = period.getLastPeriod(con.stmt);
						}
						else
						{
							newPid = pids.get(0).get(0);
						}

						System.out.println("please enter a to price per night:");
						while ((ppn = in.readLine()) == null && ppn.length() == 0);
						available.deleteAvailable(hid, pid, con.stmt);
						available.addAvailable(hid, newPid, ppn, con.stmt);
						pids.clear();
						break;
					case 3:
						//System.out.println(getTHAvailableTimes(in, con));
						System.out.println("please press enter to continue:");
						in.readLine();
						break;
					case 4:
						exit = true;
						break;
					default:
						continue;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println ("Query Error");
		}
	}

	public String getAllAvailableForLoginTH(String login, Statement stmt)
	{
		String sql="SELECT hid, hname, pid, from_date, to_date, price_per_night FROM Available NATURAL JOIN TH NATURAL JOIN Period\n" +
				"WHERE login = '"+login+"'";
		String output = "<table>";
		output = "<tr> <th> hid </th> <th> hname </th> <th> pid </th> <th> from date </th> <th> to date </th> <th> price per night </th> </tr>";
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
						+ "<td>"+rs.getString("price_per_night")+"</td><tr>";
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

	public void twoDegreesOfSeparationMenu(Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String login1;
		String login2;
		Favorites favorites;
		Users users;
		try
		{
			displayTwoDegreesOfSeparationMenu(con.stmt);
			System.out.println("please enter the first login");
			while ((login1 = in.readLine()) == null && login1.length() == 0);
			users = new Users();
			if(users.userExists(login1,con.stmt))
			{
				throw new Exception("Invalid Login");
			}
			System.out.println("please enter the second login");
			while ((login2 = in.readLine()) == null && login2.length() == 0);
			if(users.userExists(login2,con.stmt))
			{
				throw new Exception("Invalid Login");
			}
			favorites = new Favorites();
			if(!favorites.oneDegreeOfSeparation(login1,login2,con.stmt) && favorites.oneOrTwoDegreeOfSeparation(login1,login2,con.stmt))
			{
				System.out.println("login: " + login1 + " IS two degrees of separation from login: " + login2);
			}
			else
			{
				System.out.println("login: " + login1 + " IS NOT two degrees of separation from login: " + login2);
			}
			System.out.println("press enter to exit:");
			in.readLine();
		}
		catch (Exception e)
		{
			System.err.println ("Query Error possibly invalid login");
		}
	}

	public Boolean isAdmin(String login, Statement stmt)
	{
		String sql="SELECT user_type\n" +
				"FROM Users\n" +
				"WHERE login = '"+login+"'";
		String output="";
		Boolean isAdmin = false;
		ResultSet rs=null;
//		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= rs.getString("user_type");
			}
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
		try
		{
			 isAdmin = (1 == Integer.parseInt(output));
		}
		catch (Exception e)
		{
			return false;
		}
		return isAdmin;
	}

	/*****This is where Ben's code starts*****/
	public void recordVisit(List<List<String>> chosenPeriods, Connector con) {
		String thid = "";
		String pid = "";
		String fromDate = "";
		String toDate = "";
		List<String> chosenPeriod = new ArrayList<>();
//		boolean readyForCheckout = false;
		try {
//			while(!(readyForCheckout)) {
//				List<String> visit = new ArrayList<String>();
//				System.out.println(getUsersReservations(in, con));
//				System.out.println("please choose the TH you want to record as a stay (hid):");
//				while ((thid = in.readLine()) == null && thid.length() == 0) ;
//				visit.add(thid);
//
//				System.out.println("please choose the time period of your stay (pid):");
//				while ((pid = in.readLine()) == null && pid.length() == 0) ;
//				//visit.add(pid);
//				Period period = new Period();
//				chosenPeriod = period.getPeriod(pid, con.stmt).get(0);
//				System.out.println("Your Chosen Period for TH: " + thid);
//				System.out.println("\t\t" + chosenPeriod.get(0) + "\t\t" + chosenPeriod.get(1) + "\t\t" + chosenPeriod.get(2));
//				//getTHAndPeriod(thid, pid, in, con);
//
//				System.out.println("please choose a start date for your stay (date format: mm/dd/yyyy ex: 01/23/1994):");
//				while ((fromDate = in.readLine()) == null && fromDate.length() == 0) ;
//				visit.add(fromDate);
//
//				System.out.println("please choose an end date for your stay (date format: mm/dd/yyyy ex: 01/23/1994):");
//				while ((toDate = in.readLine()) == null && toDate.length() == 0) ;
//				visit.add(toDate);
//
//				visits.add(visit);
//				visits.add(chosenPeriod);
//
//				String makeAnother;
//				System.out.println("Would you like to make another visit? (Please answer yes or no)");
//				while(true) {
//					String choice;
//					int c;
//					System.out.println("\nWould you like record another visit? \n 1: Yes \n 2: No");
//					while ((choice = in.readLine()) == null && choice.length() == 0) ;
//					try {
//						c = Integer.parseInt(choice);
//					} catch (Exception e) {
//						continue;
//					}
//					if (c < 1 | c > 5) {
//						continue;
//					}
//					// user chooses to make another visit
//					if (c == 1) {
//						break;
//					}
//					// user does not want to make another visit
//					else if (c == 2) {
//						readyForCheckout = true;
//						break;
//					}
//				}
//			}
//
//			// Display all visits and ask if they are done recording visits
//			System.out.println("\nList of made visits:\nthid\t\tpid\t\tfrom_date\t\tto_date");
//			for(int i = 0; i < visits.size(); i+=2){
//				String reservation = "";
//				reservation += visits.get(i).get(0) + "\t\t";
//				reservation += visits.get(i).get(1) + "\t\t";
//				reservation += visits.get(i).get(2);
//				System.out.println(reservation);
//			}
//			while(true) {
//				String choice;
//				int c;
//				System.out.println("\nPlease double check your recorded stays \n 1: Finish \n 2: Quit");
//				while ((choice = in.readLine()) == null && choice.length() == 0);
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
//					return;
//				}
//			}
//
//
			for(int i = 0; i < visits.size(); i+=1){
//				for(int j = 0; j < visits.get(i).size(); j++){
				thid = visits.get(i).get(0);
				chosenPeriod = chosenPeriods.get(i);
				fromDate = visits.get(i).get(2);
				toDate = visits.get(i).get(3);
				addStay(thid, chosenPeriod, fromDate, toDate, con.stmt);
				//}
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
	public String suggestTH(List<List<String>> reservations, Connector con) {
		Visit visit = new Visit();
		Set<String> ths = new HashSet<String>();
		for(int j = 0; j < reservations.size(); j+=1) {
			ths.add(reservations.get(j).get(0));
		}
		String output = "";
		for(String th: ths){
			output += "<caption> Based on your reservation for " + th + ", here are some other THs we would suggest</caption>";
			output += "<table>";
			//System.out.println("\nBased on your reservation for "+ th + ",here are some other THs we would suggest");
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

	private void addStay(String thid, List<String> chosenPeriod, String fromDate, String toDate, Statement stmt) {
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

	public void addVisit(String thid, Date givenFromDate, Date givenToDate, List<String> chosenPeriod, Statement stmt){
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

	public void addLeftoverPeriodBeforeStay(Date givenFromDate, Date periodFromDate, String thid, List<String> chosenPeriod, Statement stmt){
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

	public void addLeftoverPeriodAfterStay(Date givenToDate, Date periodToDate, String thid, List<String> chosenPeriod, Statement stmt) {
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

	public String getVisitedTHS(Statement stmt) {
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

	public boolean loginUser(BufferedReader in, Connector con){
		String username;
		String password;
		try {
			System.out.println("please enter login name");
			while ((username = in.readLine()) == null && username.length() == 0) ;
			System.out.println("please enter a password:");
			while ((password = in.readLine()) == null && password.length() == 0) ;
			Users user = new Users();
			if (user.getUser(username, password, con.stmt) != "") {
				_currentUser = username;
				System.out.println("Current user: " + username);
				return true;
			} else {
				System.out.println("Username or password does not exist");
				return false;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public void registerUser(BufferedReader in, Connector con){
		String username;
		String password;
		String fullname;
		String address;
		String phone_number;
		try {
			System.out.println("please choose login name");
			while ((username = in.readLine()) == null && username.length() == 0) ;
			System.out.println("please choose a password:");
			while ((password = in.readLine()) == null && password.length() == 0) ;
			System.out.println("please enter your full name:");
			while ((fullname = in.readLine()) == null && fullname.length() == 0) ;
			System.out.println("please enter your address:");
			while ((address = in.readLine()) == null && address.length() == 0) ;
			System.out.println("please enter your phone number:");
			while ((phone_number = in.readLine()) == null && phone_number.length() == 0) ;
			Users user = new Users();
			if (user.getUser(username, con.stmt) != "") {
				System.out.println("That username has already been used");
			} else {
				user.addUser(username, fullname, password, address, phone_number, con.stmt);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public boolean leaveFeedback(String th, String score, String shortText, Connector con) {

		try {

			int thid = Integer.parseInt(th);



			Feedback feedback = new Feedback();
			List<List<String>> alreadyHaveFeedback = feedback.getFeedback(th, _currentUser, con.stmt);
			Visit stay = new Visit();
			if (stay.getVisit(_currentUser, thid, con.stmt) != "") {
				//System.out.println("That username has already been used");
				feedback.addFeedback(th,_currentUser, shortText,  Integer.parseInt(score), con.stmt);
				if(alreadyHaveFeedback.size() > 0){
					//feedback.deleteFeedback(alreadyHaveFeedback.get(0).get(0), con.stmt);
					return false;
				}
				return true;
			} else {
				System.out.println("We have no record of that visit");
				return false;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean declareTHAsFavorite(String thid, Connector con){
		Favorites favorite = new Favorites();
		//if(favorite.getFavorites())
		favorite.addFavorite(Integer.parseInt(thid), _currentUser, con.stmt);
		return true;
	}

	public List<List<String>> makeReservation(List<List<String>> chosenPeriods,Connector con){
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

	private void addReservation(String thid, List<String> chosenPeriod, String fromDate, String toDate, Statement stmt) {
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
	public void addReservationAndRemoveAvailable(String thid, Date givenFromDate, Date givenToDate, List<String> chosenPeriod, Statement stmt){
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

	public void addLeftoverPeriodBeforeReservation(Date givenFromDate, Date periodFromDate, String thid, List<String> chosenPeriod, Statement stmt){
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

	public void addLeftoverPeriodAfterReservation(Date givenToDate, Date periodToDate, String thid, List<String> chosenPeriod, Statement stmt) {
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

	public Date convertToDate(String date, SimpleDateFormat formatter){
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

	public String getTHAvailableTimes(Connector con){
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

	public String getUsersReservations(Connector con){
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
