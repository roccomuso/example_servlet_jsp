<font size="4" color="blue">
Informazioni sulla sicurezza
</font> <br>
User principal: <%= request.getUserPrincipal() %> <%--<%= request.getUserPrincipal().getName() %>--%>. <br>
Request authenticated with: <%= request.getAuthType() %>. <br>
<% if (request.isSecure()) { %>
This connection is secure. <br>
<% } else { %>
This connection is NOT secure. <br>
<% } %>
Server Address: <%= request.getServerName() %> <br>
Remote Host: <%= request.getRemoteHost() %> <br>
Remote Addr; <%= request.getRemoteAddr() %>
