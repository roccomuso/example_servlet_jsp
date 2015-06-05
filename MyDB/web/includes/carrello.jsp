


        <%@page import="strutture_dati.Carrello"%>
<%@page import="utility.dbCall"%>
<%@include file="../check_session.jsp" %> <!-- incluso da tutte le pagine che devono essere protette. -->
        
        
        <h2>Carrello:</h2>
        
        
        <%
        
        Carrello cart = (Carrello) session.getAttribute("carrello");
        if ((cart != null) && (!cart.prodotti.isEmpty())){
            
            // stampiamo il contenuto del carrello.
            out.print(dbCall.processRequest("stampa_carrello", session));
            %>
            <form action='<%= getServletContext().getContextPath() %>/inoltraOrdine' method="POST">
                <input type="submit" value="Procedi all'acquisto." />
            </form>
        <%
        }else{
            // Carrello vuoto
            out.print("<font color='red' size='10'>Il carrello è vuoto.</font><br/>");
        }
        
        %>