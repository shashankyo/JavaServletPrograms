

import java.io.*;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.sun.corba.se.pept.transport.Connection;

public class GetResult extends HttpServlet {

		String path = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String pass = "system";
		String query = "select * from student";
		java.sql.Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		PrintWriter pw = null;
		public void init()
		{
			//Intialization logic
			try 
			{
				Class.forName(path);
				con = DriverManager.getConnection(url, user, pass);
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		public void service(HttpServletRequest req, HttpServletResponse res)
		{
			//Main logic
			
			try 
			{
				pw = res.getWriter();
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()==true)
				{
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int m1 = rs.getInt("m1");
					int m2 = rs.getInt("m2");
					int m3 = rs.getInt("m3");
					int avg = rs.getInt("avg");
					pw.println(id +" "+ name+" "+m1+" "+m2+" "+m3+" "+avg);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		public void destroy()
		{
			//Cleanup logic 
			try 
			{
				con.close();
				stmt.close();
				rs.close();
				pw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}


}
