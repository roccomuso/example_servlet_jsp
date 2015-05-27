<%-- 
    Document   : index
    Created on : 27-mag-2015, 1.11.07
    Author     : Rocco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <h1>Esempio di inclusioni:</h1>
        
        HomePage < A < B
        <br/><br/>
        
        <%
        if (request.getParameter("pag") != null){
            String pag = request.getParameter("pag")+".jsp"; // percorso dinamico
        %>
        <jsp:include page="<%= pag %>" />
        <%    
        }else{
        %>
        <jsp:include page="A.jsp" />
        
        <%
        }
        %>
    </body>
</html>
