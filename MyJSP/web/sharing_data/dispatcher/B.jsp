

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>pagina B</title>
    </head>
    <body>
        <%
        
           varie.Animale x = (varie.Animale) request.getAttribute("oggetto1"); // casting necessario.
         
           out.println("<h3>Dati passati da A.jsp</h3>");
           out.println("A.jsp ha passato un oggetto di tipo "+varie.Animale.class);
           out.println("<br/><br/>Sono un: "+ x.getTipo()); 
           out.println("<br/>Ed ho "+x.getZampe()+" zampe.");
        
        
        %>
    </body>
</html>
