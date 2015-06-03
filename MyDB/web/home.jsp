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
        
        <h2>Benvenuto nella sezione privata: Ordini - Profilo Utente - [<a href='logout.jsp'>Logout</a>]</h2>
        
        <p align="center">Da questa sezione puoi sfogliare il catalogo, effettuare acquisti e lasciare recensioni.</p>
    
        <jsp:include page="./includes/catalogo.jsp" />
        <hr/>
        <jsp:include page="./includes/recensioni.jsp" />
        
        
    </body>
</html>
