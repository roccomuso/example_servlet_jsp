<%-- 

Si usa una variabile protectedPage per memorizzare la pagina richiesta
cui far ritorno dopo l'eventuale redirezione verso la login-page.

--%>
<html>
    <head><title> Una pagina protetta </title></head>
<%@taglib uri="/WEB-INF/tlds/mialib.tld" prefix="security" %>
<body>

    <security:enforceLogin loginPage="/login.jsp" errorPage="/error.jsp" />

    <jsp:useBean id="user" type="tagHandler.protectedPage.User" scope="session" />
    Questa è una pagina protetta. Benvenuto <%= user.getUserName() %>

</body>
</html>
