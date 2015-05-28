
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
        
        Map<String, String> db = new HashMap();
       
       public void jspInit(){ // chiamato inizialmente durante il ciclo di vita della servlet, equivalente ad init().
           // Stabiliamo eventuale connessione al DB . . .
           db.put("Rocco", "123456");
           db.put("Megatron", "password");
           db.put("TonyStark", "pepper");
           
       }
              
       %>
       
       <%
       
       if (request.getParameter("username") != null && request.getParameter("password") != null){
       
           // controlliamo che corrispondando sul DB
           String user = request.getParameter("username");
           String pass = request.getParameter("password");
           
           if(db.containsKey(user))
             if (pass.equals(db.get(user))){
                 // Login corretto
                 session.setAttribute("loggato", true);
                 session.setAttribute("name", user);
                 response.sendRedirect(response.encodeURL("index.jsp"));
             }else{
                 // Login errato
                 out.print("Spiacente, Login errato! <a href='index.jsp'>Riprova</a>");
             }
           else
               out.print("Username inesistente! <a href='index.jsp'>Riprova</a>");
       }else{
       
            out.print("Prego inserire entrambi i valori username e password.");
       }
           
       %>
       
    </body>
</html>
