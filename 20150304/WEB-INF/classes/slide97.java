// A simple servlet to process get requests.
import javax.servlet.*; //for ServletException
import javax.servlet.http.*; //for HttpServlet
import java.io.*; //for PrintWriter (and IOException)
import java.util.*;

public class slide97 extends HttpServlet {
    // process "get" requests from clients
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
    {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	// send XHTML page to cli ent
	// start XHTML document
	out.println("<?xmlversion=\"1.0\"?>");
	out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD " +
		    "XHTML 1.0 Strict//EN\" \"http://www.w3.org" +
		    "/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
	out.println("<html xmlns = \"http://www.w3.org/1999/xhtml \">");
	// head section of document
	out.println( "<head>" );
	out.println( "<title>A Simple Servlet Example</title>" );
	out.println( "</head>" );
	// body section of document
	out.println( "<body>" );
	out.println( "<h1>Welcome to Servlets!</h1>" );
	out.println( "<p>information sent:</p>" );
	Enumeration paramNames = request.getParameterNames();
	while(paramNames.hasMoreElements()) {
	    out.print((String)paramNames.nextElement() + "\n");
	}
	out.println( "</body>" );
	// end XHTML document
	out.println( "</html>" );
	out.close();
	// close stream to complete the page
	// int k = 0;
	// for (int e=0; e < 500; e++){
	//     for (int j=0; j < 10000000; j++){
	// 	for (int i=0; i < 100000000; i++) {
	// 	    k++;
	// 	}
	//     }
	// }
    }
}
