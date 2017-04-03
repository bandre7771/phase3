package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reserve {
	public Reserve()
	{}

	public String getReserve(String login, Statement stmt)
	{
		String sql="select * from Reserve where login = '"+login+"'";
		String output="<tr> <th> login </th> <th> hid </th> <th> pid </th> <th> cost </th></tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="<tr><td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("hid") + "</td>"
						+ "<td>"+rs.getString("pid") + "</td>"
						+ "<td>"+rs.getString("cost") + "</td></tr>";
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

	public String getReserve(String login, int hid, int pid, Statement stmt)
	{
		String sql="select * from Reserve where login = '"+login+"' and hid = "+hid+" and pid = "+pid+"";
		String output="<tr> <th> login </th> <th> hid </th> <th> pid </th> <th> cost </th></tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="<tr><td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("hid") + "</td>"
						+ "<td>"+rs.getString("pid") + "</td>"
						+ "<td>"+rs.getString("cost") + "</td></tr>";
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

	public String getReserve(int hid, int pid, Statement stmt)
	{
		String sql="select * from Reserve where hid = "+hid+" and pid = "+pid;
		String output="<tr> <th> login </th> <th> hid </th> <th> pid </th> <th> cost </th></tr>";
		ResultSet rs=null;
		try {
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="<tr><td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("hid") + "</td>"
						+ "<td>"+rs.getString("pid") + "</td>"
						+ "<td>"+rs.getString("cost") + "</td></tr>";
			}
			output += "</table>";
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

	public boolean addReservation(String login, String hid, String pid, Statement stmt)
	{
		String sql="INSERT INTO Reserve (login, hid, pid, cost) VALUES ('"+login+"', "+ hid +", " + pid + ", 500)";
		//System.out.println("executing "+sql);
		try {
			stmt.executeUpdate(sql);
			System.out.println("Reservation Successfully added");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
			return false;
		}
	}


}
