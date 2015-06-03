<%-- 
    Index.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/lib.tld" prefix="j" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        
        <!-- Questo Tag controlla che esista la sessione, se non esiste stampa un form html di login, se esiste rimanda ad home.jsp -->
        <j:controllaSessione sessione="<%= session %>" richiesta="<%= request %>" risposta="<%= response %>" /> <!-- Stampa il form se non esiste una sessione valida -->
        
        
    </body>
</html>
