

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PrintPercentage extends HttpServlet {
	
	
	PrintWriter pw = null;
	HttpSession session = null;

	
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		//Main logic
		
		try 
		{
			//Getting the PrintWriter
			pw = res.getWriter();
			//Getting access to the Existing session
			session = req.getSession();
			//Get the data from the session 
			int m1 = (int)session.getAttribute("marks1");
			int m2 = (int)session.getAttribute("marks2");
			int m3 = (int)session.getAttribute("marks3");
			
			//Calculate the percentage
			int sum = m1+m2+m3;
			float per = (float)(sum/300.0f)*100;
			
			//Printing the percentage
			pw.println("The percentage is "+per);
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
		}
	}
	

}
