<%-- 
    Costrutto Switch Case per JDK < 1.7
--%>

<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!
        
        String input = "pluto";
        
        
        private enum Scelta{ // Usato per il costrutto Switch Case con JDK < 1.7
            paperone, paperino, pluto;
        }
        
        
        

        %>
        
        <%
        Scelta scelta = null;
        
        for (Scelta x : Scelta.values())
            if (x.name().equals(input))
                scelta = Scelta.valueOf(input);
           
        
        if (scelta != null)
        switch(scelta){
            case paperone:
                out.print("Ciao Paperone!");
            break;
            case paperino:
                out.print("Ciao Paperino!");
            break;
            case pluto:
                out.print("Ciao Pluto!");
            break;
            default:
                out.print("Non ti conosco...");
            break;
        }
                
        %>
    </body>
</html>
