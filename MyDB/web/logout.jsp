<%-- 
    Document   : logout
    Created on : 3-giu-2015, 1.50.17
    Author     : Rocco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        
        // eliminazione della sessione:
            
            session.invalidate();
        
        // eliminazione dei cookie:
        
            // Non c'è bisogno, il carrello viene memorizzato nella sessione, come Attributo.
            
            
            response.sendRedirect(request.getContextPath()); // redirect alla context root.
        %>
    </body>
</html>
