package cs5530;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.ResultSet;
import java.sql.Statement;

public class Users {
		public Users()
		{}

		public boolean addUser(String login, String name, String password, String address, String phone_number, String user_type, Statement stmt)  {
			String sql="INSERT INTO Users (login, name, password, address, phone_number, user_type) VALUES"+
					"('"+login+"', '"+name+"', '"+password+"', '"+address+"', '"+phone_number+"', "+user_type+")";
			System.out.println("executing " + sql);
			try{
				stmt.executeUpdate(sql);
				System.out.println("login: "+login+" name: "+name+" address: "+address+" phone_number: "+phone_number+" user_type: "+user_type);
			}
			catch(Exception e)
			{
				System.out.println("cannot execute the query");
				return false;
			}
			return true;
		}

		public String getUser(String login, Statement stmt)
		{
			String sql="select * from Users where login = '"+login+"'";
			String output="";
			ResultSet rs=null;
   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
	   		 	while (rs.next())
				 {
					System.out.println("login: "+login);
				        output+=rs.getString("login")
								+"   "+rs.getString("name")
								+"   "+rs.getString("password")
								+"   "+rs.getString("address")
								+"   "+rs.getString("phone_number")
								+"   "+rs.getString("user_type")
								+"\n";
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
