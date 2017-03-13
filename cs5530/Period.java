package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class Period {
		public Period()
		{}

		public String getPeriod(int pid, Statement stmt)
		{
			String sql="select * from Period where pid = "+pid;
			String output="";
			ResultSet rs=null;
   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
	   		 	while (rs.next())
				 {
					//System.out.print("cname:");
				        output+=rs.getString("pid") + "   " + rs.getString("from_date") + "   " + rs.getString("to_date")+ "\n";
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

	public void addPeriod(int pid, String from_date, String to_date, Statement stmt)
	{
		String sql="INSERT INTO Period (pid, from_date, to_date) VALUES ("+pid+", "+ from_date +", " + to_date + ")";
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
