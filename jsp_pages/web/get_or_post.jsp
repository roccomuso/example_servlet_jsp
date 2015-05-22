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
			
			<!-- Commento: <\%-- --%> -->
			<!-- Scriptlet: <\% %> -->
			<!-- Espressioni: <\%= %> -->
			<!-- Dichiarazioni: <\%! %> -->
			<!-- Azioni predefinite: jsp:include, jsp:forward, jsp:setProperty, jsp:getProperty, jsp:useBean  -->
			<!-- Direttive: sono solo 3, page, include, taglib: <\%@ %> -->
			
        </p>
    </body>
</html>