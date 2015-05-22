<%-- ESEMPIO PER MANTENERE TRACCIA DI UNA SCELTA UTENTE USANDO I COOKIE (e non le sessioni) --%>

<%@page import="java.io.*, java.util.*"%>
<%@page session="false"%> <%-- Si imposta a false per migliorare le performance e ridurre l'uso di memoria, specie se la pagina non ha bisogno di usare le sessioni. (come un pagina about.jsp o faq.jsp) --%>

<%!
    private final Map<String,String> books = new HashMap<String,String>();
	
    public void jspInit(){ // Funzione di inizializzazione JSP. Richiamata al caricamento della pagina.
	books.put( "C", "0130895725" );
	books.put( "C++", "0130895717" );
	books.put( "Java", "0130125075" );
	books.put( "VB6", "0134569555" );
    }
	
    protected void doPost( HttpServletRequest request,
			   HttpServletResponse response )
	throws ServletException, IOException
    {
	String language = request.getParameter( "language" );
	String isbn = books.get( language ).toString(); // ottiene l'isbn del libro, dal nome passato dal form html
	String execinit = "";
	    Cookie cookie = new Cookie( language, isbn ); // aggiungiamo la scelta in un cookie per ricordarlo
	    cookie.setDomain(".localhost");
	    response.addCookie( cookie ); // deve precedere getWriter
	response.setContentType( "text/html" );
	PrintWriter out = response.getWriter();
	// Stampiamo il documento XHTML di risposta
	out.println( "<?xml version = \"1.0\"?>" );
	out.println( "<!DOCTYPE html PUBLIC \"-//W3C//DTD " +
		     "XHTML 1.0 Strict//EN\" \"http://www.w3.org" +
		     "/TR/xhtml1/DTD/xhtml1-strict.dtd\">" );
	out.println(
		    "<html xmlns = \"http://www.w3.org/1999/xhtml\">" );
	// header del documento
	out.println( "<head>" );
	out.println( "<title>Welcome to Cookies " + execinit + "</title>" );
	out.println( "</head>" );
	// body del documento
	out.println( "<body>" );
	out.println( "<p>Welcome to Cookies! You selected " +
		     language + "</p>" );
	out.println( "<p><a href = " +
		     "\"/20150401/slide140.html\">" +
		     "Click here to choose another language</a></p>" );
	out.println( "<p><a href = \"/20150401/cookies.jsp\">" +
		     "Click here to get book recommendations</a></p>" );
	out.println( "</body>" );
	// FINE documento
	out.println( "</html>" );
	out.close(); // chiudiamo lo stream
    }



    // leggiamo i cookies dal client e generiamo la pagina XHTML che contiene i libri raccomandati

    protected void doGet( HttpServletRequest request,
			  HttpServletResponse response )
	throws ServletException, IOException
    {
	Cookie cookies[] = request.getCookies(); // get cookies
	response.setContentType( "text/html" );
	PrintWriter out = response.getWriter();
	// creiamo la pagina XHTML di risposta
	out.println( "<?xml version = \"1.0\"?>" );
	out.println( "<!DOCTYPE html PUBLIC \"-//W3C//DTD " +
		     "XHTML 1.0 Strict//EN\" \"http://www.w3.org" +
		     "/TR/xhtml1/DTD/xhtml1-strict.dtd\">" );
	out.println(
		    "<html xmlns = \"http://www.w3.org/1999/xhtml\">" );
	// header della pagina
	out.println( "<head>" );
	out.println( "<title>Recommendations</title>" );
	out.println( "</head>" );
	// body della pagina
	out.println( "<body>" );
	// se ci sono cookie, stampa i libri salvati nei cookie
	if ( cookies != null && cookies.length != 0 ) {
	    out.println( "<h1>Recommendations</h1>" );
	    out.println( "<p>" );
	    // otteniamo il nome di ogni cookie
	    for ( int i = 0; i < cookies.length; i++ ) 
		out.println( cookies[ i ].getName() +
			     " How to Program. ISBN#: " +
			     cookies[ i ].getValue() + "<br />" );
	    out.println( "</p>" );
	}
	else { // se non ci sono cookie
	    out.println( "<h1>No Recommendations</h1>" );
	    out.println( "<p>You did not select a language.</p>" );
	}
	out.println( "</body>" );
	// FINE documento XHTML
	out.println( "</html>" );
	out.close(); // close stream
    }

%>

<%
if (request.getMethod().equals("GET")) // a seconda del metodo HTTP usato si chiama il metodo java corrispondente.
doGet(request, response);
else
doPost(request, response);
%>