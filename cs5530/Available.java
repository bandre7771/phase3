package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Available {
		public Available()
		{}

		public List<List<String>> getAvailable(String hid, String pid, Statement stmt)
		{
			String sql="select * from Available where hid = '"+hid+"' and pid '"+pid+"'";
			List<List<String>> output = new ArrayList<List<String>>();

			ResultSet rs=null;
//   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
				int count = 0;
	   		 	while (rs.next())
				 {
					 List<String> row = new ArrayList<String>();
					//System.out.print("cname:");
					 row.add(rs.getString("hid"));
					 row.add(rs.getString("pid"));
					 row.add(rs.getString("price_per_night"));
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

	public void addAvailable(String hid, String pid, String cost_per_night, Statement stmt)
	{
		String sql="INSERT INTO Available (hid, pid, price_per_night) VALUES ("+hid+", "+pid+", "+cost_per_night+")";
//		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("Available period successfully added!");
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
	}

	public void updateAvailable(String hid, String pid, String cost_per_night, Statement stmt)
	{
		String sql="UPDATE Available SET cost_per_night = '"+cost_per_night+"' WHERE hid = "+hid+" AND pid = " + pid;
//		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("success!");
		}
		catch(Exception e)
		{
			System.out.println("cannot update");
		}
	}

	public void deleteAvailable(String thid, String pid, Statement stmt) {
		String sql="delete from Available where pid = '" +pid + "' and hid = '" + thid +"'";
		//System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("success!");
		}
		catch(Exception e)
		{
			System.out.println("cannot remove available period");
		}
	}
}
