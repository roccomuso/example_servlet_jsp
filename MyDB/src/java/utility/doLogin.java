/*
 * Servlet che effettua il login controllando le credenziali etc.
 */

package utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rocco
 */
public class doLogin extends HttpServlet {

    private Connection connection;
    private PreparedStatement getUserQuery = null;
    
    private String error = null;
    
    // Configuriamo la connessione al DB e prepariamo le query SQL
        @Override
    public void init( ServletConfig config )throws ServletException{
	// attempt database connection and create PreparedStatements
	try {
            // Connessione al DB MySQL
	    Class.forName("com.mysql.jdbc.Driver");
	    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/esempi_servlet", "root", "root"); // db - user - pass
	    
	    // query:
	    getUserQuery = connection.prepareStatement("SELECT * FROM utenti WHERE username = ? AND password = ?");
	    
            
	}
	// for any exception throw an UnavailableException to
	// indicate that the servlet is not currently available
	catch ( SQLException e ) {
	    error = "SQL problem: " + e.getMessage();
	     //throw new UnavailableException( "SQL problem" + e.getMessage() );
	}
	// detect problems loading database driver
	catch ( ClassNotFoundException e ) {
	    error = "Unable to load driver: " + e.getMessage();
	     //throw new UnavailableException( "Unable to load driver" + e.getMessage() );
	}
	catch ( Exception exception ) {
	    error = "Generic error: " + exception.getMessage();
	    // exception.printStackTrace();
	     //throw new UnavailableException( "Generic error " + exception.getMessage() );
	}
    }
    // fine del metodo init
  
    // processa entrambi i metodi GET e POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (error != null) out.print(error);
        
        try {
                       
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet doLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String username = request.getParameter("username");
            getUserQuery.setString(1, username);
            String password = request.getParameter("password");
            getUserQuery.setString(2, password);
            
            ResultSet result = getUserQuery.executeQuery(); // result inizialmente punta sempre alla riga prima del risultato, è necessario ciclare con un result.next() o passare alla prima riga e analizzarla come si preferisce.
            //result.first(); // muoviamo il cursore alla prima riga dei risultati, inizialmente punta a nulla! FONDAMENTALE!

            if (!result.next()){
                // Login non riuscito
                out.print("<h2>Login non riuscito!</h2>");
                out.print("<a href='index.jsp'>Riprova</a>.");
            }else{
                // Login riuscito
                HttpSession session = request.getSession();
                session.setAttribute("id_utente", result.getInt("id_utente"));
                session.setAttribute("username", username);
                session.setAttribute("email", result.getString("email"));
                session.setAttribute("nome", result.getString("nome"));
                session.setAttribute("cognome", result.getString("cognome"));
                // redirect 
                response.sendRedirect("home.jsp");
            
            }

            
            out.println("</body>");
            out.println("</html>");
            
        }   catch (SQLException ex) {
                out.print("SQLException: "+ex.getMessage());
            }
            catch(Exception e){
                out.print("Eccezione: "+e.getMessage());
                
            }
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
    public String getServletInfo() {
        return "Servlet che processa il login";
    }

}
