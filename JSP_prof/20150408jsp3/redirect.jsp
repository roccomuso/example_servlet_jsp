<%-- 3 MODI PER EFFETTUARE UN REDIRECT --%>

<%@page import=" java.io.*"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%!

    protected void doGet( HttpServletRequest request,
			  HttpServletResponse response, JspWriter out )
	throws ServletException, IOException
    {
	String location = request.getParameter( "page" );
	if ( location != null )
	    if ( location.equals( "deitel" ) )
		response.sendRedirect( "http://www.deitel.com" );
	    else
		if ( location.equals( "welcome1" ) )
		    response.sendRedirect( "welcome1.html" );
		else if ( location.equals( "welcome2" ) ) {
		//OutputStream os = response.getOutputStream();
		//os.flush();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome2.html"); //OK
			    //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome2.html"); //OK
	            dispatcher.forward(request, response);
		} // OCCHIO ALL'URL!!!!!!!
		else if ( location.equals( "welcome3" ) ) {
%>
<%--<jsp:forward page="welcome3.html"/>--%>
<%!
		}
}
%>

<% doGet(request, response, out); %>
<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns = "http://www.w3.org/1999/xhtml">
<head>
<title>Invalid page</title>
</head>
<body>
<h1>Invalid page requested</h1>
<p><a href = "examples/slide126.html">
Click here to choose again</a></p>
</body>
</html>
