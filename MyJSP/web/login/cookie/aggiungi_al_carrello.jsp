<%-- 
    La classe CookieTools importata serve a controllare se il login è stato effettuato, come?
    controllando semplicemente se è presente un cookie di nome 'loggato'.
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="varie.CookieTools"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prodotto nel carrello!</title>
    </head>
    <body>

        
        
        <%
        
            Cookie[] cookies = request.getCookies();
            Map<String, String> COOKIES = CookieTools.convert_Cookie_to_Map(cookies);
            String item = request.getParameter("item");
            
            if (CookieTools.isSignedIn(cookies))            
            if (item == null)
                out.print("Errore, è necessario passare un parametro item.");
            else{
                
                // se già esiste il cookie con il nome dell'item, sommarvi 1. Altrimenti inizializzare ad 1.
                if (CookieTools.isSet(item, cookies))
                    response.addCookie(new Cookie(item, String.valueOf(Integer.parseInt(COOKIES.get(item))+1)));
                else
                    response.addCookie(new Cookie(item, "1")); // Cookie: oggetto - quantita
                // . . .
                
        %>
        
        <h3>Prodotto aggiuto!</h3>
        <a href="home.jsp">Torna al catalogo</a>.
        
        <%
            }
            else
                out.print("Spiacente, assicurati di aver effettuato il <a href='index.jsp'>login</a>.");
        %>
    </body>
</html>
