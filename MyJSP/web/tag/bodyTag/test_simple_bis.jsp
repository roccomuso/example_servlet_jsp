<html><head><title>Un iteratore</title></head>
<%@ taglib uri="/WEB-INF/tld.tld" prefix="it" %>
<body>
<% java.util.Vector vector = new java.util.Vector();
vector.addElement("el_uno"); vector.addElement("el_due");
vector.addElement("el_tre"); vector.addElement("el_quattro");
%>
Iterating over <%= vector %> ...
<p>
<it:iterateSimpleBis collection="<%= vector %>">
<jsp:useBean id="item" scope="page" class="java.lang.String"/>
Item: REPL_ITEM<br>
Item2: <%= item %><br>
</it:iterateSimpleBis>
</p>
</body>
</html>
