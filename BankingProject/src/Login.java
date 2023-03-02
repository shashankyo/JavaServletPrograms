

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {
	String path = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String un = "system";
	String pass = "system";
	java.sql.Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PrintWriter pw = null;
	String query = "select * from sbi where AccNo=? and Password=?";

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
			String AccNo = req.getParameter("AccNo");
			String passw = req.getParameter("Password");
			pw = res.getWriter();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, AccNo);
			pstmt.setString(2, passw);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				int AccNo1 = rs.getInt("AccNo");
				String Name = rs.getString("Name");
				
				String Email = rs.getString("Email");
				int Balance = rs.getInt("Balance");
				String passwo = rs.getString("Password");
				pw.println( AccNo1+" "+Name+" "+Email+" "+Balance+" "+passwo);
				
				
			}
			else
			{
				res.sendRedirect("/sbi/invalid.html");

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
