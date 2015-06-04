

package utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
        Carrello cart = (Carrello) session.getAttribute("carrello");
        int id_utente = (int) session.getAttribute("id_utente");
                
        try {
                       
            switch(cmd){ // Switch su string dalle classi Java funziona. (JDK >= 1.7) (Stranamente dal JSP no e bisogna ripiegare su enum).

                case "completa_acquisto":
                    // da session si ricava id_utente e carrello.
                    
                    
                    if (cart != null && id_utente != 0 && !cart.prodotti.isEmpty()){
                    // ottenuti i dati si inserisce nella tabella <ordini> la entry dell'ordine.
                        String lista_id_prodotti = "";
                    
                    // prezzo totale
                    lista_id_prodotti = lista_id_prodotti_da_carrello(cart.prodotti);
                    Statement query_prezzo_totale = connection.createStatement();
                    String qry = "SELECT id_prodotto, prezzo FROM catalogo WHERE id_prodotto IN ("+lista_id_prodotti+")";
                    ResultSet rs = query_prezzo_totale.executeQuery(qry);
                    int totale = 0;
                    while(rs.next())
                        for (Entry<Integer,Integer> e: cart.prodotti.entrySet())
                            if (e.getKey().equals(rs.getInt("id_prodotto")))
                                totale = totale + e.getValue() * rs.getInt("prezzo"); // quantita' x prezzo
                    
                                        
                    // data attuale
                    String data_attuale = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                    
                    // Inseriamo ordine e prendiamo l'id dell'ordine appena immesso
                    Statement queryImmettiOrdine = connection.createStatement();
                    String query = "INSERT INTO `ordini`(`data_ordine`, `id_utente`, `totale`) VALUES ('"+data_attuale+"','"+id_utente+"','"+totale+"')";
                    queryImmettiOrdine.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                    
                    ResultSet id_generati = queryImmettiOrdine.getGeneratedKeys();
                    int id_ordine_immesso = 0;
                    if (id_generati.next()) id_ordine_immesso = id_generati.getInt(1); // prendiamo l'id appena generato per l'ordine
                    
                    if (id_ordine_immesso == 0) return "Impossibile generare un ordine!";
                        
                    // poi si inserisce per ogni prodotto e quantità (default:1) una entry nella tabella <acquisti>, col numero d'ordine appena generato.
                    Statement queryImmettiAcquisto = connection.createStatement();
                    for (Entry<Integer,Integer> e: cart.prodotti.entrySet()){
                        query = "INSERT INTO `acquisti`(`numero_ordine`,`id_prodotto`,`quantita`) VALUES("+id_ordine_immesso+","+e.getKey()+","+e.getValue()+")";
                        queryImmettiAcquisto.executeUpdate(query);
                    }
                    
                    // svuotiamo il carrello, ordine ormai completato.
                    session.removeAttribute("carrello");
                    
                    output = "<h3>Ordine immesso con successo!</h3><a href='home.jsp'>Torna alla Home</a>";
                    
                    
                    }else output = "Carrello non esistente o nessun prodotto nel carrello!";
                break;

                case "stampa_carrello":
                    // da session ricaviamo il carrello.
                    

                    if (cart == null) return "Carrello non esistente!";

                    if (cart.prodotti.isEmpty()) return "Carrello vuoto!";
                    
                    String lista_id_prodotti = "";

                    lista_id_prodotti = lista_id_prodotti_da_carrello(cart.prodotti); // lista id prodotti nel carrello separati da virgola
                    
                    
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

    public static String lista_id_prodotti_da_carrello(Map<Integer,Integer> prodotti){ // dato il carrello prodotti restituisce gli id separati da virgola presenti all'interno.
    
        String lista_id_prodotti = "";
    for (Map.Entry e: prodotti.entrySet())
                        lista_id_prodotti = lista_id_prodotti + e.getKey() + ",";

                    lista_id_prodotti = lista_id_prodotti.substring(0, lista_id_prodotti.lastIndexOf(",")); // rimuoviamo virgola finale.
                    
    return lista_id_prodotti;
                    
    }
    
}
