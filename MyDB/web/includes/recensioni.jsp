
<%@page import="utility.dbCall"%>
<jsp:include page="../check_session.jsp" />

<h1>Recensioni</h1>
<%

// Recensioni da lasciare:
    out.print("<h2>- Da lasciare:</h2>");
    
    out.print(dbCall.processRequest("recensioni_da_lasciare", session));

// Recensioni già lasciate:
   out.print("<h2>- Lasciate:</h2>");
   
   out.print(dbCall.processRequest("recensioni_lasciate", session));
   
%>

