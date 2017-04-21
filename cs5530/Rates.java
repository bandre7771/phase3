package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class Rates {
	public Rates()
	{}

	public String getRating(String fid, String login, Statement stmt)
	{
			String sql="select * from Rates where fid = "+fid+" and login = '"+login+"'";
			String output="<table>";
			output += "<tr> <th> fid </th> <th> login </th> <th> rating </th> </tr>";
			ResultSet rs=null;
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
	   		 	while (rs.next())
				 {
			        output+="<tr><td>"+rs.getString("fid") + "</td>"
							+"<td>"+rs.getString("login") + "</td>"
							+"<td>"+rs.getString("rating")+ "</td></tr>";
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

	public boolean addRating(String fid, String login, String rating, Statement stmt)
	{
		Feedback feedback = new Feedback();
		if(!feedback.isThereOwnFeedback(login, fid, stmt))
		{
			String sql="INSERT INTO Rates (fid, login, rating) VALUES ("+fid+", '"+ login +"', " + rating + ")";
//			System.out.println("executing "+sql);
			try
			{
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
		else
		{
			System.out.println("you cannot rate your own feedback");
			return false;
		}
	}
}
