
<%@page import="utility.dbCall"%>

<%@include file="../check_session.jsp" %> <!-- incluso da tutte le pagine che devono essere protette. -->


<%

out.print(dbCall.processRequest("profilo_utente", session));

%>