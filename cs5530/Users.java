package cs5530;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.ResultSet;
import java.sql.Statement;

public class Users {
	public Users()
	{
	}

	public String getAllUsers(Statement stmt)
	{
		String sql = "select * from Users";
		String output = "";
		ResultSet rs = null;
//		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += "login: "+rs.getString("login") + "   "
						+ "name: "+rs.getString("name") + "   "
						+ "password: "+rs.getString("password") + "   "
						+ "address: "+rs.getString("address") + "   "
						+ "phone number: "+rs.getString("phone_number") + "   "
						+ "user type: "+rs.getString("user_type") + "\n";
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public boolean addUser(String login, String name, String password, String address, String phone_number, Statement stmt)  {
		String sql="INSERT INTO Users (login, name, password, address, phone_number, user_type) VALUES"+
				"('"+login+"', '"+name+"', '"+password+"', '"+address+"', '"+phone_number+"', 0)";
//		System.out.println("executing " + sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("success!");
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query. Exception: " + e);
			return false;
		}
		return true;
	}


	public String getUsers(String login, Statement stmt)
	{
		String sql = "select * from Users where login = '" + login + "'";
		String output = "";
		ResultSet rs = null;
//		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += "login: "+rs.getString("login") + "   t"
						+ "name: "+rs.getString("name") + "   "
						+ "password: "+rs.getString("password") + "   "
						+ "address: "+rs.getString("address") + "   "
						+ "phone number: "+rs.getString("phone_number") + "   "
						+ "user type: "+rs.getString("user_type") + "\n";
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String getUser(String login, String password, Statement stmt)
	{
		String sql="select * from Users where login = '"+login+"' and password = '"+ password+"'";
		String output="";
		ResultSet rs=null;
//		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				System.out.println("login: "+login);
				output+=rs.getString("login")
						+"   "+rs.getString("name")
						+"   "+rs.getString("password")
						+"   "+rs.getString("address")
						+"   "+rs.getString("phone_number")
						+"   "+rs.getString("user_type")
						+"\n";
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

	public String getUser(String login, Statement stmt)
	{
		String sql="select * from Users where login = '"+login+"'";
		String output="";
		ResultSet rs=null;
//		System.out.println("executing "+sql);
		try{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				System.out.println("login: "+login);
				output+=rs.getString("login")
						+"   "+rs.getString("name")
						+"   "+rs.getString("password")
						+"   "+rs.getString("address")
						+"   "+rs.getString("phone_number")
						+"   "+rs.getString("user_type")
						+"\n";
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

	public String getAllOtherUsers(String login, Statement stmt)
	{
		String sql = "select * from Users where login != '" + login + "'";
		String output = "";
		ResultSet rs = null;
//		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += "login: "+rs.getString("login") + "   "
						+ "name: "+rs.getString("name") + "   "
						+ "password: "+rs.getString("password") + "   "
						+ "address: "+rs.getString("address") + "   "
						+ "phone number: "+rs.getString("phone_number") + "   "
						+ "user type: "+rs.getString("user_type") + "\n";
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}
}
