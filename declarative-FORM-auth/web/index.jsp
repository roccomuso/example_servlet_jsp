<%-- 
    Indice delle risorse protette.
    Dopo il login tramite metodo FORM, si viene rimandati automaticamente alla context-root, e questa è la index contenuta e visualizzata dunque.

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Benvenuto</title>
    </head>
    <body>
        <h1>Ecco le risorse protette:</h1>
        Prova ad accedervi:
        
        <br/>
        <ul>
            <li><a href='./FORM/pagina-protetta.jsp'>pagina-protetta.jsp</a></li>
            <li><a href="./FORM/directory-protetta/">directory-protetta</a></li>
        </ul>
    </body>
</html>
