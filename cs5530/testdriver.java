package cs5530;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

public class testdriver {

	private static String _currentUser;

	public static void displayMainMenu()
	{
		System.out.println("\n       Welcome to Uotel     ");
		System.out.println("1. Login:");
		System.out.println("2. Register:");
		System.out.println("3. exit:");
		System.out.println("please enter your choice:");
	}

	public static void displayUserMenu(){
		System.out.println("\n       Welcome: " + _currentUser);
		System.out.println("1. Make a reservation:");
		System.out.println("2. Leave feedback on your stay:");
		System.out.println("3. Declare TH as your favorite TH:");
		System.out.println("4. Record your stay:");
		System.out.println("5. logout:");
		System.out.println("please enter your choice:");
	}

	public static void displayReservationMenu(){
		System.out.println("       Welcome: " + _currentUser);
		System.out.println("1. List all TH's and their available times:");
		System.out.println("2. List a given TH and it's available times:");
		System.out.println("3. Choose a TH and an available time:");
		System.out.println("4. Check reviews of TH:");
		System.out.println("5. back:");
		System.out.println("please enter your choice:");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
			Connector con= new Connector();
			Order order= new Order();
			String choice;
			int c = 0;
			_currentUser = "";

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				displayMainMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0) ;
				try {
					c = Integer.parseInt(choice);
				} catch (Exception e) {
					continue;
				}
				if (c < 1 | c > 3)
					continue;
				// Log into user
				if (c == 1) {
					if(loginUser(in, con)){
						userMenu(in, con);
					}

				}
				// Register user
				else if (c == 2) {
					registerUser(in, con);
				}
				else {
					System.out.println("EoM");
					con.stmt.close();

					break;
				}
			}
			con.closeConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
	}

	public static void userMenu(BufferedReader in, Connector con){
		try{

			String choice;
			int c = 0;

			while(true) {
				displayUserMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0) ;
				try {
					c = Integer.parseInt(choice);
				} catch (Exception e) {
					continue;
				}
				if (c < 1 | c > 5)
					continue;
				// User makes a reservation
				if (c == 1) {
					makeReservation(in, con);
					//reservationMenu(in, con);
				}
				// Leave feedback on stay
				else if (c == 2) {
					leaveFeedback(in, con);
				}
				// Declare TH as your favorite
				else if (c == 3) {
					String thid;
					printOutVisitedTHS(con.stmt);
					System.out.println("Please enter the TH you would like to favorite");
					while ((thid = in.readLine()) == null && thid.length() == 0) ;
					if(declareTHAsFavorite(thid, in, con)){
						System.out.println("Th: " + thid + " has successfully been favorited");
					}
				}
				// History of stays
				else if (c == 4) {
					recordVisit(in, con);
					//staysHistory(in, con);
				}
				else {
					System.out.println("EoM");
					//con.stmt.close();

					break;
				}
			}
//			con.closeConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
	}

	private static void recordVisit(BufferedReader in, Connector con) {
		printUsersReservations(in, con);
	}

	private static void printOutVisitedTHS(Statement stmt) {
		String sql="select DISTINCT(th.hid), category, hname\n" +
				"from Visit v, TH th\n" +
				"where v.hid = th.hid\n" +
				"and v.login = '" + _currentUser + "'";
		String instance;
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			String table_header = "hid" + "\t\t" + "category" + "\t\t" + "hname";
			System.out.println(table_header);
			while (rs.next())
			{
				instance = "";
				instance += rs.getString("hid");
				//instance += "\t\t" + rs.getString("login");
				instance += "\t\t" + rs.getString("category");
				instance += "\t\t" + rs.getString("hname");
				System.out.println(instance);

			}

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
	}




	public static boolean loginUser(BufferedReader in, Connector con){
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

	public static void registerUser(BufferedReader in, Connector con){
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

	private static void leaveFeedback(BufferedReader in, Connector con) {
		String th;
		String score;
		String shortText;
		int thid;
		try {
			printOutVisitedTHS(con.stmt);
			System.out.println("please choose a TH to leave feedback on");
			while ((th = in.readLine()) == null && th.length() == 0) ;
			System.out.println("please give a score of your stay (1-10)");
			while ((score = in.readLine()) == null && score.length() == 0) ;
			System.out.println("please write a short summary of your stay (optional)");
			while ((shortText = in.readLine()) == null);

			thid = Integer.parseInt(th);



			Feedback feedback = new Feedback();
			List<List<String>> alreadyHaveFeedback = feedback.getFeedback(th, _currentUser, con.stmt);
			Visit stay = new Visit();
			if (stay.getVisit(_currentUser, thid, con.stmt) != "") {
				//System.out.println("That username has already been used");
				feedback.addFeedback(th,_currentUser, shortText,  Integer.parseInt(score), con.stmt);
				if(alreadyHaveFeedback != null){
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

	public static boolean declareTHAsFavorite(String thid, BufferedReader in, Connector con){
		Favorites favorite = new Favorites();
		favorite.addFavorite(Integer.parseInt(thid), _currentUser, con.stmt);
		return true;
	}

	public static void makeReservation(BufferedReader in, Connector con){
		String thid = "";
		String pid = "";
		String fromDate = "";
		String toDate = "";
		List<List<String>> reservations = new ArrayList<List<String>>();
		List<String> chosenPeriod = new ArrayList<String>();
		boolean readyForCheckout = false;
		boolean makeAnotherReservation = false;
		try {
			while(!(readyForCheckout)) {
				List<String> reservation = new ArrayList<String>();
				printTHAvailableTimes(in, con);
				System.out.println("please choose the TH you want to Reserve (hid):");
				while ((thid = in.readLine()) == null && thid.length() == 0) ;
				reservation.add(thid);

				System.out.println("please choose a period of time you would like to reserve (pid):");
				while ((pid = in.readLine()) == null && pid.length() == 0) ;
				//reservation.add(pid);
				Period period = new Period();
				chosenPeriod = period.getPeriod(pid, con.stmt).get(0);
				System.out.println("Your Chosen Period for TH: " + thid);
				System.out.println("\t\t" + chosenPeriod.get(0) + "\t\t" + chosenPeriod.get(1) + "\t\t" + chosenPeriod.get(2));
				//getTHAndPeriod(thid, pid, in, con);

				System.out.println("please choose a start date for your reservation (date format: mm/dd/yyyy ex: 01/23/1994):");
				while ((fromDate = in.readLine()) == null && fromDate.length() == 0) ;
				reservation.add(fromDate);

				System.out.println("please choose an end date for your reservation (date format: mm/dd/yyyy ex: 01/23/1994):");
				while ((toDate = in.readLine()) == null && toDate.length() == 0) ;
				reservation.add(toDate);

				reservations.add(reservation);
				reservations.add(chosenPeriod);

				String makeAnother;
				System.out.println("Would you like to make another reservation? (Please answer yes or no)");
				while(true) {
					String choice;
					int c;
					System.out.println("\nWould you like to Checkout? \n 1: no \n 2: yes");
					while ((choice = in.readLine()) == null && choice.length() == 0) ;
					try {
						c = Integer.parseInt(choice);
					} catch (Exception e) {
						continue;
					}
					if (c < 1 | c > 5) {
						continue;
					}
					// user chooses to make another reservation
					if (c == 1) {
						break;
					}
					// user does not want to make another reservation
					else if (c == 2) {
						readyForCheckout = true;
						break;
					}
				}

			}

			// Display all reservations and ask if they would like to checkout
			System.out.println("\nList of made reservations:\nthid\t\tpid\t\tfrom_date\t\tto_date");
			for(int i = 0; i < reservations.size(); i+=2){
				String reservation = "";
				reservation += reservations.get(i).get(0) + "\t\t";
				reservation += reservations.get(i).get(1) + "\t\t";
				reservation += reservations.get(i).get(2);
				System.out.println(reservation);
			}
			while(true) {
				String choice;
				int c;
				System.out.println("\nWould you like to Checkout? \n 1: Checkout \n 2: quit");
				while ((choice = in.readLine()) == null && choice.length() == 0) ;
				try {
					c = Integer.parseInt(choice);
				} catch (Exception e) {
					continue;
				}
				if (c < 1 | c > 5) {
					continue;
				}
				// User makes a reservation
				if (c == 1) {
					break;
				}
				// Leave feedback on stay
				else if (c == 2) {
					return;
				}
			}


			for(int i = 0; i < reservations.size(); i+=2){
//				for(int j = 0; j < reservations.get(i).size(); j++){
					thid = reservations.get(i).get(0);
					chosenPeriod = reservations.get(i+1);
					fromDate = reservations.get(i).get(1);
					toDate = reservations.get(i).get(2);
					addReservation(thid, chosenPeriod, fromDate, toDate, con.stmt);
				//}
			}

//			Reserve reservation = new Reserve();
//			if (reservation.getReserve(Integer.parseInt(thid), Integer.parseInt(pid), con.stmt) != "") {
//				System.out.println("This TH has already been reserved for that time period");
//			} else {
//				reservation.addReservation(_currentUser, Integer.parseInt(thid), Integer.parseInt(pid), con.stmt);
//			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void addReservation(String thid, List<String> chosenPeriod, String fromDate, String toDate, Statement stmt) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Period period = new Period();
		Available available = new Available();
		List<List<String>> firstPeriod = new ArrayList<List<String>>();
		List<List<String>> secondPeriod = new ArrayList<List<String>>();
		List<List<String>> thirdPeriod = new ArrayList<List<String>>();
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
	public static void addReservationAndRemoveAvailable(String thid, Date givenFromDate, Date givenToDate, List<String> chosenPeriod, Statement stmt){
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

	public static void addLeftoverPeriodBeforeReservation(Date givenFromDate, Date periodFromDate, String thid, List<String> chosenPeriod, Statement stmt){
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

	public static void addLeftoverPeriodAfterReservation(Date givenToDate, Date periodToDate, String thid, List<String> chosenPeriod, Statement stmt) {
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


	public static Date convertToDate(String date, SimpleDateFormat formatter){
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




	public static void printTHAvailableTimes(BufferedReader in, Connector con){
		String sql="select a.hid, p.pid, p.from_date, p.to_date\n" +
				"from Available a, Period p\n" +
				"where a.pid = p.pid\n" +
				"GROUP BY a.hid, p.pid, p.from_date, p.to_date";
		String instance;
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=con.stmt.executeQuery(sql);
			int count = 0;
			String table_header = "hid" + "\t\t" + "pid" + "\t\t" + "from_date" + "\t\t" + "to_date" + "\t\t";
			System.out.println(table_header);
			while (rs.next())
			{
				instance = "";
				instance += rs.getString("hid");
				instance += "\t\t" + rs.getString("pid");
				instance += "\t\t" + rs.getString("from_date");
				instance += "\t\t" + rs.getString("to_date");
				System.out.println(instance);

			}

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
	}

	public static void printUsersReservations(BufferedReader in, Connector con){
		String sql="select r.login, r.hid, p.pid, p.from_date, p.to_date\n" +
				"from Reserve r, Period p\n" +
				"where r.pid = p.pid.login\n" +
				"GROUP BY u.login, r.hid, p.pid, p.from_date, p.to_date";
		String instance;
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=con.stmt.executeQuery(sql);
			int count = 0;
			String table_header = "login" + "\t\t" + "hid" + "\t\t" + "pid" + "\t\t" + "from_date" + "\t\t" + "to_date" + "\t\t";
			System.out.println(table_header);
			while (rs.next())
			{
				instance = "";
				instance += rs.getString("login");
				instance += "\t\t" + rs.getString("hid");
				instance += "\t\t" + rs.getString("pid");
				instance += "\t\t" + rs.getString("from_date");
				instance += "\t\t" + rs.getString("to_date");
				System.out.println(instance);

			}

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
	}

	private static void reservePeriod(List<String> period, BufferedReader in, Connector con) {

	}

	private static void listAllTHAndPeriods(BufferedReader in, Connector con) {

	}

	private static void listPeriodsOfTH(BufferedReader in, Connector con) {

	}

	private static void THReviews(BufferedReader in, Connector con) {

	}

}
