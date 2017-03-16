package cs5530;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Visit {
		public Visit()
		{}

		public boolean addVisit(String login, String hid, String pid, String cost, Statement stmt)
		{
			String sql="INSERT INTO Visit (login, hid, pid, cost) VALUES  ('"+login+"',"+hid+","+pid+","+cost+")";
			System.out.println("executing " + sql);
			try{
				stmt.executeUpdate(sql);
				System.out.println("login: "+login+" hid: "+hid+" pid: "+pid);
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
   		 	System.out.println("executing "+sql);
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
}
