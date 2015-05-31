<html>
    <head><title>Un iteratore</title></head>
<%@ taglib uri="/WEB-INF/tlds/bodyTag.tld" prefix="it" %>
<body>
<% java.util.Vector vector = new java.util.Vector();
vector.addElement("uno"); vector.addElement("due");
vector.addElement("tre"); vector.addElement("quattro");
%>
Iterando su <%= vector %> ...
<p>
<it:iterateSimpleBis collection="<%= vector %>">
<jsp:useBean id="item" scope="page" class="java.lang.String"/>
Item: REPL_ITEM<br>
Item2: <%= item %><br>
</it:iterateSimpleBis>
</p>
</body>
</html>
