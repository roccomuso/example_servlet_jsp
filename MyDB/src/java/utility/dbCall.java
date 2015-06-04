

package utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.http.HttpSession;
import strutture_dati.Carrello;


public class dbCall {
    
    private static Connection connection;
    private static PreparedStatement getUserQuery = null;
    
    private static String error = null;
    private static String content = null;
    

    // Configuriamo la connessione al DB e prepariamo le query SQL
        
    public static void init( ){
	// attempt database connection and create PreparedStatements
	try {
            // Connessione al DB MySQL
	    Class.forName("com.mysql.jdbc.Driver");
	    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/esempi_servlet", "root", "root"); // db - user - pass
	    
	    // query:
	    getUserQuery = connection.prepareStatement("SELECT *, COUNT(*) AS esiste FROM utenti WHERE username = ? AND password = ?"); // va bene quando il risultato che ci si aspetta è solo 1.
	    
            
            
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
    // fine del metodo init
  
    // processa entrambi i metodi GET e POST
    public static String processRequest(String cmd, HttpSession session) {
       
        init();
                
        if (error != null) return error;
        
        String output = "";
                
        try {
                       
            switch(cmd){ // Switch su string dalle classi Java funziona. (JDK >= 1.7) (Stranamente dal JSP no e bisogna ripiegare su enum).

                case "completa_acquisto":
                    // da session si ricava id_utente e carrello.

                    // ottenuti i dati si inserisce nella tabella <ordini> la entry dell'ordine.
                    Statement queryImmettiOrdine = connection.createStatement();
                    String query = " . . .";
                    int id_immesso = queryImmettiOrdine.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);


                    // poi si inserisce per ogni prodotto e quantità (default:1) una entry nella tabella <acquisti>, col numero d'ordine appena generato.

                break;

                case "stampa_carrello":
                    // da session ricaviamo il carrello.
                    Carrello cart = (Carrello) session.getAttribute("carrello");

                    if (cart == null) return "Carrello non esistente!";

                    if (cart.prodotti.isEmpty()) return "Carrello vuoto!";
                    
                    String lista_id_prodotti = "";

                    for (Map.Entry e: cart.prodotti.entrySet())
                        lista_id_prodotti = lista_id_prodotti + e.getKey() + ",";

                    lista_id_prodotti = lista_id_prodotti.substring(0, lista_id_prodotti.lastIndexOf(",")); // rimuoviamo virgola finale.
                    
                    // Otteniamo il nome dei prodotti, interrogando il DB
                    PreparedStatement queryProdotti = connection.prepareStatement("SELECT id_prodotto, nome FROM catalogo WHERE id_prodotto IN ("+lista_id_prodotti+")");
                    //queryProdotti.setString(1, lista_id_prodotti);
                    ResultSet nomi_prodotti_carrello = queryProdotti.executeQuery();

                    // stampiamo il carrello.
                    output = "<ul>";
                        while (nomi_prodotti_carrello.next()){
                            int quantita = cart.prodotti.get(nomi_prodotti_carrello.getInt("id_prodotto"));
                            output = output +"<li>ID: "+ nomi_prodotti_carrello.getInt("id_prodotto")+ " - Prodotto: " + nomi_prodotti_carrello.getString("nome")+" - Quantità: "+quantita+" # [<a href='./operazioniSulCarrello?action=remove&prod="+nomi_prodotti_carrello.getInt("id_prodotto")+"'>Rimuovi</a>]</li>";
                        }
                        output += "</ul>";


                break;

            }
            
        }   catch (SQLException ex) {
                return ("SQLException: "+ex.getMessage());
            }
            catch(Exception e){
                return ("Eccezione: "+e.getMessage());
                
            }
    
        return output;
    
    }

    
}
