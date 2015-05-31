<!-- Gli esempi di questa cartella mostrano come manipolare il contenuto dei Tag personalizzati in modo interattivo. -->
<html>
    <head><title>Un iteratore</title></head>
    <%@ taglib uri="/WEB-INF/tlds/bodyTag.tld" prefix="it" %>
    <body>
        <% java.util.Vector vector = new java.util.Vector();
        vector.addElement("uno"); vector.addElement("due");
        vector.addElement("tre"); vector.addElement("quattro");
        %>
        Iterating over <%= vector %> ...
        <p>
    <it:iterate collection="<%= vector %>"> <!-- Tag Handler che estende BodyTagSupport per gestire il body del tag -->
        <jsp:useBean id="item" scope="page" class="java.lang.String"/>
        Item: <%= item %><br>
    </it:iterate>
</p>
</body>
</html>
