package cs5530;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.ResultSet;
import java.sql.Statement;

public class Users {
	public Users()
	{
	}

	public boolean addUsers(String login, String name, String password, String address, String phone_number, String user_type, Statement stmt) {
		String sql = "INSERT INTO Users (login, name, password, address, phone_number, user_type) VALUES" +
				"('" + login + "', '" + name + "', '" + password + "', '" + address + "', '" + phone_number + "', " + user_type + ")";
		System.out.println("executing " + sql);
		try {
			stmt.executeUpdate(sql);
			System.out.println("login: " + login + " name: " + name + " address: " + address + " phone_number: " + phone_number + " user_type: " + user_type);
		} catch (Exception e) {
			System.out.println("cannot execute the query");
			return false;
		}
		return true;
	}

	public String getUsers(String login, Statement stmt)
	{
		String sql = "select * from Users where login = '" + login + "'";
		String output = "";
		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("login\t\t\tname\t\t\tpassword\t\t\taddress\t\t\tphone number\t\t\tuser type");
			while (rs.next()) {
				output += rs.getString("login") + "\t\t\t"
						+ rs.getString("name") + "\t\t\t"
						+ rs.getString("password") + "\t\t\t"
						+ rs.getString("address") + "\t\t\t"
						+ rs.getString("phone_number") + "\t\t\t"
						+ rs.getString("user_type") + "\n";
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

	public String getAllOtherUsers(String login, Statement stmt)
	{
		String sql = "select * from Users where login != '" + login + "'";
		String output = "";
		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("login\t\t\tname\t\t\tpassword\t\t\taddress\t\t\tphone number\t\t\tuser type");
			while (rs.next()) {
				output += rs.getString("login") + "\t\t\t"
						+ rs.getString("name") + "\t\t\t"
						+ rs.getString("password") + "\t\t\t"
						+ rs.getString("address") + "\t\t\t"
						+ rs.getString("phone_number") + "\t\t\t"
						+ rs.getString("user_type") + "\n";
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
