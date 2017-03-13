package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;

public class TH {
		public TH()
		{}

		public String getTH(int hid, Statement stmt)
		{
			String sql="select * from TH where hid = "+hid;
			String output="";
			ResultSet rs=null;
   		 	System.out.println("executing "+sql);
   		 	try{
	   		 	rs=stmt.executeQuery(sql);
	   		 	while (rs.next())
				 {
					//System.out.print("cname:");
				        output+=rs.getString("hid") + "   "
								+ rs.getString("category") + "   "
								+ rs.getString("login") + "   "
								+ rs.getString("hname") + "   "
								+ rs.getString("address") + "   "
								+ rs.getString("url") + "   "
								+ rs.getString("phone_number") + "   "
								+ rs.getString("year_built") + "   "
								+ rs.getString("picture") + "   "
								+ rs.getString("pid")+ rs.getString("cost") +"\n";
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

	public void addTH(int hid, String category, String login, String hname, String address,
					  String url, String phone_number, String year_built, String picture, Statement stmt)
	{
		String sql="INSERT INTO TH (hid, category, login, hname, address," +
				"url, phone_number, year_built, picture) VALUES ("+hid+", "+category+", "+login+", "+hname+", "+address+","+url+", "
				+phone_number+", "+year_built+", "+picture+")";
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
