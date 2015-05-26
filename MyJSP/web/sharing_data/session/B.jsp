
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sessioni: B</title>
    </head>
    <body>
        <%
        
        // Riprendiamo gli attributi definiti in A.jsp
        if (session != null && session.getAttribute("oggetto") != null){ // perchè ogni volta che si chiama una jsp, la sessione viene sempre creata.
            
            varie.Animale gatto = (varie.Animale) session.getAttribute("oggetto");
            Map<String, String> mappa = (HashMap) session.getAttribute("mappa");
         
        %>
        
        Il gatto ha <% out.println(gatto.getZampe()); %> zampe.
        
        <br/><br/>
        
        La mappa contiene:
        
        <%
        
        for (Entry a: mappa.entrySet())
            out.println(a.getKey()+" - "+a.getValue());
        
        
        }
        %>
        
    </body>
</html>
