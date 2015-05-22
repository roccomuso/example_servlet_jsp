<%@page import="java.util.*"%>
<%
// parametri associati all'applicazione
Enumeration e=application.getAttributeNames();
%>
    <table border=1>
      <tr><th>Attribute</th><th>Value</th></tr>
<%
while(e.hasMoreElements()) {
    String s = (String)e.nextElement(); 
    out.write("<tr><td>" + s + "</td><td>" + application.getAttribute(s) + "</td></tr>");
}
%>
    </table>
<%
      e=application.getInitParameterNames();
%>
    <table border=1>
      <tr><th>Attribute</th><th>Value</th></tr>
<%
while(e.hasMoreElements()) {
    String s = (String)e.nextElement(); 
    out.write("<tr><td>" + s + "</td><td>" + application.getInitParameter(s) + "</td></tr>");
}
%>
    </table>
	