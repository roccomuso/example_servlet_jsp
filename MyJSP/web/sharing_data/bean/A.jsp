
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        
            varie.Animale gatto = new varie.Animale("gatto", 4, true);
            session.setAttribute("oggetto", gatto); // Impostiamo un attributo di sessione
            
            synchronized(this){ // Perchè un attributo d'applicazione viene impostato dal primo utente che visita questa pagina e poi è visibile a tutti gli altri! Il Synchronized evita esecuzioni SIMULTANEE che potrebbero portare a comportamenti non voluti.
            Map<String, String> map = new HashMap();
            map.put("Rocco", "12345678");
            getServletContext().setAttribute("oggetto_mappa", map); // Impostiamo un attributo d'applicazione
            }
            
            out.print("Vai alla <a href='B.jsp'>pagina B</a>.");
        
        %>
    </body>
</html>
