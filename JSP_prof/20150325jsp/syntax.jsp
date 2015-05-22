<html>
  <body>
    <%@page errorPage="error.jsp"%> <!--non cattura l'eccezione perche' non e' di esecuzione della pagina, ma di compilazione-->
    A scriptlet is <%--from--%> <\% to %> <!--<br/>-->
    <%-- commento2 --%> 
    \<br/>
    '<br/>"<br/>
    Hello <%=request.getParameter("param")+request.getParameter("param")%>
    <%out.println("also put this%\>");%>
    <%response.setContentType( "text/plain" );%>
	Questa pagina non viene interpretata come HTML perchè il setContentType e' impostato a text/plain invece di text/html
  </body>
</html>
