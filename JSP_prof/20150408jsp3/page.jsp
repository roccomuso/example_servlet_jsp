<%@page info="info di page"%>
<%--<%@page info="info bis di page"%>--%>
<p>Page info: <%=((org.apache.jasper.runtime.HttpJspBase)page).getServletInfo()%></p>
<p>Page info bis: <%=this.getServletInfo()%></p>

