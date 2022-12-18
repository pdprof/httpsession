package pdprof.httpsession;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PdprofSession
 */
@WebServlet("/session")
public class PdprofSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdprofSession() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer count = (Integer)session.getAttribute("count");
		if (count == null)
			count = new Integer(0);
		else 
			count = new Integer(count.intValue() + 1);
		session.setAttribute("count", count);
		Date now = new Date();
		session.setAttribute("now", now);
		response.getWriter().append("Session Id: ").append(session.getId()).append("\ncount: ").append(count.toString())
		.append("\nnow: ").append(now.toLocaleString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
