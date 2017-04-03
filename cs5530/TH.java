package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class TH {
	public TH()
	{}

	public String getAllTH(Statement stmt)
	{
		String sql="select * from TH";
		String output="<table>";
		output += "<tr> <th> hid </th> <th> hname </th> <th> category </th> <th> address </th> <th> login </th> <th> phone number </th> <th> year built </th> <th> url </th> <th> picture </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("hid") + "</td>"
						+ "<td>"+rs.getString("hname") + "</td>"
						+ "<td>"+rs.getString("category") + "</td>"
						+ "<td>"+rs.getString("address") + "</td>"
						+ "<td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("phone_number") + "</td>"
						+ "<td>"+rs.getString("year_built") + "</td>"
						+ "<td>"+rs.getString("url") + "</td>"
						+ "<td>"+rs.getString("picture") +"</td></tr>";
			}
			output += "</table>";
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
		String output="<table>";
		output += "<tr> <th> hid </th> <th> hname </th> <th> category </th> <th> address </th> <th> login </th> <th> phone number </th> <th> year built </th> <th> url </th> <th> picture </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("hid") + "</td>"
						+ "<td>"+rs.getString("hname") + "</td>"
						+ "<td>"+rs.getString("category") + "</td>"
						+ "<td>"+rs.getString("address") + "</td>"
						+ "<td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("phone_number") + "</td>"
						+ "<td>"+rs.getString("year_built") + "</td>"
						+ "<td>"+rs.getString("url") + "</td>"
						+ "<td>"+rs.getString("picture") +"</td></tr>";
			}
			output += "</table>";
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
		String output="<table>";
		output += "<tr> <th> hid </th> <th> hname </th> <th> category </th> <th> address </th> <th> login </th> <th> phone number </th> <th> year built </th> <th> url </th> <th> picture </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("hid") + "</td>"
						+ "<td>"+rs.getString("hname") + "</td>"
						+ "<td>"+rs.getString("category") + "</td>"
						+ "<td>"+rs.getString("address") + "</td>"
						+ "<td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("phone_number") + "</td>"
						+ "<td>"+rs.getString("year_built") + "</td>"
						+ "<td>"+rs.getString("url") + "</td>"
						+ "<td>"+rs.getString("picture") +"</td></tr>";
			}
			output += "</table>";
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
		String output="<table>";
		output += "<tr> <th> hid </th> <th> hname </th> <th> category </th> <th> address </th> <th> login </th> <th> phone number </th> <th> year built </th> <th> url </th> <th> picture </th> </tr>";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output+= "<tr><td>"+rs.getString("hid") + "</td>"
						+ "<td>"+rs.getString("hname") + "</td>"
						+ "<td>"+rs.getString("category") + "</td>"
						+ "<td>"+rs.getString("address") + "</td>"
						+ "<td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("phone_number") + "</td>"
						+ "<td>"+rs.getString("year_built") + "</td>"
						+ "<td>"+rs.getString("url") + "</td>"
						+ "<td>"+rs.getString("picture") +"</td></tr>";
			}
			output += "</table>";
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

	public boolean isOwnerOfTH(String login, String hid, Statement stmt)
	{
		String sql="select * from TH where hid = "+hid+" AND login = '"+login+"'";
		String output="";
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				output += rs.getString("hid");
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
		return !output.isEmpty();
	}

	public boolean addTH(String category, String login, String hname, String address,
					  String url, String phone_number, String year_built, String picture, Statement stmt)
	{
		String sql="INSERT INTO TH (category, login, hname, address," +
				"url, phone_number, year_built, picture) VALUES ('"+category+"', '"+login+"', '"+hname+"', '"+address+"','"+url+"', '"
				+phone_number+"', "+year_built+", '"+picture+"')";
		try{
			stmt.executeUpdate(sql);
			System.out.println("Success!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("cannot insert");
		}
		return false;
	}

	public boolean updateTH(String hid, String category, String login, String hname, String address,
					  String url, String phone_number, String year_built, String picture, Statement stmt)
	{
		String sql="UPDATE TH SET category = '"+category+"', login = '"+login+"', hname = '"+hname+"', address = '"+address+"', url ='"+url+"', phone_number = '"
				+phone_number+"', year_built = "+year_built+", picture = '"+picture+"' WHERE hid = "+hid;
		try{
			stmt.executeUpdate(sql);
			System.out.println("Success!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("cannot update");
			return false;
		}
	}
}
