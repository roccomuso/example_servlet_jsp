

<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <!-- CON SCOPE="SESSIONE" -->
        
        <%
        
        try{ // se non facessimo il controllo, e ci fosse una chiamata diretta a questa pagina senza passare da A.jsp ci sarebbe un'eccezione perchè non riesce a trovare negli attributi di sessione l'oggetto chiamato 'oggetto'.
        %>
        
        <!-- Otteniamo l'istanza 'oggetto', definita da A.jsp con session.setAttribute() -->
        <jsp:useBean id="oggetto" class="varie.Animale" scope="session" />
        Il gatto ha <%= oggetto.getZampe() %> zampe. (attributo di sessione)
        
        <%
        }catch(Exception e){
                
            out.print("Non è presente questo oggetto negli <b>attributi di sessione</b>.");
          
        }
        %>
        
        <br/><br/>
        <!-- CON SCOPE="APPLICATION" -->
        
        <%
        
        // NOTA BENE. Gli attributi di Applicazione son condivisi da tutta l'applicazione, da tutti gli utenti!
        // Se un utente Pippo, crea un attributo d'applicazione, un utente Paperino può vededere il valore che ha impostato per quell'attributo!
        // Son valori condivisi da tutta l'applicazione a prescindere dalla sessione! quindi da tutti gli utenti.
        
        // Si può ricevere tale attributo usando l'oggetto implicito application:
        
        // Map<String, String> oggetto_mappa = (HashMap) application.getAttribute("oggetto_mappa");
        
        // OPPURE il contesto (il contesto è uguale all'application, condiviso fra tutti gli utenti della web application):
        
        // Map<String, String> obj = (HashMap) getServletContext().getAttribute("oggetto_mappa");
        
        
        // OPPURE Usando i bean si preleva in questo modo:
        
        try{ // se non facessimo il controllo, e ci fosse una chiamata diretta a questa pagina senza passare da A.jsp ci sarebbe un'eccezione perchè non riesce a trovare negli attributi d'applicazione l'oggetto chiamato 'oggetto2'.
        %>
        
        <!-- Otteniamo l'istanza 'oggetto2', definita da A.jsp con getServletContext().setAttribute() -->
        <jsp:useBean id="oggetto_mappa" class="java.util.HashMap<String, String>" scope="application" /> <!-- è l'azione standard a provocare l'eccezione, IstantiationException -->
        
        
        <%
        // Notare bene, sopra, il tipo di classe del javaBean, DEVE specificare anche la tipologia di oggetti contenuti nella mappa, <String, String>.
        
        for (Entry a: oggetto_mappa.entrySet())
            out.print(a.getKey()+" - "+a.getValue());
        
        out.print(" (attributo d'applicazione)");
        }catch(Exception e){
                
            out.print("Non è presente questo oggetto negli <b>attributi d'applicazione</b>.");
          
        }
        
        String nome = "Pagina B";
        pageContext.setAttribute("oggetto_pagina", nome); // settiamo questa var solo in questo contesto di pagina. Quello settato dalla pagina A è differente! è un altro pageContext!
            
        try{
        %>
        <br/><br/>
        <jsp:useBean id="oggetto_pagina" type="java.lang.String" scope="page" /> <!-- Anche a seconda del tipo di classe può dare eccezioni di mancata istanziazione -->
        <%
        
        out.print(oggetto_pagina);
       
        }catch(Exception e){
            out.print("Non è presente questo oggetto nello scope <b>page</b>.");
        }
        %>
        
    </body>
</html>
