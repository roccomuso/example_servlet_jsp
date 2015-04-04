<% if (request.getMethod().equals("GET")) { %>
Hello <%=request.getParameter("firstname")%>, you got here with <%= request.getMethod()%>
<%}else {
       out.println("errore! sei arrivato qui con POST invece che con GET");}
%>