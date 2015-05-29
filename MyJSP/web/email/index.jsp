<%-- 

Pagina per l'invio di una eMail.

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina per l'invio di una mail.</title>
    </head>
    <body>
        <h1>Compila il form sottostante per inviare una email:</h1>
        
        <form action="post.jsp" method="POST">
            
            <input type="text" name="nome" />
            ...
            
            <input type="submit" value="Invia" />
                 
        </form>
        
    </body>
</html>
