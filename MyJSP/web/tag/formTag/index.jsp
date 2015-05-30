<%-- 
    
    Form contenente diversi campi di testo.
    Se il form non è stato compilato correttamente viene riproposto all'utente e i campi del form già compilati vengono rivisualizzati in modo che l'utente debba immettere solo quelli mancanti.

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form</title>
    </head>
    <body>
        
        <%
           if (request.getParameter("emailAddress") != null && request.getParameter("firstName") != null && request.getParameter("lastName") != null) 
            if (!request.getParameter("emailAddress").equals("") && !request.getParameter("firstName").equals("") && !request.getParameter("lastName").equals("")) {
        // Se son stati specificati Nome e Email e Cognome (tutti dunque):
        %>
        <jsp:forward page="attributi.jsp">
            <jsp:param name="esito" value='OK'/>
        </jsp:forward>
        <%} else {
        // Se è stata specificata solo la Email:
        %>
        <jsp:forward page="attributi.jsp?esito=QUALCOSAMANCA"/>
        <%}%>

        <%@ taglib uri="/WEB-INF/tlds/mialib.tld" prefix="form_util" %>
        
        Prova a lasciare qualche campo vuoto, i campi compilati una volta tornati sulla pagina, rimarranno.
        <br/>
        <form method="POST" action="index.jsp">
            <table>
                <tr>
                    <td> Nome: </td>
                    <td> <input type="text" size='15' name="firstName"
                                value="<form_util:requestParameter property='firstName'/>">
                    </td>
                </tr>
                <tr>
                    <td> Cognome: </td>
                    <td> <input type="text" size='15' name="lastName"
                                value="<form_util:requestParameter property='lastName'/>">
                    </td>
                </tr>
                <tr>
                    <td> Email: </td>
                    <td> <input type="text" size='25' name="emailAddress"
                                value="<form_util:requestParameter property='emailAddress'/>">
                    </td>
                </tr>
            </table>
                    <input type="submit" value="Submit"/>
        </form>
                
    </body>
</html>
