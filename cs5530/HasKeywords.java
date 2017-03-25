package cs5530;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class HasKeywords {
	public HasKeywords()
	{}
	public String getAllHasKeywords(Statement stmt)
	{
		String sql="select * from HasKeywords";
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+="hid: "+rs.getString("hid")+"   "
						+"wid: "+rs.getString("wid")+"\n";
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



	public String getHasKeyword(String hid, String wid, Statement stmt)
	{
		String sql="select * from HasKeywords where hid = "+hid+" and wid = '"+wid+"'";
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			 {
				 output+="hid: "+rs.getString("hid")+"   "
						 +"wid: "+rs.getString("wid")+"\n";
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

	public void addHasKeyword(String hid, String wid, Statement stmt)
	{
		String sql="INSERT INTO HasKeywords (hid, wid) VALUES ("+hid+", "+wid+")";
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

	public void removeHasKeyword(String hid, String wid, Statement stmt)
	{
		String sql="DELETE FROM HasKeywords WHERE hid ="+hid+" AND wid ="+wid;
		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("successfuly removed!");
		}
		catch(Exception e)
		{
			System.out.println("cannot remove");
		}
	}
}
