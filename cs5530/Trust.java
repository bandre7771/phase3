package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class Trust {
	public Trust()
	{}
		
	public String getTrust(String login1, String login2, Statement stmt)
	{
			String sql="select * from Trust where login1 = "+login1+" and login2 "+login2;
			String output="<table>";
			output += "<tr> <th> login1 </th> <th> login2 </th> <th> Is Trusted </th> </tr>";
			ResultSet rs=null;
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
				while (rs.next())
				 {
				        output+="<tr><td>"+rs.getString("login1")+"</td>"
					 			+"<td>"+rs.getString("login2")+"</td>"
								+"<td>"+rs.getString("is_trusted")+"</td></tr>";
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

	public boolean addTrust(String login1, String login2, String is_trusted, Statement stmt)
	{
		if(!login1.equals(login2))
		{
			String sql="INSERT INTO Trust (login1, login2, is_trusted) VALUES ('"+login1+"', '"+login2+"', "+is_trusted+")";
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
			System.out.println("cannot declare yourself as trusted");
			return false;
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
		String output="<tr> <th> login </th> <th> trust score </th> </tr>";
		ResultSet rs=null;
		//System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				try
				{
					output+="<tr><td>"+rs.getString("login")+"</td>"
							+"<td>"+rs.getString("trust_score")+"</td></tr>";
				}
				catch (Exception e)
				{
					rs.afterLast();
				}
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
