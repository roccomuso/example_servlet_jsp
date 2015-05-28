package eCommerce;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

// Stampiamo Success o Failure, in base al fatto che il client supporti i cookie (e il server le sessioni) o meno.

public class TestCookiesSession extends HttpServlet {

    

    private void handleWrong(HttpServletRequest req, HttpServletResponse res) // metodo non utilizzato
	throws ServletException, IOException
    {
	
        res.setContentType("text/html"); // Altrimenti non viene interpretato il codice html! apparirà come semplice testo!
        
        String esito = (req.isRequestedSessionIdFromURL()) ? "Failure" : "Success";
        
        PrintWriter out = res.getWriter();
        out.print("<h3>"+esito+"</h3>");
        
    }

    private void handleCorrect(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	HttpSession session = req.getSession( true );
	if ( session.isNew() ) 
	    res.sendRedirect(res.encodeURL(req.getRequestURI()));
	else {
            res.setContentType("text/html"); // Altrimenti non viene interpretato il codice html! apparirà come semplice testo!
            
	    String esito = (req.isRequestedSessionIdFromURL()) ? "Failure" : "Success";
        
            PrintWriter out = res.getWriter();
            out.print("<h3>"+esito+"</h3>");
        }
        
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	handleCorrect(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	handleCorrect(req, res);
    }
}
