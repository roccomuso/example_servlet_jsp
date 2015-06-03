<%-- 
    Pagina che elenca tutto il catalogo di prodotti presente nel DB.
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo prodotti</title>
    </head>
    <body>
        <%@include file="../check_session.jsp" %> <!-- incluso da tutte le pagine che devono essere protette. -->
        
        <h1>Catalogo prodotti</h1>
        
        <%!
        
        // Connessione al DB
        
    private Connection connection;
    private PreparedStatement getProductsQuery = null;
    
    private String error = null;
    
    // Configuriamo la connessione al DB e prepariamo le query SQL
        
    public void init(){
	// attempt database connection and create PreparedStatements
	try {
            // Connessione al DB MySQL
	    Class.forName("com.mysql.jdbc.Driver");
	    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/esempi_servlet", "root", "root"); // db - user - pass
	    
	    // query:
	    getProductsQuery = connection.prepareStatement("SELECT *, COUNT(*) AS esiste FROM catalogo");
	    
            
	}
	// for any exception throw an UnavailableException to
	// indicate that the servlet is not currently available
	catch ( SQLException e ) {
	    error = "SQL problem" + e.getMessage();
	     //throw new UnavailableException( "SQL problem" + e.getMessage() );
	}
	// detect problems loading database driver
	catch ( ClassNotFoundException e ) {
	    error = "Unable to load driver" + e.getMessage();
	     //throw new UnavailableException( "Unable to load driver" + e.getMessage() );
	}
	catch ( Exception exception ) {
	    error = "Generic error " + exception.getMessage();
	    // exception.printStackTrace();
	     //throw new UnavailableException( "Generic error " + exception.getMessage() );
	}
    }
             
        
        %>
        
        <%
        
               if (error != null) out.print(error);
        

            ResultSet result = getProductsQuery.executeQuery(); // result inizialmente punta sempre alla riga prima del risultato, è necessario ciclare con un result.next() o passare alla prima riga e analizzarla come si preferisce.
            
            result.first(); // muoviamo il cursore alla prima riga dei risultati, inizialmente punta a nulla! FONDAMENTALE!

            if (result.getInt("esiste") == 0){
                // Non esistono prodotti
                out.print("<font color='red' size='10'>Non esistono prodotti nel catalogo!</font>");
                
            }else{
                // Lista prodotti
                
                result.beforeFirst();// riportiamo indietro il cursore sul ResultSet.
                out.print("<table border='1'>");
                out.print("<thead><tr><th>ID</th><th>Nome</th><th>Descrizione</th><th>Disponibilità</th><th>Prezzo</th><th>Acquista</th></tr></thead><tbody>");
                while(result.next()){
                    
                    out.print("<tr><td>"+result.getInt("id_prodotto")+"</td><td>"+result.getString("nome")+"</td><td>"+result.getString("descrizione")+"</td><td>"+result.getInt("quantita")+"</td><td>"+result.getInt("prezzo")+" €</td><td><a href=''>Acquista</a></td></tr>");
                
                }
                out.print("</tbody></table><br/><br/>");
                
            }
            

        
        %>
    </body>
</html>
