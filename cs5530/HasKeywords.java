package cs5530;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class HasKeywords {
	public HasKeywords()
	{}

	public String getHasKeyword(int hid, int wid, Statement stmt)
	{
		String sql="select * from HasKeywords where hid = "+hid+" and wid = '"+wid+"'";
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			 {
				//System.out.print("cname:");
					output+=rs.getString("hid")+"   "+rs.getString("wid") + "\n";
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

	public void addHasKeyword(int hid, int wid, Statement stmt)
	{
		String sql="INSERT INTO HasKeywords (hid, wid) VALUES ("+hid+", "+wid+")";
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
