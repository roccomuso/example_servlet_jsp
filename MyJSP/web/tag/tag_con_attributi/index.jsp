<%-- 
    Esempio di pagina JSP con TAG personalizzati con ATTRIBUTI.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP con TAG personalizzati e attributi</title>
    </head>
    <body>
        
        
    <%@taglib uri="/WEB-INF/tlds/mialib.tld" prefix="j" %> <!-- <-- il prefisso è arbitrario, potremmo mettere anche altro nome. -->
     
    <%
    
    int identificativo = 2;
    
    %>
    
    
        <j:profilo_utente id="1"/> <!-- NOTA BENE. i nomi degli attributi devono essere uguali ai nomi degli attributi dentro il tag Handler e anche del setter! setid() <-- con id minuscolo come scritto nell'attributo -->
       
        <br/>
        
        <j:profilo_utente id="<%= identificativo %>"/> <!-- Funziona perchè nellA TLD l'attributo id ha <rtexprvalue>true</rtexprvalue> -->
    
    
    </body>
</html>
