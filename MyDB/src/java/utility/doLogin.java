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
public class doLogin extends HttpServlet {

        private Connection connection;
    private PreparedStatement getUserQuery;
    
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
	    getUserQuery = connection.prepareStatement("SELECT *, COUNT(*) AS esiste FROM utenti WHERE username = ? AND password = ?");
	    
            
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
    
    // processa entrambi i metodi GET e POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
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
            
            ResultSet result = getUserQuery.executeQuery();
            if (result.getInt("esiste") == 0){
                // Login non riuscito
                
            }else{
                // Login riuscito
            
            }
            
            out.println("</body>");
            out.println("</html>");
            
        }   catch (SQLException ex) {
                Logger.getLogger(doLogin.class.getName()).log(Level.SEVERE, null, ex);
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
