

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Validation extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		
	try{
			
		String id = req.getParameter("id");
		String passw = req.getParameter("password");
		
		
		if(id.length() == 0 || passw.length() == 0)
		{
			res.sendRedirect("/UniversityAbc/invalid.html");
		}
		else
		{
			req.getServletContext().getRequestDispatcher("/GetResult").forward(req,res);
		
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
