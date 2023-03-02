

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Validation extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		
	try{
			
		String AccNo = req.getParameter("AccNo");
		String passw = req.getParameter("Password");
		
		
		if(AccNo.length() == 0 || passw.length() == 0)
		{
			res.sendRedirect("SBI/invalid.html");
		}
		else
		{
			req.getServletContext().getRequestDispatcher("/Login").forward(req,res);
		
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
}