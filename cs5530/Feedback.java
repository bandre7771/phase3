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

	public boolean addFeedback(String hid, String login, String text, int score, Statement stmt)
	{
		Calendar calendar = Calendar.getInstance();
		java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
		String sql="INSERT INTO Feedback (hid, login, text, score, fbdate) VALUES ("+hid+", '" + login + "', '" + text +"', "+score+", '"+ourJavaDateObject+"')";
		try{
			stmt.executeUpdate(sql);
			System.out.println("success!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
			return false;
		}
	}

	public boolean deleteFeedback(String fid, Statement stmt) {
		String sql="delete from Feedback where fid = '" +fid + "'";
		try{
			stmt.executeUpdate(sql);
			System.out.println("success!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("cannot remove available period");
			return false;
		}
	}

	public String getAllOtherUserFeedback(String login, Statement stmt)
	{
		String sql="select * from Feedback where login != '"+login+"'";
		String output="<table>";
		output += "<tr> <th> fid </th> <th> hid </th> <th> login </th> <th> text </th> <th> score </th> <th> fbdate </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="<tr><td>"+rs.getString("fid")+"</td>"
						+"<td>"+rs.getString("hid")+"</td>"
						+"<td>"+rs.getString("login")+"</td>"
						+"<td>"+rs.getString("text") + "</td>"
						+"<td>"+rs.getString("score")+"</td>"
						+"<td>"+rs.getString("fbdate")+"</td></tr>";
			}
			output += "</table>";
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

	public boolean isThereOwnFeedback(String login, String fid, Statement stmt)
	{
		String sql="select DISTINCT login from Feedback where fid = "+fid;

		String output="";
		ResultSet rs=null;
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
		return login.equals(output);
	}
}
