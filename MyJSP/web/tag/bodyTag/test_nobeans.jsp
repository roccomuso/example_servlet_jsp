<html><head><title>Un iteratore</title></head>
<%@ taglib uri="/WEB-INF/tld.tld" prefix="it" %>
<body>
<% java.util.Vector vector = new java.util.Vector();
vector.addElement("el_uno"); vector.addElement("el_due");
vector.addElement("el_tre"); vector.addElement("el_quattro");
String item = "";
%>
Iterating over <%= vector %> ...
<p>
<it:iterate collection="<%= vector %>">
Item: <%= item %><br>Ciao4<br>
</it:iterate>
</p>
</body>
</html>
