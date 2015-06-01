<html>
    <head><title>Una pagina protetta </title></head>
    <body>
        <%@include file="show-security.jsp" %>
        <%
    if (request.isUserInRole("tomcat")) { %>
        Appartieni al ruolo <i>tomcat</i> <br>
        <%
} else { %>
        Non appartieni al ruolo <i>tomcat</i><br>
        <%
    } %>
        <% if (request.isUserInRole("cartoni")) { %>
        Appartieni al ruolo <i>cartoni</i> <br>
        <%
} else { %>
        Non appartieni al ruolo <i>cartoni</i><br>
        <%
    }
        if (request.isUserInRole("user")){
            %>

        Appartieni al ruolo <i>user</i>
        
        <%}else{%>
        Non appartieni al ruolo <i>user</i><br/>
        <%}%>
        
 <br/>
 <p>Effettua il [<a href="logout.jsp">logout</a>].</p>
    </body>

</html>

