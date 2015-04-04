import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
 
public class slide210 extends HttpServlet {
    
    private String j = "POST";
	
    public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
    {
	if (request.getParameterValues("relativo_req") != null) {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("htmls/login.html"); //OK
	    request.setAttribute("ciao", "ciao");
	    dispatcher.forward(request, response);
	}
	else if (request.getParameterValues("relativo_cont") != null) {
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("htmls/login.html"); //NO!!!!
	    dispatcher.forward(request, response);
	}
	else if (request.getParameterValues("relativo_contesto_req") != null) {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/htmls/login.html"); //OK
	    dispatcher.forward(request, response);
	}
	else if (request.getParameterValues("relativo_contesto_cont") != null) {
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/htmls/login.html"); //OK
	    dispatcher.forward(request, response);
	}
	else if (request.getParameterValues("relativo_server_req") != null) {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/20150318/htmls/login.html"); //NO!!!!!
	    dispatcher.forward(request, response);
	}
	else if (request.getParameterValues("relativo_server_cont") != null) {
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/20150318/htmls/login.html"); //NO!!!!!
	    dispatcher.forward(request, response);
	}
	else if (request.getParameterValues("include") != null) {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/htmls/login.html"); //OK
	    response.setContentType( "text/html" );
	    PrintWriter out = response.getWriter();
	    out.println(j);
	    dispatcher.include(request, response);
	    out.println("Ciao");
	    out.close();	
	}
	response.setContentType( "text/html" );
	PrintWriter out = response.getWriter();
	out.println("Ciao");
	out.close();	
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
    {
	j = "GET";
	doPost(request, response);
    }
}
