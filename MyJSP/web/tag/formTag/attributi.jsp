
<html>
    <head>
        <title>Pagina di output</title>
    </head>
    <body>
        
        
        <h1>
            <%-- scriptlet to obtain "name" request parameter --%>
            <%
                String esito = request.getParameter("esito");
                
                
                
                if (esito != null)
                if (esito.equals("QUALCOSAMANCA")){
                    out.print("Qualche campo mancante, torna indietro e correggi. <a href='index.jsp'>Ritorna</a>.");
                    
                    // Settiamo i parametro gi� impostati come attributi di sessione, cos� l'handler dei tag pu� rimettere il valore nel form
                    if (request.getParameter("emailAddress") != null) session.setAttribute("emailAddress", request.getParameter("emailAddress"));
                    if (request.getParameter("firstName") != null) session.setAttribute("firstName", request.getParameter("firstName"));
                    if (request.getParameter("lastName") != null) session.setAttribute("lastName", request.getParameter("lastName"));
                    
                }else if(esito.equals("OK")){
                    out.print("Tutti i dati inseriti: "+request.getParameter("firstName")+" - "+request.getParameter("lastName")+" - "+request.getParameter("emailAddress"));
                    
                }
                else out.print("parametro 'esito' non specificato!");
                
                  
            %>
            
        </h1>
    </body>
</html>
