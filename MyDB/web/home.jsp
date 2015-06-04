<%-- 
    Home, visibile solo se si è loggati dentro.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="check_session.jsp" %> <!-- incluso da tutte le pagine che devono essere protette. -->
        
        <h2>Benvenuto nella sezione privata: 
            <a href='home.jsp?page=ordini'>Ordini</a> - <a href='home.jsp?page=profilo_utente'>Profilo Utente</a> - [<a href='home.jsp?page=logout'>Logout</a>]</h2>
        
        <p align="center">Da questa sezione puoi sfogliare il catalogo, effettuare acquisti e lasciare recensioni.</p>
    
        
        <%!
        private enum Scelta{ // Usato per il costrutto Switch Case con JDK < 1.7
            ordini, profilo_utente, logout, home;
        }
        %>
        <%            
        
        String pagina = (String) request.getParameter("page"); // pagina richiesta
          
        if (pagina == null)
                pagina = "home"; // per evitare che si faccia switch(null)
        
                Scelta scelta = null;
        
        for (Scelta x : Scelta.values())
            if (x.name().equals(pagina))
                scelta = Scelta.valueOf(pagina);
           

        
        if (scelta != null)
            switch (scelta) {

                case ordini:
        %>
            <jsp:include page="./includes/ordini.jsp" />
        <%
                break;
                case profilo_utente:
        %>
            <jsp:include page="./includes/profilo_utente.jsp" />
        <%
                break;
                case logout:
        %>
            <jsp:include page="./includes/logout.jsp" />
            <% response.sendRedirect(request.getContextPath()); // redirect alla context root. %>
        <%        
                break;
                case home:
        %>
            <jsp:include page="./includes/catalogo.jsp" />
            <hr/>
            <jsp:include page="./includes/carrello.jsp" />
            <hr/>
            <jsp:include page="./includes/recensioni.jsp" />
        <%
             break;

        }
        %>
        
        
        
    </body>
</html>
