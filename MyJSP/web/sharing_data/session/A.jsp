

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Condividere dati con le sessioni</title>
    </head>
    <body>
        <%
        
            varie.Animale obj = new varie.Animale("gatto", 4, true);
            
            Map<String, String> map = new HashMap();
            map.put("Rocco", "123456");
            
            // passiamo l'oggetto Animale e la Map tramite la sessione.
            session.setAttribute("oggetto", obj);
            session.setAttribute("mappa", map);
        %>
        
        Vai alla <a href="B.jsp">pagina B</a>.
    </body>
</html>
