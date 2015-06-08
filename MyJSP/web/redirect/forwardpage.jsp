
<%@page import="java.io.IOException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%! // <-- se non definito come dichiarazione, da errore!

public void doGet(HttpServletRequest request, HttpServletResponse response, JspWriter out) throws IOException, ServletException{

    if (request.getParameter("modo") != null){
    
        String modalita = request.getParameter("modo");
        
        if (modalita.equals("redirect")){
            response.sendRedirect("destinazione.jsp"); // Non viene passata la richiesta ad altro script. Semplice redirect. Può riferirsi anche a risorse remote, esterne alla context root. (quindi esterne alla web-app corrente)
        }
        else if (modalita.equals("dispatcher")){ // Il dispatcher permette redirect solo all'interno della stessa context root, quindi verso risorse presenti nella stessa web-app.
            //request.setAttribute("chiave", oggetto); // Per condividere dati e valori, si usa il setAttribute() e getAttribute().
            RequestDispatcher dispatcher = request.getRequestDispatcher("destinazione.jsp"); // da getServletContext() il percorso dev'essere riferito dalla context root: getServletContext().getRequestDispatcher("/destinazione.jsp");
            dispatcher.forward(request, response);
            // chi riceve userà request.getAttribute("chiave"); per recuperare l'oggetto passato. Appunto si possono passare anche oggetti!
        }else if(modalita.equals("azione_predefinita")){
            out.println("<font color='red'>Spiacente, l'azione predefinita non può essere invocata in modo procedurale da una Dichiarazione!</font>");
            %>
            <%-- <!-- Il forward accetta anche dei parametri param per inviare dati insieme al redirect. Non può essere inserito nel costrutto if di una dichiarazione, verrà eseguito a priori. -->
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

