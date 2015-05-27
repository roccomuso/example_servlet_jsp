

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <%!
        
        public Map<String, String> db = new HashMap();
        
        public void jspInit(){
        
            db.put("Rocco", "123456");
            db.put("tonystark", "jarvis");
            db.put("pippo", "password");
            
        }
        
        %>
        
        <%
        
            
            if (request.getParameter("username") != null && request.getParameter("password") != null){
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                
                if (db.containsKey(user))
                    if (pass.equals(db.get(user))){
                        // login effettuato
                        Cookie a = new Cookie("username", user);
                        Cookie b = new Cookie("loggato", "si");
                        
                        response.addCookie(a);
                        response.addCookie(b);
                        
                        response.sendRedirect("home.jsp");
                    }else{
                        // credenziali errate
                        out.print("Spiacente, credenziali errate! <a href='index.jsp'>Riprova</a>.");
                    }
                else
                    out.print("Spiacente, username non esistente. <a href='index.jsp'>Riprova</a>.");
            }
        
        %>
    </body>
</html>
