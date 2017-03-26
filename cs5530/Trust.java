package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class Trust {
	public Trust()
	{}
		
	public String getTrust(String login1, String login2, Statement stmt)
	{
			String sql="select * from Trust where login1 = "+login1+" and login2 "+login2;
			String output="";
			ResultSet rs=null;
   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
				while (rs.next())
				 {
				        output+="login1: "+rs.getString("login1")+"   "
					 			+"login2: "+rs.getString("login2")+"   "
								+"is_trusted: "+rs.getString("is_trusted")+"\n";
				 }
			     rs.close();
   		 	}
   		 	catch(Exception e)
   		 	{
   		 		System.out.println("cannot execute the query");
   		 	}
   		 	finally
   		 	{
   		 		try
				{
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

	public void addTrust(String login1, String login2, String is_trusted, Statement stmt)
	{
		if(!login1.equals(login2))
		{
			String sql="INSERT INTO Trust (login1, login2, is_trusted) VALUES ('"+login1+"', '"+login2+"', "+is_trusted+")";
			System.out.println("executing "+sql);
			try
			{
				stmt.executeUpdate(sql);
				System.out.println("success!");
			}
			catch(Exception e)
			{
				System.out.println("cannot insert");
			}
		}
		else
		{
			System.out.println("cannot declare yourself as trusted");
		}
	}

	public String getMMostTrusted(String m, Statement stmt)
	{
		String sql="SELECT DISTINCT tt.login2 as login, tt.c - tt.nc as trust_score\n" +
				"FROM ((SELECT *\n" +
				"    FROM ((SELECT t.login2, count(*) as c\n" +
				"    FROM Trust t\n" +
				"    WHERE t.is_trusted = true\n" +
				"    GROUP BY t.login2) AS trusted) LEFT JOIN\n" +
				"    ((SELECT t.login2 as log2, count(*) as nc\n" +
				"    FROM Trust t\n" +
				"    WHERE t.is_trusted = false\n" +
				"    GROUP BY t.login2) AS notTrusted)\n" +
				"    ON trusted.login2 = notTrusted.log2)\n" +
				"UNION ALL\n" +
				"      (SELECT *\n" +
				"   FROM ((SELECT t.login2, count(*) as c\n" +
				"    FROM Trust t\n" +
				"    WHERE t.is_trusted = true\n" +
				"    GROUP BY t.login2) AS trusted) RIGHT JOIN\n" +
				"    ((SELECT t.login2 AS log2, count(*) as nc\n" +
				"    FROM Trust t\n" +
				"    WHERE t.is_trusted = false\n" +
				"    GROUP BY t.login2) AS notTrusted)\n" +
				"    ON trusted.login2 = notTrusted.log2))AS tt\n" +
				"WHERE tt.login2 IS NOT NULL\n" +
				"    AND (tt.c - tt.nc) IS NOT NULL\n" +
				"ORDER BY trust_score DESC LIMIT "+m;
		String output="";
		ResultSet rs=null;
		//System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				try
				{
					output+="login: "+rs.getString("login")+"   "
							+"trust score: "+rs.getString("trust_score")+"\n";
				}
				catch (Exception e)
				{
					rs.afterLast();
				}
			}
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try
			{
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
