package cs5530;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class testdriver {

	private static String _currentUser;

	public static void displayMainMenu()
	{
		System.out.println("       Welcome to Uotel     ");
		System.out.println("1. Login:");
		System.out.println("2. Register:");
		System.out.println("3. exit:");
		System.out.println("please enter your choice:");
	}

	public static void displayUserMenu(){
		System.out.println("       Welcome: " + _currentUser);
		System.out.println("1. Make a reservation:");
		System.out.println("2. Leave feedback on your stay:");
		System.out.println("3. Declare TH as your favorite TH:");
		System.out.println("4. History of your stays:");
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
					loginUser(in, con);
					userMenu(in, con);
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
					reservationMenu(in, con);
				}
				// Leave feedback on stay
				else if (c == 2) {
					leaveFeedback(in, con);
				}
				// Declare TH as your favorite
				else if (c == 3) {
					declareTHAsFavorite(in, con);
				}
				// History of stays
				else if (c == 4) {
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



	public static void reservationMenu(BufferedReader in, Connector con) {
		try{

			String choice;
			int c = 0;

			while(true) {
				displayReservationMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0) ;
				try {
					c = Integer.parseInt(choice);
				} catch (Exception e) {
					continue;
				}
				if (c < 1 | c > 5)
					continue;
				// List all THs and their periods available
				if (c == 1) {
					makeReservation(in, con);
					reservationMenu(in, con);
				}
				// List a given TH and its available time periods
				else if (c == 2) {
					registerUser(in, con);
				}
				else {
					System.out.println("EoM");
					con.stmt.close();

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

	private static void leaveFeedback(BufferedReader in, Connector con) {
		String th;
		String score;
		String shortText;
		int thid;
		try {
			System.out.println("please choose a TH to leave feedback on");
			while ((th = in.readLine()) == null && th.length() == 0) ;
			System.out.println("please give a score of your stay (1-10)");
			while ((score = in.readLine()) == null && score.length() == 0) ;
			System.out.println("please write a short summary of your stay (optional)");
			while ((shortText = in.readLine()) == null);

			thid = Integer.parseInt(th);

			Feedback feedback = new Feedback();
			Visit stay = new Visit();
			if (stay.getVisit(_currentUser, thid, con.stmt) != "") {
				System.out.println("That username has already been used");
			} else {
				System.out.println("That username has already been used");
				user.addUser(username, fullname, password, address, phone_number, con.stmt);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void loginUser(BufferedReader in, Connector con){
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
			} else {
				System.out.println("Username or password does not exist");
			}
		}
		catch (Exception e){
			e.printStackTrace();
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

	public static void makeReservation(BufferedReader in, Connector con){
		String chosenTH;
		String chosenPeriod;
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

}
