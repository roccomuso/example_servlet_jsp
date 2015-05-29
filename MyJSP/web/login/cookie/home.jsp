<%--
    Login e carrello usando i Cookie.
--%>

<%@page import="varie.CookieTools"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <%
        
        // Facciamo una mappa, perchè è più pratica dell'array cookies.
        Map<String, String> COOKIES = CookieTools.convert_Cookie_to_Map(request.getCookies());
            
        
        if (COOKIES.containsKey("loggato")){ // Login ok, mostriamo carrello e catalogo
            String nome= COOKIES.get("username");
            
        %>
    
        <h3>Benvenuto, <%= nome %> - <a href="logout.jsp">logout</a></h3>
            
            Qui di seguito il tuo carrello:
            <br/>
            
                <jsp:include page="carrello.jsp" /> <!-- questa dichiarazione al contrario della dichiarazione forward può essere gestita nell'if statement.-->
            
            <hr/>
            
            <br/>
            Qui il catalogo prodotti:
            <br/>
            
                <jsp:include page="catalogo.jsp" />
            
                
        <%
        }else{ // Nessun login.
        
            %>
            Prego, riandare alla <a href="index.jsp">Index</a> ed effettuare il login!
            
            <%
        }            
                   
        
        %>
    </body>
</html>
