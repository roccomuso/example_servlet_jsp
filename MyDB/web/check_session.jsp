<%-- 
    
--%>


        <%
        
          
            if (session.getAttribute("username") == null){ // Se non esiste la sessione rimanda alla pagina di login.
                              
                response.sendRedirect(request.getContextPath()+"/index.jsp"); // Siamo sicuri che effettui il redirect alla pagina giusta da qualsiasi sotto-directory sia incluso questo file.
                // Col dispatcher non si riesce a fare un redirect corretto e assoluto... ?
            
            }
          
            
        %>
 