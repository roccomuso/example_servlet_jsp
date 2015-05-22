
<%-- ESEMPIO SEMPLICE REDIRECT --%>

<%
	String location = request.getParameter( "page" );
	if ( location != null )
	    if ( location.equals( "deitel" ) )
		response.sendRedirect( "http://www.deitel.com" );
	    else
		if ( location.equals( "welcome1" ) )
		    response.sendRedirect( "/20150304/nonesiste/alias_servlet_di_saluto" );
		    // response.sendRedirect( "nonesiste/alias_servlet_di_saluto" );
	// codice che viene eseguito solo se questa servlet non riesce a redirigere
	// la richiesta come voluto
	// start XHTML document
%>
<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns = "http://www.w3.org/1999/xhtml">
<head>
<title>Invalid page</title>
</head>
<body>
<h1>Invalid page requested</h1>
<p><a href = "slide126.html">
Click here to choose again</a></p>
</body>
</html>

