package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class Period {
	public Period()
	{}

	public List<List<String>> getPeriod(String pid, Statement stmt)
	{
		String sql="select * from Period where pid = "+pid;
		//String output="";
		List<List<String>> output = new ArrayList<List<String>>();
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			 {
				//System.out.print("cname:");
				 List<String> row = new ArrayList<String>();
				 //System.out.print("cname:");
				 row.add(rs.getString("pid"));
				 row.add(rs.getString("from_date"));
				 row.add(rs.getString("to_date"));
				 output.add(row);
				   // output+=rs.getString("pid") + "   " + rs.getString("from_date") + "   " + rs.getString("to_date")+ "\n";
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

	public List<List<String>> getPeriod(String fromDate, String toDate, Statement stmt)
	{
		String sql="select * from Period where from_date = '"+fromDate
				+"' and to_date = '"+toDate+"'";
		//String output="";
		List<List<String>> output = new ArrayList<List<String>>();
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				//System.out.print("cname:");
				List<String> row = new ArrayList<String>();
				//System.out.print("cname:");
				row.add(rs.getString("pid"));
				row.add(rs.getString("from_date"));
				row.add(rs.getString("to_date"));
				output.add(row);
				// output+=rs.getString("pid") + "   " + rs.getString("from_date") + "   " + rs.getString("to_date")+ "\n";
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

	public String getLastPeriod(Statement stmt)
	{
		String sql="select MAX(p.pid) from Period p";
		//String output="";
		String output = "";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{

				output += rs.getString("MAX(p.pid)");

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

	public void addPeriod(String from_date, String to_date, Statement stmt)
	{
		String sql="INSERT INTO Period (from_date, to_date) VALUES ('"+from_date +"', '" + to_date + "')";
		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("success!");
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
	}

	public void updatePeriod(String pid, String from_date, String to_date, Statement stmt)
	{
		String sql="UPDATE Period SET from_date = '"+from_date+"' to_date = '"+to_date+"' WHERE pid = "+pid;
		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("Success!");
		}
		catch(Exception e)
		{
			System.out.println("cannot update");
		}
	}
}
