

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetResult extends HttpServlet {

	String path = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String un = "system";
	String pass = "system";
	java.sql.Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PrintWriter pw = null;
	String query = "select * from UniversityResult where id=? and password=?";

	public void init()
	{
		//Intialization logic
		try 
		{
			Class.forName(path);
			con = DriverManager.getConnection(url, un, pass);
			
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
			String id = req.getParameter("id");
			String passw = req.getParameter("password");
			pw = res.getWriter();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, passw);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				int id1 = rs.getInt("id");
				String name1 = rs.getString("name");
				int m1 = rs.getInt("marks1");
				int m2 = rs.getInt("marks2");
				int m3 = rs.getInt("marks3");
				int avg = rs.getInt("avg");
				String passwo = rs.getString("password");
				pw.println( id1+" "+ ""+name1+" "+m1+" "+m2+" "+m3+" "+avg+" "+passwo);
			}
			else
			{
				res.sendRedirect("/UniversityResult/login.html");

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
			pstmt.close();
			rs.close();
			pw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
