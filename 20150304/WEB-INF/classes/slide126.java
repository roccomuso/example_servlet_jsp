// Redirecting a user to a different Web page.
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class slide126 extends HttpServlet {
    protected void doGet( HttpServletRequest request,
			  HttpServletResponse response )
	throws ServletException, IOException
    {
	String location = request.getParameter( "page" );
	if ( location != null )
	    if ( location.equals( "deitel" ) )
		response.sendRedirect( "http://www.deitel.com" );
	    else
		if ( location.equals( "welcome2" ) )
		    response.sendRedirect( "/20150304/nonesiste/alias_servlet_di_saluto" );
		    // response.sendRedirect( "nonesiste/alias_servlet_di_saluto" );
	// codice che viene eseguito solo se questa servlet non riesce a redirigere
	// la richiesta come voluto
	response.setContentType( "text/html" );
	PrintWriter out = response.getWriter();
	// start XHTML document
	out.println( "<?xml version = \"1.0\"?>" );
	out.println( "<!DOCTYPE html PUBLIC \"-//W3C//DTD " +
		     "XHTML 1.0 Strict//EN\" \"http://www.w3.org" +
		     "/TR/xhtml1/DTD/xhtml1-strict.dtd\">" );
	out.println(
		    "<html xmlns = \"http://www.w3.org/1999/xhtml\">" );
	// head section of document
	out.println( "<head>" );
	out.println( "<title>Invalid page</title>" );
	out.println( "</head>" );
	// body section of document
	out.println( "<body>" );
	out.println( "<h1>Invalid page requested</h1>" );
	out.println( "<p><a href = " +
		     "\"examples/slide126.html\">" );
	out.println( "Click here to choose again</a></p>" );
	out.println( "</body>" );
	// end XHTML document
	out.println( "</html>" );
	out.close(); // close stream to complete the page
    }
}
