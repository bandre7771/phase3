package cs5530;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.ResultSet;
import java.sql.Statement;

public class Users {

	public String _login;
	public String _name;
	public String _password;
	public String _address;
	public String _phone_number;

	public Users()
	{
		_login = "";
		_name = "";
		_password = "";
		_address = "";
		_phone_number = "";
	}

	public Users(String login, String name, String password, String address, String phone_number)
	{
		_login = login;
		_name = name;
		_password = password;
		_address = address;
		_phone_number = phone_number;
	}

	public boolean addUser(String login, String name, String password, String address, String phone_number, Statement stmt)  {
		String sql="INSERT INTO Users (login, name, password, address, phone_number, user_type) VALUES"+
				"('"+login+"', '"+name+"', '"+password+"', '"+address+"', '"+phone_number+"', 0)";
		System.out.println("executing " + sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("login: "+login+" name: "+name+" address: "+address+" phone_number: "+phone_number+" user_type: 0");
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query. Exception: " + e);
			return false;
		}
		return true;
	}

	public boolean addUser(Statement stmt)  {
		String sql="INSERT INTO Users (login, name, password, address, phone_number, user_type) VALUES"+
				"('"+this._login+"', '"+this._name+"', '"+this._password+"', '"+this._address+"', '"+this._phone_number+"', 0)";
		System.out.println("executing " + sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("login: "+this._login+" name: "+this._name+" address: "+this._address+" phone_number: "+this._phone_number+" user_type: 0");
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
			return false;
		}
		return true;
	}

	public String getUser(String login, Statement stmt)
	{
		String sql="select * from Users where login = '"+login+"'";
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
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

	public String getUser(String login, String password, Statement stmt)
	{
		String sql="select * from Users where login = '"+login+"' and password = '"+ password+"'";
		String output="";
		ResultSet rs=null;
		System.out.println("executing "+sql);
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
}
