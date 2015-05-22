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
	
Questa pagina non viene interpretata come HTML perchè il setContentType e' impostato a text/plain invece di text/html.
Inoltre essendo un errore di compilazione e non d'esecuzione non rimanda alla errorPage.

		
  </body>
</html>
