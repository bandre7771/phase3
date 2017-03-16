package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class Available {
		public Available()
		{}
		
	public String getAvailable(String hid, String pid, Statement stmt)
	{
		String sql="select * from Available where hid = "+hid+" and pid "+pid;
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			 {
				//System.out.print("cname:");
					output+=rs.getString("hid")+"   "+rs.getString("pid")+"\n";
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

	public void addFavorites(String hid, String pid, Statement stmt)
	{
		String sql="INSERT INTO Favorites (hid, login) VALUES ("+hid+", "+pid+")";
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
