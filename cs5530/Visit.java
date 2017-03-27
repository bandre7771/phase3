package cs5530;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Visit {
		public Visit()
		{}

		public boolean addVisit(String login, String hid, String pid, String cost, Statement stmt)
		{
			String sql="INSERT INTO Visit (login, hid, pid, cost) VALUES  ('"+login+"',"+hid+","+pid+","+cost+")";
//			System.out.println("executing " + sql);
			try{
				stmt.executeUpdate(sql);
//				System.out.println("login: "+login+" hid: "+hid+" pid: "+pid);
			}
			catch(Exception e)
			{
				System.out.println("cannot execute the query");
				return false;
			}
			return true;
		}

		public String getVisit(String login, String hid, String pid, Statement stmt)
		{
			String sql="select * from Visit where login = '"+login+"' and hid = "+hid+" and pid = "+pid;
			String output="";
			ResultSet rs=null;
//   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
				while (rs.next())
				 {
				        output+="login: "+rs.getString("login")+"   "
								+"hid: "+rs.getString("hid")+"   "
								+"pid: "+rs.getString("pid")+"\n";
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

	public String getVisit(String login, int hid, Statement stmt)
	{
		String sql="select * from Visit where login = '"+login+"' and hid = "+hid;
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
//				System.out.println("login: "+login+" hid: "+hid);
				output+=rs.getString("login")+"   "+rs.getString("hid")+"   "+rs.getString("pid")+"\n";
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

	public List<List<String>> getVisit(String login, Statement stmt)
	{
		String sql="select * from Visit where login = '"+login+"'";
		//String output="";
		ResultSet rs=null;
		List<List<String>> output = new ArrayList<List<String>>();

//		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				//System.out.println("login: " + login + " hid: " + hid);
				List<String> row = new ArrayList<String>();
				//System.out.print("cname:");
				row.add(rs.getString("login"));
				row.add(rs.getString("hid"));
				row.add(rs.getString("pid"));
				row.add(rs.getString("cost"));
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

	public void addVisit(String login, String hid, String pid, Statement stmt)
	{
		String sql="INSERT INTO Visit(login, hid, pid, cost) VALUES ('"+login+"', "+ hid +", " + pid + ", 500)";
//		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("Visit successfully added");
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
	}

	public List<List<String>> getSuggestedTHs(String hid, Statement stmt){

		String sql="select t.hid, t.hname, t.category, t.address, t.year_built, t.phone_number, t.url, COUNT(*) as numVisits\n" +
				"from TH t, (select v.hid\n" +
				"        from Visit v\n" +
				"        where v.login = ANY (\n" +
				"            select DISTINCT r.login\n" +
				"            from Reserve r\n" +
				"            where r.hid =" + hid + ")) v\n" +
				"where v.hid = t.hid\n" +
				"GROUP BY t.hid, t.hname, t.category, t.address, t.year_built, t.phone_number, t.url\n" +
				"order by numVisits DESC";
		List<List<String>> output= new ArrayList<List<String>>();
		ResultSet rs=null;
		// System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				List<String> row = new ArrayList<String>();
				row.add(rs.getString("hid"));
				row.add(rs.getString("hname"));
				row.add(rs.getString("category"));
				row.add(rs.getString("address"));
				row.add(rs.getString("year_built"));
				row.add(rs.getString("phone_number"));
				row.add(rs.getString("url"));
				row.add(rs.getString("numVisits"));
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
}
