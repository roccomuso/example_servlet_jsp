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
            if (request.getParameter("emailAddress") != "" && request.getParameter("firstName") != "" && request.getParameter("emailAddress") != null && request.getParameter("firstName") != null) {
        %>
        <jsp:forward page="attributi.jsp">
            <jsp:param name="name" value='<%=request.getParameter("emailAddress")%>'/>
        </jsp:forward>
        <%} else if (request.getParameter("emailAddress") != "" && request.getParameter("emailAddress") != null) {%>
        <jsp:forward page="attributi.jsp?name=NONSPECIFICATO"/>
        <%}%>

        <%@ taglib uri="/WEB-INF/tlds/mialib.tld" prefix="form_util" %>

        <form method="POST" action="index.jsp">
            <table>
                <tr>
                    <td> Nome: </td>
                    <td> <input type="text" size='15' name="firstName”
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
