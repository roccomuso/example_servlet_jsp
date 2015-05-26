
<%@page import="javaBean.JavaBean"%>
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
        <p>Output HTML viene dopo qualsiasi output del PrintWriter. Anche se scritto prima.</p>

<%! 
public void jspInit(){ // Funzione di inizializzazione JSP. Richiamata al caricamento della pagina.

}
%>
<!-- Un modo possibile per condividere dati, oltre all'uso del Dispatcher con setAttribute, si possono usare i bean, con il contesto di visibilità 'scope' opportuno. E dunque richiamare l'istanza bean con l'id scelto dove più ci aggrada (gli scriptlet hanno visibilità dei bean solitamente.).. -->
<jsp:useBean id="istanzaBean" class="javaBean.JavaBean" scope="session" />
<!-- ogni parametro viene passato al Bean setter corrispondente. -->
<jsp:setProperty name="istanzaBean" property="*" />

<%! // <-- Dichiariamo un metodo, quindi il tag dev'essere una DICHIARAZIONE!!!
    
protected void doPost(HttpServletRequest request, HttpServletResponse response, JavaBean istanzaBean) throws IOException{
    // siamo dentro una dichiarazione, l'istanzaBean ce la facciamo passare da un normale scriptlet che ha visibilità del javaBean.
    PrintWriter outt = response.getWriter();
    outt.println("doPost() chiamato!<br/><br/>");
   
    outt.println("<b>Ciao, ecco i dati da te inseriti e processati dal javaBean:</b><br/>");
    outt.println("Nome: "+istanzaBean.getNome()+"<br/>");
    outt.println("Cognome: "+istanzaBean.getCognome()+"<br/>");
    outt.println("Email: "+istanzaBean.getEmail()+"<br/>");
    
}

%>


<!--
<%--

Nome: <jsp:getProperty name="istanzaBean" property="nome" /><br/>
Cognome: <jsp:getProperty name="istanzaBean" property="cognome" /><br/>
Email: <jsp:getProperty name="istanzaBean" property="email" /><br/>
<%= istanzaBean.getEmail() %>
<% istanzaBean.setEmail("indirizzo@email.it"); %>

<jsp:setProperty name="istanzaBean" property="nome" value="<%= request.getParameter("parametro") %>" /> passare un valore numerico può essere problematico! perchè bisogna fare il casting.
Motivo per cui invece di assegnare direttamente value="..request.getPara...", si usa param='parametro', e la conversione è fatta automaticamente!

Addirittura mettendo in property il simbolo * asterisco, si effettua un mapping con conversione diretta, fra parametri passati e attributi del Bean.
<jsp:setProperty name="istanzaBean" property="*" />

--%>
-->

<%!

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
    PrintWriter out = response.getWriter(); // anche perchè all'interno delle direttive non son definiti gli oggetti impliciti
    out.println("doGet() chiamato!");

    out.println("<form name=\"form1\" action=\"bean.jsp\" method=\"POST\">");
    out.println("<input type=\"text\" name=\"nome\" placeholder=\"Inserisci nome\" /> <br/>");
    out.println("<input type=\"text\" name=\"cognome\" placeholder=\"Inserisci cognome\" /> <br/>");
    out.println("<input type=\"text\" name=\"email\" placeholder=\"Inserisci Email\" /> <br/>");
    out.println("<br/>");
    out.println("<input type=\"submit\" name=\"button\" value=\"Invia\" />");
    out.println("</form>");


    }
%>

<%

 // <-- Non dichiariamo un metodo, dunque è semplicemente uno scriptlet.

if (request.getMethod().equals("POST")){
    doPost(request, response, istanzaBean); // passiamo anche l'istanzaBean, perchè non è visibile da una DICHIRAZIONE JSP.. <%!
}else
    doGet(request, response);

%>


    </body>
</html>