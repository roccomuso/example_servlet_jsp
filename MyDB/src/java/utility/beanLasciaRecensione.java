package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Descrizione della classe.....
 * @author Rocco
 * 
 */
public class beanLasciaRecensione {
    
    private String testo_recensione;
    private int stars;
    private int id_prodotto;
    private int id_utente;
    private int numero_ordine;

    public beanLasciaRecensione() { // Costruttore vuoto
    }
    
    // GETTER and SETTER
    
    public int getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    public int getNumero_ordine() {
        return numero_ordine;
    }

    public void setNumero_ordine(int numero_ordine) {
        this.numero_ordine = numero_ordine;
    }


    public String getTesto_recensione() {
        return testo_recensione;
    }
    
    public int getStars() {
        return stars;
    }

    public void setTesto_recensione(String testo_recensione) {
        this.testo_recensione = testo_recensione;     
        
    }

    public void setStars(int stars) {
        this.stars = stars;
         
    }

    /**
     * Questo metodo permette di salvare nel DB la recensione ricevuta.
     *
    */
    public String salva_in_db(){ // Chiamato dalla pagina JSP che usa il Bean. (lascia_recensione.jsp)
        
        Connection connection;
        String error = null;
        String output = "Salvataggio non riuscito...";
        
        try {
            // Connessione al DB MySQL
	    Class.forName("com.mysql.jdbc.Driver");
	    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/esempi_servlet", "root", "root"); // db - user - pass
	    
	    // query:
	    PreparedStatement putReviewQuery = connection.prepareStatement("INSERT INTO `recensioni`(`numero_ordine`, `id_prodotto`, `testo_recensione`, `id_utente`, `stars`, `data`) VALUES (?,?,?,?,?,?)");
	    
            putReviewQuery.setInt(1, getNumero_ordine());
            putReviewQuery.setInt(2, getId_prodotto());
            putReviewQuery.setString(3, getTesto_recensione());
            putReviewQuery.setInt(4, getId_utente());
            putReviewQuery.setInt(5, getStars());
            
            // data attuale
            String data_attuale = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            
            putReviewQuery.setString(6, data_attuale);
            
            // Eseguiamo la query
            putReviewQuery.executeUpdate();
            
            output = "<font size='10' color='green'>Recensione lasciata, Grazie!</font>";
            
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
                
               if (error != null) return error;
               
              return output; 
    }
    
    
}
