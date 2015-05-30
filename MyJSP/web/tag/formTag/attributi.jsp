
<html>
    <head>
        <title>Pagina di output</title>
    </head>
    <body>
        
        
        <h1>
            <%-- scriptlet to obtain "name" request parameter --%>
            <%
                String name = request.getParameter("name");
                out.print(name);
            %>
            
        </h1>
    </body>
</html>
