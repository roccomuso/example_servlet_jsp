<%-- 

Pagina per l'invio di una eMail.

  Richiede le librerie:
  - javax.mail.jar
  - activation-1.1.jar (JavaBeans Activation Framework)
  Quest'ultima se si usa Java 6 è già pre-installata.
 
 

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina per l'invio di una mail.</title>
    </head>
    <body>
        <h1>Compila il form sottostante per inviare una email:</h1>
        
        <form action="invia_mail.jsp" method="POST">
            
            <input type="email" name="destinatario" placeholder="Inserisci destinatario" required/><br/><br/>
            <input type="text" name="oggetto" placeholder="Inserisci oggetto" required/><br/><br/>
            
            <textarea name="testo" rows="10" cols="25" placeholder="Inserisci il testo della Mail..." required></textarea>
            <br/>
            <input type="submit" value="Invia Mail" />
                 
        </form>
        
    </body>
</html>
