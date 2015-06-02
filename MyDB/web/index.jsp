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
        
        <j:controllaSessione sessione="<%= session %>" /> <!-- Stampa il form se non esiste una sessione valida -->
        
        
    </body>
</html>
