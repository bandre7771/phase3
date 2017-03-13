package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class Rates {
		public Rates()
		{}

		public String getRating(int fid, String login, Statement stmt)
		{
			String sql="select * from Rates where fid = "+fid+" and login = '"+login+"'";
			String output="";
			ResultSet rs=null;
   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
	   		 	while (rs.next())
				 {
					//System.out.print("cname:");
				        output+=rs.getString("fid") + "   " + rs.getString("login") + "   " + rs.getString("rating")+ "\n";
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

	public void addRating(int fid, String login, int rating, Statement stmt)
	{
		String sql="INSERT INTO Rates (fid, login, rating) VALUES ("+fid+", "+ login +", " + rating + ")";
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
