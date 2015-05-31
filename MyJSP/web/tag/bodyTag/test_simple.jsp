<html><head><title>Un iteratore</title></head>
<%@ taglib uri="/WEB-INF/tlds/bodyTag.tld" prefix="it" %>
<body>
<% java.util.Vector vector = new java.util.Vector();
vector.addElement("uno"); vector.addElement("due");
vector.addElement("tre"); vector.addElement("quattro");
%>
Iterando su <%= vector %> ...
<p>
<it:iterateSimple collection="<%= vector %>">
Ciao e andiamo a casa: REPL_ITEM<br>
</it:iterateSimple>
</p>
</body>
</html>
