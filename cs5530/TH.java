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
				System.out.println("hid\t\thname\t\tcategory\t\taddress\t\tlogin\t\tphone number\t\tyear built\t\turl\t\tpicture");
				while (rs.next())
				{
					output+= "hid: "+rs.getString("hid") + "\t\t"
							+ "hname: "+rs.getString("hname") + "\t\t"
							+ "category: "+rs.getString("category") + "\t\t"
							+ "address: "+rs.getString("address") + "\t\t"
							+ "login: "+rs.getString("login") + "\t\t"
							+ "phone number: "+rs.getString("phone_number") + "\t\t"
							+ "year_built: "+rs.getString("year_built") + "\t\t"
							+ "url: "+rs.getString("url") + "\t\t"
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
				System.out.println("hid\t\thname\t\tcategory\t\taddress\t\tlogin\t\tphone number\t\tyear built\t\turl\t\tpicture");
				while (rs.next())
				{
					output+=rs.getString("hid") + "\t\t"
							+ rs.getString("hname") + "\t\t"
							+ rs.getString("category") + "\t\t"
							+ rs.getString("address") + "\t\t"
							+ rs.getString("login") + "\t\t"
							+ rs.getString("phone_number") + "\t\t"
							+ rs.getString("year_built") + "\t\t"
							+ rs.getString("url") + "\t\t"
							+ rs.getString("picture") +"\n";
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
			System.out.println("hid\t\thname\t\tcategory\t\taddress\t\tlogin\t\tphone number\t\tyear built\t\turl\t\tpicture");
			while (rs.next())
			{
				output+=rs.getString("hid") + "\t\t"
						+ rs.getString("hname") + "\t\t"
						+ rs.getString("category") + "\t\t"
						+ rs.getString("address") + "\t\t"
						+ rs.getString("login") + "\t\t"
						+ rs.getString("phone_number") + "\t\t"
						+ rs.getString("year_built") + "\t\t"
						+ rs.getString("url") + "\t\t"
						+ rs.getString("picture") +"\n";
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
			System.out.println("hid\t\thname\t\tcategory\t\taddress\t\tlogin\t\tphone number\t\tyear built\t\turl\t\tpicture");
			while (rs.next())
			{
				output+=rs.getString("hid") + "\t\t"
						+ rs.getString("hname") + "\t\t"
						+ rs.getString("category") + "\t\t"
						+ rs.getString("address") + "\t\t"
						+ rs.getString("login") + "\t\t"
						+ rs.getString("phone_number") + "\t\t"
						+ rs.getString("year_built") + "\t\t"
						+ rs.getString("url") + "\t\t"
						+ rs.getString("picture") +"\n";
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
