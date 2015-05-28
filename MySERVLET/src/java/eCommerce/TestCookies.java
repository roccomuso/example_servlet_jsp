// Aggiungiamo Package, perchè gli esempi del prof son compilati da linea di comando e non da NetBeans.
package eCommerce;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


public class TestCookies extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	if (req.getParameter( "param" ) == null) {
	    Cookie cookie = new Cookie( "qualcosa" , "qualcosa" );
	    res.addCookie(cookie);
	    res.sendRedirect(req.getRequestURI() + "?param=qualcosa");
	}
        else{
            
            String esito = ( req.getCookies() == null || req.getCookies().length == 0 ) ? "Failure" : "Success";
        res.setContentType("text/html"); // Altrimenti non viene interpretato il codice html! apparirà come semplice testo!
        PrintWriter out = res.getWriter();
        out.print("<h3>"+esito+"</h3>");
        
	     // Stampiamo Success o Failure, se i cookie son attivi o meno.
    }
    
}

    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	doPost(req, res);
    }
}
