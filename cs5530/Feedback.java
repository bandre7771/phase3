package cs5530;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Feedback {
		public Feedback()
		{}

		public List<List<String>> getFeedback(String hid, String login, Statement stmt)
		{
			String sql="select * from Feedback where hid = "+hid+" and login = '"+login+"'";
//			String output="";
			List<List<String>> output = new ArrayList<List<String>>();
			ResultSet rs=null;
//   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
	   		 	while (rs.next())
				 {
					//System.out.print("cname:");
					 List<String> row = new ArrayList<String>();
					 row.add(rs.getString("fid"));
					 row.add(rs.getString("hid"));
					 row.add(rs.getString("login"));
					 row.add(rs.getString("text"));
					 output.add(row);
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

	public void addFeedback(String hid, String login, String text, int score, Statement stmt)
	{
		Calendar calendar = Calendar.getInstance();
		java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
		String sql="INSERT INTO Feedback (hid, login, text, score, fbdate) VALUES ("+hid+", '" + login + "', '" + text +"', "+score+", '"+ourJavaDateObject+"')";
//		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("success!");
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
	}

	public void deleteFeedback(String fid, Statement stmt) {
		String sql="delete from Feedback where fid = '" +fid + "'";
//		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("success!");
		}
		catch(Exception e)
		{
			System.out.println("cannot remove available period");
		}
	}

	public String getAllOtherUserFeedback(String login, Statement stmt)
	{
		String sql="select * from Feedback where login != '"+login+"'";
		String output="";
		ResultSet rs=null;
//		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="fid: "+rs.getString("fid")+"   "
						+"hid: "+rs.getString("hid")+"   "
						+"login: "+rs.getString("login")+"   "
						+"text: "+rs.getString("text") + "   "
						+"score: "+rs.getString("score")+"   "
						+"fbdate: "+rs.getString("fbdate")+"\n";
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

	public String getLoginFeedback(String fid, Statement stmt)
	{
		String sql="select DISTINCT login from Feedback where fid = "+fid;

		String output="";
		ResultSet rs=null;
//		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+=rs.getString("login");
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
