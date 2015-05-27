<%-- 
    Login usando le sessioni.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Login</title>
    </head>
    <body>
        
        <%
               
            if (session.getAttribute("loggato") == null){ 
        %>
        
        <fieldset style="width:300px;height:100px">
            <legend>Effettua il Login</legend>
                
        <form action="login.jsp" method="POST" name="form1">   
            <input type="text" name="username" placeholder="Inserisci username" required /><br/>
            <input type="password" name="password" placeholder="Inserisci password" required /><br/>
            <input type="submit" value="Login" />
        </form>
        
        </fieldset>
        
        <%
            }else{
        %>
        
        <h3>Sei dentro!</h3>
        Benvenuto <%= session.getAttribute("name") %>, effettua il <a href="logout.jsp">logout</a> se vuoi.
        
        <%

            }
        %>
    </body>
</html>
