<html>
<p>Questo c'&egrave; sempre:</p>
<%@include file="welcome3.html"%>
<p>Questo solo se si mette qualcosa nella request:</p>
<%if (request.getParameter("qualcosa") != null) {%>
<jsp:include page="welcome3.html"/>
<%}%>
</html>
