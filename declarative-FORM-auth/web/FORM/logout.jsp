<%-- 
    Pagina di logout.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout effettuato</title>
    </head>
    <body>
        <%
        
        session.invalidate();
        
        %>
        <h1>Logout effettuato!</h1>
        
        PS. L'autenticazione BASIC non è stata pensata per gestire il LOG-OUT! (infatti non funziona nel modo classico..). Con il metodo FORM invece il logout funziona!
        
        <br/><p>Torna alla <a href="../index.jsp">home</a>.</p>
    </body>
</html>
