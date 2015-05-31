<html>
    <head><title>Un iteratore</title></head>
<%@ taglib uri="/WEB-INF/tlds/bodyTag.tld" prefix="it" %>
<body>
<% java.util.Vector vector = new java.util.Vector();
vector.addElement("uno"); vector.addElement("due");
vector.addElement("tre"); vector.addElement("quattro");
String item = "";
%>
Iterando su <%= vector %> ...
<p>
<it:iterate collection="<%= vector %>">
Item: <%= item %><br>body_tag<br> <!-- item settato come attributo di pagina: pageContext().setAttribute("item", "valore..");-->
</it:iterate>
</p>
</body>
</html>
