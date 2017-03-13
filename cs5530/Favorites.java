package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class Favorites {
		public Favorites()
		{}

		public String getFavorites(String hid, String login, Statement stmt)
		{
			String sql="select * from Favorites where hid = "+hid+" and login = '"+login+"'";
			String output="";
			ResultSet rs=null;
   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
	   		 	while (rs.next())
				 {
					//System.out.print("cname:");
				        output+=rs.getString("hid")+"   "+rs.getString("login")+"\n";
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

	public void addFavorite(int hid, String login, Statement stmt)
	{
		String sql="INSERT INTO Favorites (hid, login) VALUES ("+hid+", "+login+")";
		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
	}
}
