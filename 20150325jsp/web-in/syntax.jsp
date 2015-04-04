<html>
  <body>
    <%@page errorPage="error.jsp"%> <!--non cattura l'eccezione perche' non e' di esecuzione della pagina, ma di compilazione-->
    A scriptlet is <%--from--%> <\% to %> <!--<br/>-->
    <%-- commento2 --%> 
    \<br/>
    '<br/>"<br/>
    Hello <%=request.getParameter("param")%>
    <%out.write("also put this");%>
    <%response.setContentType( "text/plain" );%>
  </body>
</html>
