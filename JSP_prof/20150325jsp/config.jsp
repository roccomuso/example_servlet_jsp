<%@page import="java.util.*"%>
<%
// Prendiamo i parametri di inizializzazione
Enumeration e=config.getInitParameterNames();
%>
    <table border=1>
      <tr><th>Attribute</th><th>Value</th></tr>
<%
while(e.hasMoreElements()) {
    String s = (String)e.nextElement(); 
    out.write("<tr><td>" + s + "</td><td>" + config.getInitParameter(s) + "</td></tr>");
}
%>
    </table>
	