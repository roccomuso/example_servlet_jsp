<%-- 
    Document   : index
    Created on : 3-giu-2015, 1.03.15
    Author     : Rocco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prova Bean</title>
    </head>
    <body>
        <h1>Compila il form</h1>
        
        <form action='elabora.jsp' method="POST">
            
            <input type="text" name="nome" placeholder="Nome..." /><br/>
            <input type="text" name="cognome" placeholder="Cognome..." /><br/>
            <input type="submit" value="Invia" />
            
        </form>
        
    </body>
</html>
