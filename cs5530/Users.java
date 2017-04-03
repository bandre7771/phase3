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
		String output = "<table>";
		ResultSet rs = null;
		output += "<tr> <th> login </th> <th> name </th> <th> password </th> <th> address </th> <th> phone number </th> user type </th> <tr>";
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += "<tr><td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("name") + "</td>"
						+ "<td>"+rs.getString("password") + "</td>"
						+ "<td>"+rs.getString("address") + "</td>"
						+ "<td>"+rs.getString("phone_number") + "</td>"
						+ "<td>"+rs.getString("user_type") + "</td></tr>";
			}
			output += "</table>";
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

	public boolean userExists(String login, Statement stmt)
	{
		String sql = "select * from Users where login = '" + login + "'";
		String output = "";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += rs.getString("login");
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
		return !output.isEmpty();
	}
	public String getUsers(String login, Statement stmt)
	{
		String sql = "select * from Users where login = '" + login + "'";
		String output = "<table>";
		ResultSet rs = null;
		output += "<tr> <th> login </th> <th> name </th> <th> password </th> <th> address </th> <th> phone number </th> user type </th> <tr>";
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += "<tr><td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("name") + "</td>"
						+ "<td>"+rs.getString("password") + "</td>"
						+ "<td>"+rs.getString("address") + "</td>"
						+ "<td>"+rs.getString("phone_number") + "</td>"
						+ "<td>"+rs.getString("user_type") + "</td></tr>";
			}
			output += "</table>";
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
		String output = "<table>";
		ResultSet rs = null;
		output += "<tr> <th> login </th> <th> name </th> <th> password </th> <th> address </th> <th> phone number </th> user type </th> <tr>";
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += "<tr><td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("name") + "</td>"
						+ "<td>"+rs.getString("password") + "</td>"
						+ "<td>"+rs.getString("address") + "</td>"
						+ "<td>"+rs.getString("phone_number") + "</td>"
						+ "<td>"+rs.getString("user_type") + "</td></tr>";
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

	public String getUser(String login, Statement stmt)
	{
		String sql="select * from Users where login = '"+login+"'";
		String output = "<table>";
		ResultSet rs = null;
		output += "<tr> <th> login </th> <th> name </th> <th> password </th> <th> address </th> <th> phone number </th> user type </th> <tr>";
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += "<tr><td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("name") + "</td>"
						+ "<td>"+rs.getString("password") + "</td>"
						+ "<td>"+rs.getString("address") + "</td>"
						+ "<td>"+rs.getString("phone_number") + "</td>"
						+ "<td>"+rs.getString("user_type") + "</td></tr>";
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

	public String getAllOtherUsers(String login, Statement stmt)
	{
		String sql = "select * from Users where login != '" + login + "'";
		String output = "<table>";
		ResultSet rs = null;
		output += "<tr> <th> login </th> <th> name </th> <th> password </th> <th> address </th> <th> phone number </th> user type </th> <tr>";
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += "<tr><td>"+rs.getString("login") + "</td>"
						+ "<td>"+rs.getString("name") + "</td>"
						+ "<td>"+rs.getString("password") + "</td>"
						+ "<td>"+rs.getString("address") + "</td>"
						+ "<td>"+rs.getString("phone_number") + "</td>"
						+ "<td>"+rs.getString("user_type") + "</td></tr>";
			}
			output += "</table>";
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
