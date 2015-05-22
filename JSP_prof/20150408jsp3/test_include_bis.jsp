<html>
<p>Questo c'&egrave; sempre:</p>
<%@include file="page.jsp"%>
<p>Questo solo se si mette qualcosa nella request:</p>
<%if (request.getParameter("qualcosa") != null) {%>
<jsp:include page="page.jsp"/>
<%}%>
</html>
