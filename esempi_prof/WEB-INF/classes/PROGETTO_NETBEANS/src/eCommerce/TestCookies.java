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
	else
	    res.sendRedirect
		(( req.getCookies() == null || req.getCookies().length == 0 ) ? "html_prof/failure.html" : "html_prof/success.html"
		 );
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	doPost(req, res);
    }
}
