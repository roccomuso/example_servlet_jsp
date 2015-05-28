<%-- 
    include con il RequestDispatcher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Include con il RequestDispatcher</title>
    </head>
    <body>
        <%
        
            RequestDispatcher dp = request.getRequestDispatcher("index.jsp"); // senza slash se il dispatcher è dal request, dal contesto serve lo slash / e tutto il percorso partendo dalla context root.
            //RequestDispatcher dp =  getServletContext().getRequestDispatcher("/include/index.jsp");
            dp.include(request, response);
            
        %>
    </body>
</html>
