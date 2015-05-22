import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import example.*;
 
public class passBean extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
    {
	if (request.getParameter("request") != null) {
	    esempio4 ex4 = new esempio4(5);
	    request.setAttribute("eccolo", ex4);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/beans4_ok.jsp");
	    dispatcher.forward(request, response);
	}
	else if (request.getParameter("application") != null) {
	    synchronized(this) {
		if (getServletContext().getAttribute("applicazione") == null) {
		    esempio4 ex4 = new esempio4(50);
		    getServletContext().setAttribute("applicazione", ex4);
		}
		else {
		    esempio4 ex4 = (esempio4)getServletContext().getAttribute("applicazione");
		    ex4.setProprieta_privata(ex4.getProprieta_privata() + 1);
		    getServletContext().setAttribute("applicazione", ex4);
		}
	    }
	}
	else if (request.getParameter("session") != null) {
	    HttpSession session = request.getSession(true);
	    if (session.isNew() || session.getAttribute("sessione") == null) {
		esempio4 ex4 = new esempio4(30);
		session.setAttribute("sessione", ex4);
	    }
	    else {
		esempio4 ex4 = (esempio4)session.getAttribute("sessione");
		ex4.setProprieta_privata(ex4.getProprieta_privata() + 1);
		session.setAttribute("sessione", ex4);
	    }
	}
	else {
	    esempio8 ex8 = new esempio8(5, 100);
	    request.setAttribute("eccolo", ex8);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/beans8.jsp");
	    dispatcher.forward(request, response);
	}
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
    {
	doPost(request, response);
    }
}
