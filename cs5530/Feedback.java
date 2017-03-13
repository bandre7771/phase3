package cs5530;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class Feedback {
		public Feedback()
		{}

		public String getFeedback(int fid, int hid, Statement stmt)
		{
			String sql="select * from Favorites where fid = "+fid+" and hid = '"+hid+"'";
			String output="";
			ResultSet rs=null;
   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
	   		 	while (rs.next())
				 {
					//System.out.print("cname:");
				        output+=rs.getString("fid")+"   "+rs.getString("hid")+ rs.getString("text") + "\n";
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

	public void addFeedback(String fid, String hid, String text, int score, Date fbdate, Statement stmt)
	{
		String sql="INSERT INTO Favorites (fid, hid, text, score, fbdate) VALUES ("+fid+", "+hid+", " + text +", "+score+", "+", "+fbdate+", "+")";
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
