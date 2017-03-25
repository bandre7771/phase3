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
		String output="";
		ResultSet rs=null;
		//System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="login: "+rs.getString("login") + "   "
						+ "hid: "+rs.getString("hid") + "   "
						+ "pid: "+rs.getString("pid") + "   "
						+ "cost: "+rs.getString("cost") + "\n";
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

	public String getReserve(String login, int hid, int pid, Statement stmt)
	{
		String sql="select * from Reserve where login = '"+login+"' and hid = "+hid+" and pid = "+pid+"";
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			 {
				 output+="login: "+rs.getString("login") + "   "
						 + "hid: "+rs.getString("hid") + "   "
						 + "pid: "+rs.getString("pid") + "   "
						 + "cost: "+rs.getString("cost") + "\n";
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

	public String getReserve(int hid, int pid, Statement stmt)
	{
		String sql="select * from Reserve where hid = "+hid+" and pid = "+pid;
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				//System.out.print("cname:");
				output+=rs.getString("login") + "   " + rs.getString("hid") + "   " + rs.getString("pid")+ rs.getString("cost") +"\n";
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


	public void addReservation(String login, String hid, String pid, Statement stmt)
	{
		String sql="INSERT INTO Reserve (login, hid, pid, cost) VALUES ('"+login+"', "+ hid +", " + pid + ", 500)";
		//System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
	}


}
