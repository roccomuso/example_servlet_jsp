

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
    </head>
    <body>
        <%
        
            // Con le sessioni sarebbe bastato: session.invalidate();
            // per eliminare i cookie invece:
            
            for (Cookie e: request.getCookies()){
                Cookie c = new Cookie(e.getName(), null);
                c.setMaxAge(0);
                response.addCookie(c);
            }
            
            response.sendRedirect("index.jsp");
        
        %>
    </body>
</html>
