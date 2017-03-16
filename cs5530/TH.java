package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class TH {
	public TH()
	{}

	public String getAllTH(Statement stmt)
	{
			String sql="select * from TH";
			String output="";
			ResultSet rs=null;
			System.out.println("executing "+sql);
			try{
				rs=stmt.executeQuery(sql);
				while (rs.next())
				{
					output+= "hid: "+rs.getString("hid") + "   "
							+ "hname: "+rs.getString("hname") + "   "
							+ "category: "+rs.getString("category") + "   "
							+ "address: "+rs.getString("address") + "   "
							+ "login: "+rs.getString("login") + "   "
							+ "phone number: "+rs.getString("phone_number") + "   "
							+ "year_built: "+rs.getString("year_built") + "   "
							+ "url: "+rs.getString("url") + "   "
							+ "picture: "+rs.getString("picture") +"\n";
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

	public String getTH(String hid, Statement stmt)
	{
			String sql="select * from TH where hid = "+hid;
			String output="";
			ResultSet rs=null;
   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
				while (rs.next())
				{
					output+= "hid: "+rs.getString("hid") + "   "
							+ "hname: "+rs.getString("hname") + "   "
							+ "category: "+rs.getString("category") + "   "
							+ "address: "+rs.getString("address") + "   "
							+ "login: "+rs.getString("login") + "   "
							+ "phone number: "+rs.getString("phone_number") + "   "
							+ "year_built: "+rs.getString("year_built") + "   "
							+ "url: "+rs.getString("url") + "   "
							+ "picture: "+rs.getString("picture") +"\n";
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

	public String getTHForLogin(String login, Statement stmt)
	{
		String sql="select * from TH where login = '"+login+"'";
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "hid: "+rs.getString("hid") + "   "
						+ "hname: "+rs.getString("hname") + "   "
						+ "category: "+rs.getString("category") + "   "
						+ "address: "+rs.getString("address") + "   "
						+ "login: "+rs.getString("login") + "   "
						+ "phone number: "+rs.getString("phone_number") + "   "
						+ "year_built: "+rs.getString("year_built") + "   "
						+ "url: "+rs.getString("url") + "   "
						+ "picture: "+rs.getString("picture") +"\n";
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

	public String getTH(String login, String hid, Statement stmt)
	{
		String sql="select * from TH where hid = "+hid+" AND login = '"+login+"'";
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "hid: "+rs.getString("hid") + "   "
						+ "hname: "+rs.getString("hname") + "   "
						+ "category: "+rs.getString("category") + "   "
						+ "address: "+rs.getString("address") + "   "
						+ "login: "+rs.getString("login") + "   "
						+ "phone number: "+rs.getString("phone_number") + "   "
						+ "year_built: "+rs.getString("year_built") + "   "
						+ "url: "+rs.getString("url") + "   "
						+ "picture: "+rs.getString("picture") +"\n";
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

	public void addTH(String category, String login, String hname, String address,
					  String url, String phone_number, String year_built, String picture, Statement stmt)
	{
		String sql="INSERT INTO TH (category, login, hname, address," +
				"url, phone_number, year_built, picture) VALUES ('"+category+"', '"+login+"', '"+hname+"', '"+address+"','"+url+"', '"
				+phone_number+"', "+year_built+", '"+picture+"')";
		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("Success!");
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
	}

	public void updateTH(String hid, String category, String login, String hname, String address,
					  String url, String phone_number, String year_built, String picture, Statement stmt)
	{
		String sql="UPDATE TH SET category = '"+category+"', login = '"+login+"', hname = '"+hname+"', address = '"+address+"', url ='"+url+"', phone_number = '"
				+phone_number+"', year_built = "+year_built+", picture = '"+picture+"' WHERE hid = "+hid;
		System.out.println("executing "+sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("Success!");
		}
		catch(Exception e)
		{
			System.out.println("cannot update");
		}
	}
}
