package cs5530;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class application {

	public static void displayMenu()
	{
		 System.out.println("       Welcome to Uotel     ");
    	 System.out.println("1. Login:");
    	 System.out.println("2. Register:");
    	 System.out.println("3. exit:");
    	 System.out.println("4. Add User:");
		 System.out.println("5. Get User:");
    	 System.out.println("please enter your choice:");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Example for cs5530");
		Connector con=null;
		String choice;
        String username;
        String password;
        String sql=null;
        String login;
        String name;
        String address;
        String phone_number;
        String user_type;

        int c=0;
         try
		 {
			//remember to replace the password
			 	 con= new Connector();
	             System.out.println ("Database connection established");
	         
	             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	             
	             while(true)
	             {
	            	 displayMenu();
	            	 while ((choice = in.readLine()) == null && choice.length() == 0);
	            	 try{
	            		 c = Integer.parseInt(choice);
	            	 }catch (Exception e)
	            	 {
	            		 continue;
	            	 }
					 switch (c) {
						 case 1: //login
						 	System.out.println("please enter login name");
						 	while ((username = in.readLine()) == null && username.length() == 0);
						 	System.out.println("please enter a password:");
						 	while ((password = in.readLine()) == null && password.length() == 0);
						 	Course course=new Course();
						 	System.out.println(course.getCourse(username, password, con.stmt));
						 	break;
						 case 2: //Register
							 System.out.println("Register");
							 while ((sql = in.readLine()) == null && sql.length() == 0)
								 System.out.println(sql);
							 ResultSet rs=con.stmt.executeQuery(sql);
							 ResultSetMetaData rsmd = rs.getMetaData();
							 int numCols = rsmd.getColumnCount();
							 while (rs.next())
							 {
								 //System.out.print("cname:");
								 for (int i=1; i<=numCols;i++)
									 System.out.print(rs.getString(i)+"  ");
								 System.out.println("");
							 }
							 System.out.println(" ");
							 rs.close();
						 	break;
						 case 3:
							 System.out.println("EoM");
							 con.stmt.close();
							 break;
						 case 4:
							 System.out.println("please enter a login name");
							 while ((login = in.readLine()) == null && login.length() == 0);
							 System.out.println("please enter a name:");
							 while ((name = in.readLine()) == null && name.length() == 0);
							 System.out.println("please enter a password:");
							 while ((password = in.readLine()) == null && password.length() == 0);
							 System.out.println("please enter a address:");
							 while ((address = in.readLine()) == null && address.length() == 0);
							 System.out.println("please enter a phone number:");
							 while ((phone_number = in.readLine()) == null && phone_number.length() == 0);
							 System.out.println("please enter a user type (0 for standard and 1 for admin):");
							 while ((user_type = in.readLine()) == null && user_type.length() == 0);
							 //System.out.println(users.addUser(login, name, password, address, phone_number, user_type, con.stmt));
						 	break;
						 case 5:
							 System.out.println("please enter a login name");
							 while ((login = in.readLine()) == null && login.length() == 0);
							 Users users = new Users();
							 System.out.println(users.getUser(login, con.stmt));
						 	break;
						 default: continue;
					 }
	             }
		 }
         catch (Exception e)
         {
        	 e.printStackTrace();
        	 System.err.println ("Either connection error or query execution error!");
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
}
