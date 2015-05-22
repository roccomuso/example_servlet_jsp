<html>
  <body>
    <%= new Date() %>
	Scateniamo di proposito un'eccezione per andare alla pagina d'errore.
	Questo testo non viene mostrato percèh in caso di errore JSP rimanda direttamente alla errorPage
    <%new java.io.BufferedReader(new java.io.FileReader("nonesiste"));%>

    <%-- <%@page import="java.util.*"%> --%> <!-- Really commented -->
    <!-- <%@page import="java.util.*"%> --> <!-- Not really commented!!!!-->
    <%-- see var/lib/tomcat7/work/Catalina/localhost/20150325/org/apache/jsp/ --%>
    <%@page errorPage="error.jsp"%>
  </body>
</html>