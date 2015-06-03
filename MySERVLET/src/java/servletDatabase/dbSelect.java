/*
 * Esempi di query verso un DB MySQL.
 */

package servletDatabase;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rocco
 */
public class dbSelect extends HttpServlet {

    private Connection connection;
    private PreparedStatement getBooksQuery, putBooksQuery, updateBooksQuery, deleteBooksQuery; // 'preparedStatement' per eseguire comandi SQL parametrizzati, altrimenti per un comando SQL semplice va bene 'statement'
    
    private String error = null;
    
    // Configuriamo la connessione al DB e prepariamo le query SQL
    @Override
    public void init( ServletConfig config )throws ServletException{
	// attempt database connection and create PreparedStatements
	try {
            // Connessione al DB MySQL
	    Class.forName("com.mysql.jdbc.Driver");
	    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/delete_me", "root", "root"); // db - user - pass
	    
	    // Le varie query:
	    getBooksQuery = connection.prepareStatement("SELECT * FROM libri");
            putBooksQuery = connection.prepareStatement("INSERT INTO libri(nome_libro, autore, quantita, data) VALUES (?,?,?,?)");
            updateBooksQuery = connection.prepareStatement("UPDATE libri SET quantita = ? WHERE id_libro = ?");
            deleteBooksQuery = connection.prepareStatement("DELETE FROM libri WHERE id_libro = ?");
	    
            
	}
	// for any exception throw an UnavailableException to
	// indicate that the servlet is not currently available
	catch ( SQLException e ) {
	    error = "SQL problem" + e.getMessage();
	    // throw new UnavailableException( "SQL problem" + e.getMessage() );
	}
	// detect problems loading database driver
	catch ( ClassNotFoundException e ) {
	    error = "Unable to load driver" + e.getMessage();
	    // throw new UnavailableException( "Unable to load driver" + e.getMessage() );
	}
	catch ( Exception exception ) {
	    error = "Generic error " + exception.getMessage();
	    // exception.printStackTrace();
	    // throw new UnavailableException( "Generic error " + exception.getMessage() );
	}
    }
    // fine del metodo init
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
 
        PrintWriter out = response.getWriter();

        if (error != null) out.print(error);
                   
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dbSelect</title>");            
            out.println("</head>");
            out.println("<body>");
            
           
	try {
	    
            // Query di UPDATE
	    updateBooksQuery.setInt(1, 5); // Settiamo il 1 parametro con ? nella query a 5.
            updateBooksQuery.setInt(2, 3); // Settiamo il 2 parametro con ? nella query a 3.
	    updateBooksQuery.executeUpdate(); // quindi la query di UPDATE finale verrebbe: UPDATE libri SET quantita = 5 WHERE id_libro = 3
                            // quando si 'manipolano' dati si usa executeUpdate e non executeQuery!
            
            // Query di INSERT
            putBooksQuery.setString(1, "Il signore degli anelli"); // nome
            putBooksQuery.setString(2, "J.R. Tolkien"); // autore
            putBooksQuery.setInt(3, 4);// quantita
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            putBooksQuery.setString(4, timeStamp); // data
            
            putBooksQuery.executeUpdate();
            
            // Query di DELETE
            deleteBooksQuery.setInt(1, 4); // Elimina la tupla con id=4
            deleteBooksQuery.executeUpdate(); // siccome si 'manipolano' dati si usa executeUpdate, si usa per INSERT, UPDATE, DELETE.
            
            // Query di SELECT
	    ResultSet result = getBooksQuery.executeQuery();
	    
                // mostriamo i risultati
	    
	    while ( result.next() ) { // il next va subito avanti, perchè result alla prima iterazione ha il cursore che punta a prima della prima riga (-1), FONDAMENTALE!
                 // getInt("nome_colonna") oppure getInt(indice colonna); se è getInt restituisce un intero.
                 // Entrambi i metodi getInt o getString accettano il nome della colonna o l'indice della colonna, e restituiscono il tipo corrispondente.
                    
                    out.print(result.getInt("id_libro")+" - "); // getInt perchè id_libro è di tipo intero.
                    out.print(result.getString("nome_libro")+" - "); // Nome della colonna (attributo) oppure possiamo passare l'indice della colonna, in qst caso 2
                    out.print(result.getInt("quantita")+" - ");
                    out.print(result.getDate("data")+" <br/> "); // getDate perchè data è di tipo Data.
                    
            }
	    result.close();
	    
	}
	// Se c'è un'eccezione mostriamo la pagina d'errore:
	catch ( SQLException sqlException ) {
	    
	    out.println( "<title>Error</title></head>" );
	    out.println( "<body><p>Database error occurred: "+sqlException.getMessage() );
	    out.println( "<br/>Try again later.</p></body></html>" );
	    out.close();
	}
            
                    
            out.println("</body>");
            out.println("</html>");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy(){ // Elimina ogni preparedStatement e chiude tutte le connessioni
        
        try {
        
            getBooksQuery.close();
            putBooksQuery.close();
            deleteBooksQuery.close();
            updateBooksQuery.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbSelect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public String getServletInfo() { // Descrizione servlet
        return "Esempi di query verso un DB MySQL";
    }

}
