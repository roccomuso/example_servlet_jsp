<%-- 
    Prova di redirect con l'azione predefinita <jsp:forward page="" /> .

in generale:

QUALUNQUE Azione predefinita, quindi sia <jsp:forward che <jsp:include etc...
se richiamata in un if statement all'interno di una dichiarazione, verrà SEMPRE chiamata.
Mentre all'interno di un if definito in uno scriptlet, seguirà il comportamento voluto!

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redirect con dichiarazione JSP</title>
    </head>
    <body>
        
        <%! // <-- Dichiarazione
        
            public void redirect(){  // NO! in una dichiarazione verrà sempre eseguito!
                %>
                <jsp:forward page="destinazione.jsp" />
                <%!
            }
        %>
        
        <% // <-- semplice Scriptlet
         
            if (request.getParameter("page") != null){ // SI! qui viene eseguito solo se effettivamente passiamo il parametro page.
             %>   
             <jsp:forward page="destinazione.jsp" />
             <%
            }
         %>   
        
        
        <%
        if (request.getParameter("page") != null)
            redirect();
        %>
        
        Nessun redirect. Manca il parametro 'page'.
    </body>
</html>
