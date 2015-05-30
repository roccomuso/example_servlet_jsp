<html><head><title>Un iteratore</title></head>
<%@ taglib uri="/WEB-INF/tld.tld" prefix="it" %>
<body>
<% java.util.Vector vector = new java.util.Vector();
vector.addElement("el_uno"); vector.addElement("el_due");
vector.addElement("el_tre"); vector.addElement("el_quattro");
%>
Iterating over <%= vector %> ...
<p>
<it:iterateSimple collection="<%= vector %>">
Ciao e andiamo a casa: REPL_ITEM<br>
</it:iterateSimple>
</p>
</body>
</html>
