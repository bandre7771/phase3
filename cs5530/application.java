package cs5530;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import sun.invoke.empty.Empty;
import sun.reflect.annotation.ExceptionProxy;
import sun.text.resources.et.FormatData_et_EE;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class application {
	///Display Menus
	public static void displayLoginMenu()
	{
		 System.out.println("       Welcome to Uotel     ");
    	 System.out.println("1. Login:");
    	 System.out.println("2. Register:");
         System.out.println( "please enter your choice:");
    }

	public static void displayWelcomeToUotelMenu()
	{
		System.out.println("       Welcome to Uotel     ");
		System.out.println("1. reserve:");
		System.out.println("2. th:");
		System.out.println("3. stays:");
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

	public static void displayTHMenu()
	{
		System.out.println("       TH     ");
		System.out.println("1. add TH:");
		System.out.println("2. update TH:");
		System.out.println("3. browse TH:");
		System.out.println("4. exit:");
		System.out.println( "please enter your choice:");
	}

	public static void displayTHBrowsingMenu()
	{
		System.out.println("       TH Browsing     ");
		System.out.println("1. search & sort by:");
		System.out.println("2. exit:");
		System.out.println( "please enter your choice:");
	}

	public static void displaySearchByTHMenu()
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

	public static void displayusefulFeedbacksMenu(Statement stmt)
	{
		System.out.println("       Useful Feedbacks     ");
		TH th = new TH();
		System.out.println(th.getAllTH(stmt));
		System.out.println("please enter a TH id (hid):");
	}

	public static void displayTwoDegreesOfSeparationMenu(Statement stmt)
	{
		System.out.println("       Two Degrees of Separation     ");
		Users users = new Users();
		System.out.println(users.getAllUsers(stmt));
	}

	public static  void displayStaysMenu(String login, Statement stmt)
	{
		System.out.println("       Stays     ");
		Reserve reserve = new Reserve();
		System.out.println(reserve.getReserve(login, stmt));
		System.out.println("please enter the TH id (hid)");
	}

	public static void displayUsefulnessRatingsMenu(String login, Statement stmt)
	{
		System.out.println("       Usefulness Recording     ");
		Feedback feedback = new Feedback();
		System.out.println(feedback.getAllOtherUserFeedback(login, stmt));
		System.out.println( "please enter the Feedback ID (fid):");
	}

	public static void displayTrustedRecordingsMenu(String login, Statement stmt)
	{
		System.out.println("       Trusted Rercordings     ");
		Users users = new Users();
		System.out.println(users.getAllOtherUsers(login, stmt));
		System.out.println( "please enter the User login:");
	}

	public static void displayStatisticsMenu()
	{
		System.out.println("       Statistics     ");
		System.out.println("1. Show top most popular TH's for each category:");
		System.out.println("2. Show top most popular TH's for each category:");
		System.out.println("3. Show top most popular TH's for each category:");
		System.out.println("4. exit");
	}

	public static void displayUserAdminMenu()
	{
		System.out.println("       User Awards     ");
		System.out.println("1. show top most trusted users:");
		System.out.println("2. show top most useful users:");
		System.out.println("3. exit:");
		System.out.println("please enter a choice:");
	}

	public static void main(String[] args)
	{
		System.out.println("5530u16");
		Connector con=null;
        String sql=null;
         try
		 {
		 		 con = new Connector();
	             System.out.println ("Database connection established");
	             loginMenu(con);
		 }
         catch (Exception e)
         {
        	 e.printStackTrace();
        	 System.err.println ("Connection error");
         }
         finally
         {
        	 if (con != null)
        	 {
        		 try
        		 {
        			 con.closeConnection();
        			 System.out.println ("Database connection terminated");
        		 }

        		 catch (Exception e) { /* ignore close errors */ }
        	 }
         }

	}

	///Menus
	public static void loginMenu(Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice;
		String login;
		boolean exit = false;
		int c=0;

		try
		{
			while(!exit)
			{
				displayLoginMenu();
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
					case 1: //login
                        System.out.println("please enter login");
                        while ((login = in.readLine()) == null && login.length() == 0);
                        welcomeToUotelMenu(login, con);
						break;
					case 2: //Register
//						welcomeToUotelMenu(con);
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

	public static void welcomeToUotelMenu(String login, Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayWelcomeToUotelMenu();
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

						break;
                    case 2:
                        tHMenu(login, con);
                        break;
					case 3:
						staysMenu(login, con);
						break;
					case 6:
						usefulnessRatingsMenu(login, con);
						break;
					case 7:
						trustRecordingsMenu(login, con);
						break;
					case 8:
						usefulFeedbacksMenu(login, con);
						break;
					case 9:
						twoDegreesOfSeparationMenu(login, con);
						break;
					case 10:
						statisticsMenu(login, con);
						break;
					case 11:
						userAwardsMenu(login, con);
						break;
					case 12:
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

	//TODO: a user should be able to add/update a TH's periods of availability and keywords.
    public static void tHMenu(String login, Connector con)
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        String hname;
        String category;
        String url;
        String phone_number;
        String year;
        String picture;
		String hid;
		String address;
        TH th;
        int c=0;
        try
        {
			boolean exit = false;
            while(!exit)
            {
                displayTHMenu();
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
                    case 1: // Add TH
                        th = new TH();
						System.out.println("       Add TH     ");

						System.out.println("please enter TH name:");
                        while ((hname = in.readLine()) == null && hname.length() == 0);

                        System.out.println("please enter category name:");
                        while ((category = in.readLine()) == null && category.length() == 0);

						System.out.println("please enter city:");
						while ((address = in.readLine()) == null && address.length() == 0) ;

						System.out.println("please enter phone number:");
                        while ((phone_number =  in.readLine()) == null && phone_number.length() == 0);

                        System.out.println("please enter year built:");
                        while ((year =  in.readLine()) == null && year.length() == 0);

                        System.out.println("please add picture:");
                        while ((picture =  in.readLine()) == null && picture.length() == 0);

                        System.out.println("please enter url:");
                        while ((url =  in.readLine()) == null && url.length() == 0);

                        th.addTH(category,login,hname,address,url,phone_number,year,picture,con.stmt);
                        break;
                    case 2:
						th = new TH();
						System.out.println("       Update TH     ");
						System.out.println(th.getTHForLogin(login, con.stmt));
						System.out.println("please enter TH id (hid):");
						while ((hid = in.readLine()) == null && hid.length() == 0);
						if (th.getTH(login, hid, con.stmt).isEmpty()) {
							System.out.println("You do not own the TH with id:"+hid);
							break;
						}
						System.out.println("please enter TH name:");
						while ((hname = in.readLine()) == null && hname.length() == 0);

						System.out.println("please enter category name:");
						while ((category = in.readLine()) == null && category.length() == 0);

						System.out.println("please enter address:");
						while ((address = in.readLine()) == null && address.length() == 0) ;

						System.out.println("please enter phone number:");
						while ((phone_number =  in.readLine()) == null && phone_number.length() == 0);

						System.out.println("please enter year built:");
						while ((year =  in.readLine()) == null && year.length() == 0);

						System.out.println("please add picture:");
						while ((picture =  in.readLine()) == null && picture.length() == 0);

						System.out.println("please enter url:");
						while ((url =  in.readLine()) == null && url.length() == 0);

						th.updateTH(hid, category, login, hname, address, url, phone_number, year, picture, con.stmt);
                        break;
					case 3: //browse TH
						tHBrowsingMenu(login, con);
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

	public static void staysMenu(String login, Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice;
		String hid;
		String pid;
		String cost;
		ArrayList<String> pids = new ArrayList<String>();
		ArrayList<String> hids = new ArrayList<String>();
		ArrayList<String> costs = new ArrayList<String>();
		Visit visit;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayStaysMenu(login, con.stmt);
				while ((hid = in.readLine()) == null && hid.length() == 0);
				System.out.println("please enter the period id (pid)");
				while ((pid = in.readLine()) == null && pid.length() == 0);
				System.out.println("please enter the cost of the stay");
				while ((cost = in.readLine()) == null && cost.length() == 0);
				hids.add(hid);
				pids.add(pid);
				costs.add(cost);
				System.out.println("1. record another stay");
				System.out.println("2. exit:");
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
						break;
					case 2:
						exit = false;
						while(!exit) {
							System.out.println("You have recorded stays for the following:");
							System.out.println("hid\t\tpid\t\tcost");
							for (int i = 0; i < hids.size(); i++)
							{ //print out all the stays recorded
								System.out.print(hids.get(i)+"\t\t");
								System.out.print(pids.get(i)+"\t\t");
								System.out.println("$"+costs.get(i));
							}
							System.out.println("1. confirm and exit:");
							System.out.println("2. cancel and exit:");
							System.out.println("please enter your choice:");
							String subChoice;
							while ((subChoice = in.readLine()) == null && subChoice.length() == 0);
							try
							{
								c = Integer.parseInt(subChoice);
							}
							catch (Exception e)
							{
								continue;
							}
							switch (c) {
								case 1: //save and exit
									for (int i = 0; i < hids.size(); i++)
									{ //all the stays recorded
										visit = new Visit();
										hid = hids.get(i);
										pid = pids.get(i);
										cost = costs.get(i);
										visit.addVisit(login, hid, pid, cost, con.stmt);
									}
									exit = true;
									break;
								case 2: //cancel and exit
									exit = true;
									break;
								default:
									continue;
							}
						}
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

	public static void usefulnessRatingsMenu(String login, Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice;
		String fid;
		String rating;
		Rates rates;
		int r;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayUsefulnessRatingsMenu(login, con.stmt);
				while ((fid = in.readLine()) == null && fid.length() == 0);
				System.out.println("0. useless");
				System.out.println("1. useful");
				System.out.println("2. very useful");
				System.out.println("please enter your choice");

				while ((rating = in.readLine()) == null && rating.length() == 0);
				try
				{
					r = Integer.parseInt(rating);
				}
				catch (Exception e)
				{
					continue;
				}
				if (r == 0 | r == 1 | r == 2)
				{
					rates = new Rates();
					rates.addRating(fid, login, rating, con.stmt);
					exit = true;
				}
				else
				{
					System.out.println("your choice is out of bounds.");
				}
			}
		}
		catch (Exception e)
		{
			System.err.println ("Query Error");
		}
	}

	public static void trustRecordingsMenu(String login, Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String login2;
		String isTrusted;
		Trust trust;
		int t;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayTrustedRecordingsMenu(login, con.stmt);
				while ((login2 = in.readLine()) == null && login2.length() == 0);
				System.out.println("0. not trusted");
				System.out.println("1. trusted");
				System.out.println("please enter your choice");
				while ((isTrusted = in.readLine()) == null && isTrusted.length() == 0);
				try
				{
					t = Integer.parseInt(isTrusted);
				}
				catch (Exception e)
				{
					continue;
				}
				if (t == 0 | t == 1)
				{
					trust = new Trust();
					trust.addTrust(login, login2, isTrusted, con.stmt);
					exit = true;
				}
				else
				{
					System.out.println("your choice is out of bounds.");
				}
			}
		}
		catch (Exception e)
		{
			System.err.println ("Query Error");
		}
	}

	//TODO: implement administrator only privilege's
	public static void userAwardsMenu(String login, Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice;
		String m; // amount displayed
		Trust trust;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayUserAdminMenu();
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
						trust = new Trust();
						System.out.println("please enter the amount of users you’d like to display:");
						while ((m = in.readLine()) == null && m.length() == 0);
						System.out.println("Top most trusted users:");
						System.out.println(trust.getMMostTrusted(m, con.stmt));
						break;
					case 2:
						System.out.println("please enter the amount of users you’d like to display:");
						while ((m = in.readLine()) == null && m.length() == 0);
						System.out.println("Top most useful users:");
						System.out.println(mostUsefulUsers(m, con.stmt));
						break;
					case 3:
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

	public static String mostUsefulUsers(String m, Statement stmt)
	{
		String sql="SELECT  f.login, avg(r.rating) as usefulness_score\n" +
				"FROM Feedback f, Rates r\n" +
				"WHERE f.fid = r.fid\n" +
				"GROUP BY f.login\n" +
				"ORDER BY usefulness_score DESC LIMIT "+m;
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
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

	public static void usefulFeedbacksMenu(String login, Connector con)
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

	public static String mostUsefulFeedbacksForTh(String n, String hid, Statement stmt)
	{
		String sql="SELECT f.fid, f.hid, f.login, f.text, f.score, f.fbdate, AVG(r.rating) as usefulness\n" +
				"FROM Feedback f, Rates r\n" +
				"WHERE f.fid = r.fid\n" +
				"  AND hid = "+hid+"\n" +
				"GROUP BY f.fid\n" +
				"ORDER BY usefulness DESC LIMIT "+n;
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="fid: "+rs.getString("fid")+"   "
						+ "hid: "+rs.getString("hid")+"   "
						+ "login: "+rs.getString("login")+"   "
						+ "text: "+rs.getString("text")+"   "
						+ "score: "+rs.getString("score")+"   "
						+ "fbdate: "+rs.getString("fbdate")+"   "
						+ "usefulness: "+rs.getString("usefulness")+"\n";
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

	public static void statisticsMenu(String login, Connector con)
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
						System.out.println(mostPopularThsPerCategory(m, con.stmt));
						break;
					case 2:
						System.out.println("please enter the amount of TH’s you’d like to display for each category:");
						while ((m = in.readLine()) == null && m.length() == 0);
						System.out.println(mostExpensiveThsPerCategory(m, con.stmt));
						break;
					case 3:
						System.out.println("please enter the amount of TH’s you’d like to display for each category:");
						while ((m = in.readLine()) == null && m.length() == 0);
						System.out.println(mostHighlyRatedThsPerCategory(m, con.stmt));
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

	public static String mostPopularThsPerCategory(String m, Statement stmt)
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
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "category: "+rs.getString("category")+"   "
						+ "hid: "+rs.getString("hid")+"   "
						+ "hname: "+rs.getString("hname")+"   "
						+ "address: "+rs.getString("address")+"   "
						+ "login: "+rs.getString("login")+"   "
						+ "phone number: "+rs.getString("phone_number")+"   "
						+ "year built: "+rs.getString("year_built")+"   "
						+ "url: " +rs.getString("url")+"   "
						+ "picture: " +rs.getString("picture")+"   "
						+ "visit count: " +rs.getString("visit_count")+"\n";
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

	public static String mostExpensiveThsPerCategory(String m, Statement stmt)
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
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "category: "+rs.getString("category")+"   "
						+ "hid: "+rs.getString("hid")+"   "
						+ "hname: "+rs.getString("hname")+"   "
						+ "address: "+rs.getString("address")+"   "
						+ "login: "+rs.getString("login")+"   "
						+ "phone number: "+rs.getString("phone_number")+"   "
						+ "year built: "+rs.getString("year_built")+"   "
						+ "url: " +rs.getString("url")+"   "
						+ "picture: " +rs.getString("picture")+"   "
						+ "average cost: " +rs.getString("average_cost")+"\n";
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

	public static String mostHighlyRatedThsPerCategory(String m, Statement stmt)
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
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			System.out.println("category\t\thid\t\thname\t\taddress\t\tlogin\t\tphone number\t\tyear built\t\turl\t\tpicture\t\taverage_score");
			while (rs.next())
			{
				output+= "category: "+rs.getString("category")+"\t\t"
						+ "hid: "+rs.getString("hid")+"\t\t"
						+ "hname: "+rs.getString("hname")+"\t\t"
						+ "address: "+rs.getString("address")+"\t\t"
						+ "login: "+rs.getString("login")+"\t\t"
						+ "phone number: "+rs.getString("phone_number")+"\t\t"
						+ "year built: "+rs.getString("year_built")+"\t\t"
						+ "url: " +rs.getString("url")+"\t\t"
						+ "picture: " +rs.getString("picture")+"\t\t"
						+ "average_score: " +rs.getString("average_score")+"\n";
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

	public static void tHBrowsingMenu(String login, Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displayTHBrowsingMenu();
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
						searchByTHMenu("#","","#","#", "#", con);
						break;
					case 2:
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

	public static void searchByTHMenu(String whereActive, String whereQuery, String orderPriceActive, String orderScoreActive, String onlyTrustedActive, Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice = "";
		String minPrice = "";
		String maxPrice = "";
		String address = "";
		String hname = "";
		String category = "";
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				displaySearchByTHMenu();
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
						completeTHSearchMenu("#","","#","#", "#", con);
						exit = true;
						break;
					case 2:
						System.out.println("please enter a min price:");
						minPrice = in.readLine();
						System.out.println("please enter a max price:");
						maxPrice = in.readLine();
						whereQuery += "(price >= "+minPrice+" AND price <= "+maxPrice+") ";
						completeTHSearchMenu("", whereQuery, orderPriceActive, orderScoreActive, onlyTrustedActive, con);
						exit = true;
						break;
					case 3:
						System.out.println("please enter a keyword for the address");
						address = in.readLine();
						whereQuery += "address LIKE '%"+address+"%' ";
						completeTHSearchMenu("", whereQuery, orderPriceActive, orderScoreActive, onlyTrustedActive, con);
						exit = true;
						break;
					case 4:
						System.out.println("please enter a keyword for the name");
						hname = in.readLine();
						whereQuery += "hname LIKE '%"+hname+"%' ";
						completeTHSearchMenu("", whereQuery, orderPriceActive, orderScoreActive, onlyTrustedActive, con);
						exit = true;
						break;
					case 5:
						System.out.println("please enter a keyword for a category");
						category = in.readLine();
						whereQuery += "category LIKE '%"+category+"%' ";
						completeTHSearchMenu("", whereQuery, orderPriceActive, orderScoreActive, onlyTrustedActive, con);
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

	/// # means not Active and an empty string means it is active ("#"=false; ""=true)
	public static void completeTHSearchMenu(String whereActive, String whereQuery, String orderPriceActive, String orderScoreActive, String onlyTrustedActive, Connector con)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice;
		int c=0;
		try
		{
			boolean exit = false;
			while(!exit)
			{
				System.out.println("current search: "+whereQuery);
				System.out.println("1. complete current search:");
				System.out.println("2. add to current search with another search (OR):");
				System.out.println("3. add to current search with another search (AND):");
				System.out.println("4. complete and sort by price:");
				System.out.println("5. complete and sort by average score of feedbacks:");
				System.out.println("6. complete and sort by average score of only trusted feedbacks:");

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
						System.out.println(browsingTH(whereActive, whereQuery, orderPriceActive, orderScoreActive, onlyTrustedActive, con.stmt));
						exit = true;
						break;
					case 2:
						whereQuery += "OR ";
						searchByTHMenu(whereActive, whereQuery, orderPriceActive, orderScoreActive, onlyTrustedActive, con);
						exit = true;
						break;
					case 3:
						whereQuery += "AND ";
						searchByTHMenu(whereActive, whereQuery, orderPriceActive, orderScoreActive, onlyTrustedActive, con);
						exit = true;
						break;
					case 4:
						System.out.println(browsingTH(whereActive, whereQuery, "", orderScoreActive, onlyTrustedActive, con.stmt));
						exit = true;
						break;
					case 5:
						System.out.println(browsingTH(whereActive, whereQuery, orderPriceActive, "", onlyTrustedActive, con.stmt));
						exit = true;
						break;
					case 6:
						System.out.println(browsingTH(whereActive, whereQuery, orderPriceActive, "", "", con.stmt));
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

	public static String browsingTH(String whereActive, String whereQuery, String orderPriceActive, String orderScoreActive, String onlyTrustedActive, Statement stmt)
	{
		String semiColon = "";
		if(orderPriceActive.equals("#") && orderScoreActive.equals("#"))
		{
			semiColon = ";";
		}
		String sql="SELECT browsingTH1.hid, browsingTH1.hname, browsingTH1.category, browsingTH1.address, browsingTH1.login, AVG(browsingTH1.fbscore) AS average_fbscore, AVG(browsingTH1.price) average_price #START OF FULL OUTER JOINS long because mysql doesn't support it short hand\n" +
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
				"      "+onlyTrustedActive+"AND browsingTH.is_trusted = 1 #THIS IS WHERE THE TRUSTED STRING WILL GO _______________\n" +
				"     ) as browsingTH1 #END OF FULL OUTER JOINS long because mysql doesn't support it short hand\n" +
				whereActive+"WHERE "+whereQuery+"#THIS IS WHERE THE WHERE STRING WILL GO_________\n" +
				"GROUP BY browsingTH1.hid, browsingTH1.hname, browsingTH1.category, browsingTH1.address, browsingTH1.login"+semiColon+"\n" +
				orderPriceActive+"ORDER BY average_price DESC;\n" +
				orderScoreActive+"ORDER BY average_fbscore DESC;";
		String output="";
		ResultSet rs=null;
//		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "hid: "+rs.getString("hid")+"   "
						+ "hname: "+rs.getString("hname")+"   "
						+ "category: "+rs.getString("category")+"   "
						+ "address: "+rs.getString("address")+"   "
						+ "login: "+rs.getString("login")+"   "
						+ "average_fbscore: " +rs.getString("average_fbscore")+"   "
						+ "average_price: " +rs.getString("average_price")+"\n";
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

	public static void twoDegreesOfSeparationMenu(String login, Connector con)
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
			if(users.getUsers(login1,con.stmt).isEmpty())
			{
				throw new Exception("Invalid Login");
			}
			System.out.println("please enter the second login");
			while ((login2 = in.readLine()) == null && login2.length() == 0);
			if(users.getUsers(login2,con.stmt).isEmpty())
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
}
