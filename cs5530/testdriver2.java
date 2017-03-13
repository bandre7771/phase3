package cs5530;


import java.lang.*;
import java.sql.*;
import java.io.*;

public class testdriver2 {

	public static void displayMenu()
	{
		 System.out.println("       Welcome to Uotel     ");
    	 System.out.println("1. Login:");
    	 System.out.println("2. Register:");
    	 System.out.println("3. exit:");
    	 System.out.println("pleasse enter your choice:");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Example for cs5530");
		Connector con=null;
		String choice;
        String username;
        String password;
        String sql=null;
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
	            	 if (c<1 | c>3)
	            		 continue;
	            	 // Enter userm
					 if (c==1)
	            	 {
	            		 System.out.println("please enter login name");
	            		 while ((username = in.readLine()) == null && username.length() == 0);
	            		 System.out.println("please enter a password:");
	            		 while ((password = in.readLine()) == null && password.length() == 0);
	            		 Course course=new Course();
	            		 System.out.println(course.getCourse(username, password, con.stmt));
	            	 }
					 // Enter your own query
	            	 else if (c==2)
	            	 {	 
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
	            	 }
					 // Exit
	            	 else
	            	 {   
	            		 System.out.println("EoM_______");
	            		 con.stmt.close(); 
	        
	            		 break;
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
