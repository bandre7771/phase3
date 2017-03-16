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

	public boolean oneDegreeOfSeparation(String login1, String login2, Statement stmt)
	{
		String sql="# returns something if there is a one degree of separation.\n" +
				"SELECT *\n" +
				"FROM Favorites f2\n" +
				"WHERE f2.hid = ANY (SELECT DISTINCT hid\n" +
				"              FROM Favorites f1\n" +
				"              WHERE login = '"+login1+"')\n" +
				"      AND f2.login = '"+login2+"';";
		String output="";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{

				output+="hid: "+rs.getString("hid")+"   "
						+"login: "+rs.getString("login")+"\n";
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
		return !output.isEmpty();
	}

	public boolean oneOrTwoDegreeOfSeparation(String login1, String login2, Statement stmt)
	{
		String sql="# returns something if there is a one or two degree of separation.\n" +
				"SELECT *\n" +
				"FROM Favorites f4\n" +
				"WHERE f4.hid IN\n" +
				"  (SELECT f3.hid\n" +
				"    FROM Favorites f3\n" +
				"    WHERE f3.login IN (SELECT login\n" +
				"        FROM Favorites f2\n" +
				"        WHERE f2.hid = ANY (SELECT DISTINCT hid\n" +
				"                      FROM Favorites f1\n" +
				"                      WHERE login = '"+login1+"')))\n" +
				"  AND f4.login = '"+login2+"';";
		String output="";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{

				output+="hid: "+rs.getString("hid")+"   "
						+"login: "+rs.getString("login")+"\n";
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
		return !output.isEmpty();
	}
}
