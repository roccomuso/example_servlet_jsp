
<%@page import="java.io.IOException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%! // <-- se non definito come dichiarazione, da errore!

public void doGet(HttpServletRequest request, HttpServletResponse response, JspWriter out) throws IOException, ServletException{

    if (request.getParameter("modo") != null){
    
        String modalita = request.getParameter("modo");
        
        if (modalita.equals("redirect")){
            response.sendRedirect("destinazione.jsp"); // Non viene passata la richiesta ad altro script. Semplice redirect.
        }
        else if (modalita.equals("dispatcher")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("destinazione.jsp");
            dispatcher.forward(request, response);
        }else if(modalita.equals("azione_predefinita")){
            out.println("<font color='red'>Spiacente, l'azione predefinita non può essere invocata in modo procedurale.</font>");
            %>
            <%-- <!-- Il forward accetta anche dei parametri param per inviare dati insieme al redirect. -->
            <jsp:forward page="destinazione.jsp" > 
                <jsp:param name="parametro" value="valore" />
            </jsp:forward>
            --%>
            <%!
        }
    
    }
    
}

%>




<%
doGet(request, response, out); // oggetti impliciti che passiamo, perchè non visibili all'interno delle dichiarazioni <\%! %\>
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redirect non valido</title>
    </head>
    <body>
        <h1>'Modo' non valido, redirect non effettuato.</h1>
        
        <p>La pagina di redirect è sempre 'destinazione.jsp', cambia il modo in cui viene effettuato il redirect.<br/>
        Modalità disponibili:
            <ul>
                <li>response.sendRedirect("destinazione.jsp");</li>
                <li>request.getRequestDispatcher("destinazione.jsp");</li>
                <li><\jsp:forward page="destinazione.jsp" /></li>
            </ul>
        <br/>
        Passare un modo attraverso il parametro GET: modo=redirect, modo=dispatcher oppure modo=azione_predefinita.
        </p>
    </body>
</html>

