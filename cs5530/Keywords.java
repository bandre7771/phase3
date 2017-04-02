package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class Keywords {
	public Keywords()
	{}

	public String getAllKeyWords(Statement stmt)
	{
		String sql="select * from Keywords";
		String output="<table>";
		output += "<tr> <th> wid </th> <th> word </th> <th> language </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="<tr><td>"+rs.getString("wid") + "</td>"
						+"<td>"+rs.getString("word") + "</td>"
						+"<td>"+rs.getString("language")+"</td></tr>";
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

	public String getKeywords(String wid, Statement stmt)
	{
		String sql="select * from Keywords where wid = "+wid;
		String output="<table>";
		output += "<tr> <th> wid </th> <th> word </th> <th> language </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="<tr><td>"+rs.getString("wid") + "</td>"
						+"<td>"+rs.getString("word") + "</td>"
						+"<td>"+rs.getString("language")+"</td></tr>";
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

	public boolean addKeyword(String word, String language, Statement stmt)
	{
		String sql="INSERT INTO Keywords (word, language) VALUES ('"+ word +"', '" + language + "')";
//		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("Success!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
			return false;
		}
	}

	public boolean updateKeyword(String wid, String word, String language, Statement stmt)
	{
		String sql="UPDATE Keywords SET wid = "+wid+", word = '"+word+"', language = '"+language+"' WHERE wid = "+wid;
//		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("Success!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("cannot update");
			return false;
		}
	}
}
