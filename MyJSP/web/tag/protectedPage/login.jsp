<html>
    <head><title> Login Page </title></head>
    <%@taglib uri="/WEB-INF/tlds/mialib.tld" prefix="security" %>
    <body>
    <font size=4 color="red"><security:showErrors /> </font>
    <p><font size=5 color="blue">Please login </font> <hr>
    <form action="<%=response.encodeURL("/MyJSP/authenticate")%>" method="POST">
        <%
            if (session.getAttribute("login-page") == null) {
                session.setAttribute("login-page", "/login.jsp");
            }
            if (session.getAttribute("error-page") == null) {
                session.setAttribute("error-page", "/error.jsp");
            }
            if (session.getAttribute("protected-page") == null) //for sendRedirect
            // session.setAttribute("protected-page", "/20150511/protectedPage.jsp");
            //for forward
            {
                session.setAttribute("protected-page", "/protectedPage.jsp");
            }
        %>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="userName" /> </td>
            </tr> <tr>
                <td>Password: </td>
                <td><input type="password" name="password" size="8" /> </td>
            </tr> </table>
        <input type="submit" value="login">
    </form> </p>
Ricorda che un nome valido &egrave;: Picasso e password: Pablo
</body></html>
