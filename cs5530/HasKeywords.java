package cs5530;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class HasKeywords {
	public HasKeywords()
	{}
	public String getAllHasKeywords(Statement stmt) {
		String sql = "select * from HasKeywords";
		String output = "<table>";
		output += "<tr> <th> hid </th> <th> wid </th> </tr>";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += "<tr><td>" + rs.getString("hid") + "</td>"
						+ "<td>" + rs.getString("wid") + "</td></tr>";
			}
			output += "</table>";
			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String getHasKeyword(String hid, String wid, Statement stmt)
	{
		String sql="select * from HasKeywords where hid = "+hid+" and wid = '"+wid+"'";
		String output="<table>";
		output += "<tr> <th> hid </th> <th> wid </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="<tr><td>"+rs.getString("hid")+"</td>"
						+"<td>"+rs.getString("wid")+"</td></tr>";
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

	public boolean addHasKeyword(String hid, String wid, Statement stmt)
	{
		String sql="INSERT INTO HasKeywords (hid, wid) VALUES ("+hid+", "+wid+")";
//		System.out.println("executing "+sql);
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

	public boolean removeHasKeyword(String hid, String wid, Statement stmt)
	{
		String sql="DELETE FROM HasKeywords WHERE hid ="+hid+" AND wid ="+wid;
//		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("successfuly removed!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("cannot remove");
			return false;
		}
	}
}
