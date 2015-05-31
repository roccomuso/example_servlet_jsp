<%-- 
    Pagina d'errore.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Errore Login</title>
    </head>
    <body>
        <font size="6" color="red">
            I dati inseriti non sono validi!
        </font><br/>
        
        Clicca <a href='<%=response.encodeURL("login.jsp")%>'>qui</a> per riprovare!
    </body>
</html>
