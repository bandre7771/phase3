package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class Reserve {
		public Reserve()
		{}

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

	public void addReservation(String login, int hid, int pid, float cost, Statement stmt)
	{
		String sql="INSERT INTO Reserve (login, hid, pid, cost) VALUES ("+login+", "+ hid +", " + pid + ", "+ cost + ")";
		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
	}
}
