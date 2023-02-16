

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class display extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		try {
			res.sendRedirect("/FirstServerProject1/display");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
