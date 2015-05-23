<%-- 
    Document   : destinazione
    Created on : 23-mag-2015, 20.36.14
    Author     : Rocco
--%>

<%@page import="java.util.Map.Entry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Pagina di Arrivo.</h2>
        
        Son stati passati i seguenti parametri:
        
        <%
        
            // Iteriamo su tutti i parametri passati.
            for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                String name = entry.getKey();
                String[] value = entry.getValue();
                out.println(name+" --> "+value[0]);
            }
        
            // Solo il Dispatcher riesce a reindirizzare una richiesta mantenendo i parametri GET/POST.
            
        %>
        
    </body>
</html>
