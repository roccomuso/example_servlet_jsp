

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
        
        
        int id_utente = 0;
        
        if (session.getAttribute("id_utente") != null) id_utente = (int) session.getAttribute("id_utente");
        if (id_utente == 0) return "Session non presente...!";
        
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

                case "lista_ordini":
                    // id_utente preso dalla sessione passata.
                    // la stringa output viene sempre restituita dopo questa chiamata.
                    
                    output = "<table border='1'><thead>"
                            + "<tr><th># Ordine</th><th>Data ordine</th><th>Totale</th></tr></thead><tbody>";
                    
                    Statement getOrders = connection.createStatement();
                    ResultSet ordini = getOrders.executeQuery("SELECT * FROM ordini WHERE id_utente ="+id_utente);
                    
                    while(ordini.next()){
                    
                        output += "<tr><td>"+ordini.getInt("numero_ordine")+"</td><td>"+ordini.getString("data_ordine")+"</td><td>"+ordini.getInt("totale")+" €</td></tr>";
                    }
                    
                    output += "</tbody></table>";
                    
                    if (output.length() < 118) output = "<h2><u>Nessun ordine presente!</u></h2>";
                    
                break;
            
                case "profilo_utente":
                    // id_utente preso dalla sessione passata.
                    
                    output = "<h2>Ecco le info sul tuo profilo:</h2>";
                    
                    Statement getUserInfo = connection.createStatement();
                    ResultSet profilo = getUserInfo.executeQuery("SELECT * FROM utenti WHERE id_utente = "+id_utente);
                    
                    profilo.next(); // perchè il cursore inizialmente sta a -1
                    
                    output += "<table border='1'>"
                    + "<tr><td>ID Utente</td><td>"+profilo.getInt("id_utente")+"</td></tr>"
                    + "<tr><td>Username</td><td>"+profilo.getString("username")+"</td></tr>"
                    + "<tr><td>Email</td><td>"+profilo.getString("email")+"</td></tr>"
                    + "<tr><td>Nome</td><td>"+profilo.getString("nome")+"</td></tr>"
                    + "<tr><td>Cognome</td><td>"+profilo.getString("cognome")+"</td></tr>"
                    + "</table>";
                    
                break;
                    
              
                case "recensioni_da_lasciare":
                    // id_utente preso dalla sessione
                    
                    PreparedStatement getProductToReview = connection.prepareStatement("SELECT acquisti.id_prodotto, acquisti.numero_ordine, acquisti.quantita, catalogo.nome FROM acquisti JOIN catalogo ON (acquisti.id_prodotto = catalogo.id_prodotto) WHERE (acquisti.numero_ordine, acquisti.id_prodotto) NOT IN (SELECT numero_ordine, id_prodotto FROM recensioni WHERE id_utente = ?)");
                    getProductToReview.setInt(1, id_utente);
                    ResultSet da_recensire = getProductToReview.executeQuery();
                    
                    if (!da_recensire.next()){ // Non ci sono risultati
                        output = "<font color='green' size='8'>Nessuna recensione da lasciare!</font>";
                    }else{ // ci sono record da mostrare
                        
                        do{ // perchè già al .next() sopra il cursorse è stato spostato alla prima riga.
                        
                            output = "<fieldset>" +
"    <legend>Ci risulta che hai acquistato <em><b>"+da_recensire.getInt("quantita")+"</b></em> pezzi di <em><b>"+da_recensire.getString("nome")+"</b></em>, lascia una recensione!</legend>\n" +
"    <form action='lascia_recensione.jsp' method='POST'>"+
"    <input type='hidden' name='id_prodotto' value='"+da_recensire.getInt("id_prodotto")+"' />" +
"    <input type='hidden' name='numero_ordine' value='"+da_recensire.getInt("numero_ordine")+"' />" +
"    <input type='hidden' name='id_utente' value='"+id_utente+"' />" +
"        <textarea rows='10' cols='60' name='testo_recensione' placeholder='Scrivi la tua recensione...'></textarea>" +
"    <br/><fieldset>" +
"        <legend>Dai un voto al prodotto!</legend>" +
"        1 <input type='radio' name='stars' value='1' checked='checked'/>" +
"        2 <input type='radio' name='stars' value='2'/>" +
"        3 <input type='radio' name='stars' value='3'/>" +
"        4 <input type='radio' name='stars' value='4'/>" +
"        5 <input type='radio' name='stars' value='5'/>" +
"    </fieldset>"+
"    <br/><input type='submit' value='Invia recensione!' /></form>" +
"</fieldset><br/><br/>";
                        
                        }while(da_recensire.next());

                    }
                    
           
                break;
                    
                case "recensioni_lasciate":
                    // id_utente preso dalla sessione
                    
                    PreparedStatement getReviews = connection.prepareStatement("SELECT id_recensione, nome, recensioni.id_prodotto, data, testo_recensione, stars FROM recensioni JOIN catalogo ON (recensioni.id_prodotto = catalogo.id_prodotto) WHERE id_utente = ? ORDER BY data DESC");
                    getReviews.setInt(1, id_utente);
                    ResultSet recensioni = getReviews.executeQuery();
                    
                    if (!recensioni.next()){ // Nessuna recensione
                        output = "<u>Nessuna recensione lasciata!</u>";
                    }else{ // Stampiamo le recensioni presenti
                        
                        output = "<table border='1'><thead><tr><th># Recensione</th><th>Lasciata per</th><th>Recensione</th><th>Stelle 1/5</th><th>Data</th></tr></thead>";
                        
                        do{ // perchè già al .next() sopra il cursorse è stato spostato alla prima riga.
                            output += "<tr><td>"+recensioni.getInt("id_recensione")+"</td><td>"+recensioni.getString("nome")+"</td><td>"+recensioni.getString("testo_recensione")+"</td><td>"+recensioni.getInt("stars")+"</td><td>"+recensioni.getString("data")+"</td></tr>";
                        }while(recensioni.next());
                    
                        output += "</tbody></table>";
                    
                    }
                    

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
