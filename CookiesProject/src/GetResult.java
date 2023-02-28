

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetResult extends HttpServlet {
	String path = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String un = "system";
	String pass = "system";
	java.sql.Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PrintWriter pw = null;
	HttpSession session = null;
	String query = "select * from UniversityAbc where id=? and password=?";

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
				
				String new_id = Integer.toString(id1);
				String new_avg = Integer.toString(avg);
				//step1: Create the cookie object
				Cookie ck1 = new Cookie("id",new_id);
				Cookie ck2 = new Cookie("name",name1);
				Cookie ck3 = new Cookie("avg",new_avg);

				
				//step2: Set the expiry time of cookie
				ck1.setMaxAge(60*60*60);
				ck2.setMaxAge(60*60*60);
				ck3.setMaxAge(60*60*60);
				
				//step3: Send the cookie to client using response
				res.addCookie(ck1);
				res.addCookie(ck2);
				res.addCookie(ck3);
				pw.println( id1+" "+name1+" "+m1+" "+m2+" "+m3+" "+avg+" "+passwo);
			
				
			}
			else
			{
				res.sendRedirect("/UniversityAbc/invalid.html");

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
