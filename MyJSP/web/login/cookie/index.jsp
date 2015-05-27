<%--
    Login e carrello usando i Cookie.
--%>

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
        
            // siccome il controllo è fatto spesso, è preferibile metterlo in una classe esterna: CookieTools.isSignedIn(cookies);
        Cookie[] cookies = request.getCookies();
        
        boolean loggato = false;
        
        for (Cookie e: cookies)
            if (e.getName().equals("loggato"))
                loggato = true;
        
        if (loggato)
            response.sendRedirect("home.jsp");
        
        // Altrimenti interpreta normalmente il form per il login...
        %>
        <fieldset style="width:300px;height:100px">
            <legend>Effettua il Login</legend>

        <form action="login.jsp" method="POST" name="form1">   
            <input type="text" name="username" placeholder="Inserisci username" required /><br/>
            <input type="password" name="password" placeholder="Inserisci password" required /><br/>
            <input type="submit" value="Login" />
        </form>

        </fieldset>
        
    </body>
</html>
