// Processing HTTP get requests containing data.
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class slide110 extends HttpServlet {
    // process "get" request from client
    protected void doGet( HttpServletRequest request,
			  HttpServletResponse response )
	throws ServletException, IOException
    {
	String firstName = request.getParameter( "firstname" );
	response.setContentType( "text/html" );
	PrintWriter out = response.getWriter();
	// send XHTML document to client
	// start XHTML document
	out.println( "<?xml version = \"1.0\"?>" );
	out.println( "<!DOCTYPE html PUBLIC \"-//W3C//DTD " +
		     "XHTML 1.0 Strict//EN\" \"http://www.w3.org" +
		     "/TR/xhtml1/DTD/xhtml1-strict.dtd\">" );
	out.println(
		    "<html xmlns = \"http://www.w3.org/1999/xhtml\">" );
	// head section of document
	out.println( "<head>" );
	out.println(
		    "<title>Processing get requests with data</title>" );
	out.println( "</head>" );
	// body section of document
	out.println( "<body>" );
	out.println( "<h1>Hello " + firstName + ",<br />" );
	out.println( "Welcome to Servlets!</h1>" );
	out.println( "</body>" );
	// end XHTML document
	out.println( "</html>" );
	out.close(); // close stream to complete the page
    }
}
