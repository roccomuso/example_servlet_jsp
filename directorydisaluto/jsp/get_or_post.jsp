<html>
    
    <head>
        <title>Prova pagina JSP che accetta un parametro GET</title>
            
    </head>
    <body>
        <p>
            <%
                String nome = request.getParameter("nome"); // Sia che GET che POST.

                if (nome != null){
            %>
            Benvenuto, <%=nome%>.
            <%
            }else{
            %>
            Prego inviare un parametro GET o POST "nome" alla pagina.
            <%
                }
            %>
        </p>
    </body>
</html>