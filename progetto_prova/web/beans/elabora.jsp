

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
        <%--
        
        Prova uso Bean con attributo class='' o type=''
                       
        --%>
        <%
            
        //beans.Utente billy = new beans.Utente("Rocco", "Muso");
        //session.setAttribute("istanza", billy);
        
        Map<String, String> x = new HashMap();
        x.put("roba", "buona");
        session.setAttribute("istanza", x);
        
        %>
        
        <jsp:useBean id="istanza" class="java.util.HashMap<String, String>" type="java.util.Map" scope="session" />
        
        <jsp:useBean id="istanza2" type="beans.Utente" scope="session" />
        
            <jsp:setProperty name="istanza2" property="*" /> <!-- setta tutte le proprietà passate dal form HTML -->
        
       <%
       
       // qui avremo l'oggetto settato dal bean, 'istanza'.
           
        out.print(istanza.getClass().getName());
        
           out.print("<br/>"+istanza2.getNome()+"<br/>");
           out.print(istanza2.getCognome());
       %>
    </body>
</html>
