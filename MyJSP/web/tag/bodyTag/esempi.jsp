<%-- 
    File d'esempi per capire il funzionamento dei metodi che interagiscono col body dei tag personalizzati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/bodyTag.tld" prefix="it" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Esempi Tag personalizzati e bodyTagSupport</title>
    </head>
    <body>
        <%
            java.util.Vector vector = new java.util.Vector();
            vector.addElement("uno");
            vector.addElement("due");
            vector.addElement("tre");
            vector.addElement("quattro");
            
            //String item = "";
        %>
        Iterando su <%= vector%> ...

        
    <ul>
        <it:itera collection="<%= vector %>">
            <jsp:useBean id="item" scope="page" class="java.lang.String" /> <!-- settato dal tag Handler: pageContext.setAttribute("item", iterator.next()); -->
            <li>Un elemento della lista è: <%= item %></li>
        </it:itera>
    </ul>
    
    </body>
</html>
