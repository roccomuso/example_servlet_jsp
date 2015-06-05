



<%@include file="../check_session.jsp" %> <!-- incluso da tutte le pagine che devono essere protette. -->



        <%
        
        out.print(utility.dbCall.processRequest("lista_ordini", session));
        
        
        %>