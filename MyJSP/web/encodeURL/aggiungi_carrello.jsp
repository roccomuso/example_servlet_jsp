<%-- 
    Aggiungiamo un prodotto al carrello. (Sessioni).
--%>

<%@page import="javax.persistence.Entity"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="varie.Carrello"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aggiungi al Carrello</title>
    </head>
    <body>
        <%!
        
           // Classe Carrello definita in varie.Carrello. (guarda import sopra).
        
        %>
        
        <%
        
        Carrello current_cart = (Carrello) session.getAttribute("carrello");
        
        String prodotto = request.getParameter("item");
        
      if (prodotto != null){ // se il parametro passato esiste
        if (current_cart != null){
            // carrello già esistente, aggiorniamo con i prodotti da aggiungere.
            
            current_cart.aggiungi(prodotto, 1); // aggiungiamo il prodotto richiesto.
            
            session.setAttribute("carrello", current_cart); // sovrascriviamo il carrello esistente col nuovo aggiornato.
            
        }else{
            // creiamo il carrello e lo impostiamo come attributo di sessione.
            
            Carrello new_cart = new Carrello();
            
            // aggiungiamo il prodotto richiesto
            new_cart.aggiungi(prodotto, 1);
            
            session.setAttribute("carrello", new_cart);
            
        }
      
        out.print("Prodotto inserito! <a href='"+response.encodeURL("index.jsp")+"'>Torna alla Home</a>.");
      }else out.print("Passare un parametro item! <a href='"+response.encodeURL("index.jsp")+"'>Riprova</a>.");
        
        
        %>
    </body>
</html>
