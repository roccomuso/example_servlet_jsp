<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Condividere dati fra Risorse</title>
    </head>
    <body>
        <%! // <-- classi o metodi vanno in una Dichiarazione.
        
        
        
        %>
        
        <%
            
        // Passiamo un oggetto come Attributo, da A.jsp verso B.jsp.
        // Col dispatcher inoltre vengono passati anche tutti i parametri GET/POST inviati a questa pagina.
        varie.Animale obj1 = new varie.Animale("Cane", 4, true); // classe definita nel package di default. Se l'avessimo dichiarata qua, non sarebbe stata visibili da B.jsp
        
        request.setAttribute("oggetto1", obj1);
        
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/sharing_data/dispatcher/B.jsp"); // percorso partendo dalla Context Root.
        dis.forward(request, response);
        
        %>
        
       
    
    </body>
</html>
