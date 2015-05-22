<%-- ESEMPIO PER MANTENERE TRACCIA DI UNA SCELTA UTENTE USANDO LE SESSIONI 
la pagina HTML fa una richiesta POST a questo script, che in doPost() nell'oggetto sessione, mette i libri scelti dall'utente e stampa tramite lo stesso doPost() la pagina HTML con l'elenco dei libri scelti.

In doGet() semplicemente invece si stampa l'elenco dei libri scelti se è presente la sessione.

miglioramento in termini di code refactoring: realizzare una procedura che faccia la stampa del documento XHTML in caso sia presente una sessione.

--%>

<%@page import="java.io.*, java.util.*"%>
<%!

    private final Map<String,String> books = new HashMap<String,String>();
    // inizializziamo la mappa con i libri.
	public void jspInit()
    {
	books.put( "C", "0130895725" );
	books.put( "C++", "0130895717" );
	books.put( "Java", "0130125075" );
	books.put( "VB6", "0134569555" );
    }
    // Riceviamo via POST i libri scelti e creiamo l'oggetto sessione, mostrando nella pagina i libri scelti.
	protected void doPost( HttpServletRequest request,
			       HttpServletResponse response, JspWriter out, HttpSession session )
	throws ServletException, IOException
    {
	String language = request.getParameter( "language" );
	// HttpSession session = request.getSession( true );
	session.setAttribute( language, books.get( language ) );
	response.setContentType( "text/html" );
	// PrintWriter out = response.getWriter();
	// Creiamo la pagina XHTML
	out.println( "<?xml version = \"1.0\"?>" );
	out.println( "<!DOCTYPE html PUBLIC \"-//W3C//DTD " +
		     "XHTML 1.0 Strict//EN\" \"http://www.w3.org" +
		     "/TR/xhtml1/DTD/xhtml1-strict.dtd\">" );
	out.println(
		    "<html xmlns = \"http://www.w3.org/1999/xhtml\">" );
	// header della pagina
	out.println( "<head>" );
	out.println( "<title>Welcome to Sessions</title>" );
	out.println( "</head>" );
	// body della pagina
	out.println( "<body>" );
	out.println( "<p>Welcome to Sessions! You selected " +
		     language + ".</p>" );
	// mostra le informazioni sulla sessione
	out.println( "<p>Your unique session ID is: " +
		     session.getId() + "<br />" );
	out.println(
		    "This " + ( session.isNew() ? "is" : "is not" ) +
		    " a new session<br />" );
	out.println( "The session was created at: " +
		     new Date( session.getCreationTime() ) + "<br />" );
	out.println( "You last accessed the session at: " +
		     new Date( session.getLastAccessedTime() ) + "<br />" );
	out.println( "The maximum inactive interval is: " +
		     session.getMaxInactiveInterval() + " seconds</p>" );
	out.println( "<p><a href = " +
		     "\"/20150401/slide159.html\">" +
		     "Click here to choose another language</a></p>" );
	out.println( "<p><a href = \"/20150401/session.jsp\">" +
		     "Click here to get book recommendations</a></p>" );
	out.println( "</body>" );
	// fine pagina XHTML
	out.println( "</html>" );
	out.close(); // close stream
    }
	
	
	
    // Se c'è una semplice richiesta GET di session.jsp mostriamo i libri scelti se esiste già una sessione.
    
    protected void doGet( HttpServletRequest request,
			  HttpServletResponse response, JspWriter out, HttpSession session )
	throws ServletException, IOException
    {
	// Otteniamo l'oggetto session dell'utente.
	// E non creiamo la sessione, se questa non esiste. (false).
	// HttpSession session = request.getSession( false );
	// get names of session object's values
	Enumeration valueNames;
	// if ( session != null )
	    valueNames = session.getAttributeNames();
	// else
	//     valueNames = null;
	// PrintWriter out = response.getWriter();
	response.setContentType( "text/html" );
	// inizio pagina XHTML di risposta
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
	if ( valueNames != null &&
	     valueNames.hasMoreElements() ) {
	    out.println( "<h1>Recommendations</h1>" );
	    out.println( "<p>" );
	    String name, value;
	    // otteniamo il valore per ogni nome in valueNames

	    while ( valueNames.hasMoreElements() ) {

		name = valueNames.nextElement().toString();

		value = session.getAttribute( name ).toString();


		out.println( name + " How to Program. " +

			     "ISBN#: " + value + "<br />" );

	    }


	    out.println( "</p>" );

	}

	else {

	    out.println( "<h1>No Recommendations</h1>" );

	    out.println( "<p>You did not select a language.</p>" );

	}


	out.println( "</body>" );


	// fine documento XHTML

	out.println( "</html>" );

	out.close(); // chiudiamo lo stream
    }

%>

<%
if (request.getMethod().equals("GET")) // a seconda del metodo HTTP usato si chiama il metodo java corrispondente.
    doGet(request, response, out, session);
else
    doPost(request, response, out, session);
%>