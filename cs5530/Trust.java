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
					//System.out.print("cname:");
				        output+=rs.getString("login1")+"   "
					 			+rs.getString("login2")+"   "
								+rs.getString("is_trusted")+"\n";
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

	public void addTrust(String login1, String login2, int is_trusted, Statement stmt)
	{
		String sql="INSERT INTO Trust (login1, login2, is_trusted) VALUES ("+login1+", "+login2+", "+is_trusted+")";
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
