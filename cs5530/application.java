package cs5530;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import sun.text.resources.et.FormatData_et_EE;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class application {

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
		System.out.println("7. trust recordings");
		System.out.println("8. useful feedbacks");
		System.out.println("9. two degrees of seperation");
		System.out.println("10. statistics");
		System.out.println("11. user awards (Admin Only)");
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

						System.out.println("please enter TH id:");
						while ((hid = in.readLine()) == null && hid.length() == 0);
						if (th.getTH(login, hid, con.stmt).isEmpty()) {
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
			System.out.println("login\t\tusefulness score");
			while (rs.next())
			{
				output+=rs.getString("login")+"\t\t"
						+ rs.getString("usefulness_score")+"\n";
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
}
