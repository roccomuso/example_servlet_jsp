package profSessionCookie;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class TestCookiesSession extends HttpServlet {

    private static final String successURI = "html_prof/success.html";
    private static final String failureURI = "html_prof/failure.html";

    private void handleWrong(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	res.sendRedirect(req.isRequestedSessionIdFromURL() ? failureURI : successURI );
    }

    private void handleCorrect(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	HttpSession session = req.getSession( true );
	if ( session.isNew() ) 
	    res.sendRedirect(res.encodeURL(req.getRequestURI()));
	else 
	    res.sendRedirect(req.isRequestedSessionIdFromURL() ? failureURI : successURI );
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
