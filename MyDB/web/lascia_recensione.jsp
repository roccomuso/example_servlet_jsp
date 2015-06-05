<%-- 
    File per registrare le recensioni nel DB, usando un bean per processare i dati e inserirli nel DB.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <jsp:include page="./check_session.jsp" />  <!-- incluso da tutte le pagine che devono essere protette. -->
        
        <jsp:useBean id="istanza" class='utility.beanLasciaRecensione' scope="session" />
            <jsp:setProperty name="istanza" property="*" />
        
            <%
            out.print(istanza.salva_in_db()); // chiamiamo questo metodo definito nel Bean.
            %>

                    
        <br/> Torna alla <a href='home.jsp'>Home</a>.
        
        
        
    </body>
</html>
