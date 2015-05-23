
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page for Java Bean Example</title>
    </head>
    <body>
        <h1>Hello World!</h1>


<%

JspWriter output = out;

%>

<%! // <-- Dichiariamo un metodo, quindi il tag dev'essere una DICHIARAZIONE!!!
    
public void doPost(HttpServletRequest request, HttpServletResponse response){
    
}  
    
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
    PrintWriter out = response.getWriter();
    out.println("<form name=\"form1\" action=\"bean.jsp\" method=\"POST\">");
    out.println("<input type=\"text\" name=\"nome\" value=\"Inserisci nome\" />");
    out.println("<input type=\"text\" name=\"cognome\" value=\"Inserisci cognome\" />");
    out.println("<input type=\"text\" name=\"email\" value=\"Inserisci Email\" />");
    out.println("</form>");
}

%>

<% // <-- Non dichiariamo un metodo, dunque è semplicemente uno scriptlet.
    
output = out;
if (request.getMethod().equals("POST")){
    doPost(request, response);
}else
    doGet(request, response);

%>


    </body>
</html>