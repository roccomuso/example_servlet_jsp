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
        <% if (request.isUserInRole("for_basic")) { %>
        Appartieni al ruolo <i>for_basic</i> <br>
        <%
} else { %>
        Non appartieni al ruolo <i>for_basic</i><br>
        <%
    }
        if (request.isUserInRole("file")){
            %>

        Appartieni al ruolo <i>prova</i>
        
        <%}%>
    </body></html>
