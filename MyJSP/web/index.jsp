<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>


<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        <%
        
        
        out.print("ciaoooo");
        // praticamente già è definita in JSP uno stream di output verso il client, chiamato out.
        // out è infatti un oggetto implicito. Vedere slide 270.
        // Gli oggetti impliciti non sono definiti all'interno delle dichiarazioni <\%! %\>
        
        /*
            Altri oggetti impliciti:
                - application
                - config
                - exception
                - out
                - page
                - pageContext
                - request
                - response
                - session
                
        */
        %>
        
        		<!-- Commento: <\%-- --%> -->
			<!-- Scriptlet: <\% %> -->
			<!-- Espressioni: <\%= %> -->
			<!-- Dichiarazioni: <\%! %> -->
			<!-- Azioni predefinite: jsp:include, jsp:forward, jsp:setProperty, jsp:getProperty, jsp:useBean  -->
			<!-- Direttive: sono solo 3, page, include, taglib: <\%@ %> -->
      
        <%-- 
        Due diversi tipi di include:
        
        <jsp:include page="get_or_post.jsp" />
        <%@include file="get_or_post.jsp" %>
        
        si comportano allo stesso modo. Come l'include nel PHP.
        --%>               
       
        
        
    </body>
</html>
