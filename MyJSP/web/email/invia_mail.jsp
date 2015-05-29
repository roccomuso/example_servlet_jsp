<%-- 

Pagina per l'invio di una eMail.

  Richiede le librerie:
  - javax.mail.jar
  - activation-1.1.jar (JavaBeans Activation Framework)
  Quest'ultima se si usa Java 6 è già pre-installata.
 
 

--%>

<%@page import="javax.mail.Message.RecipientType"%>
<%@page import="javax.mail.internet.*"%>
<%@page import="javax.mail.*"%>
<%@page import="java.util.Properties"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invio Mail...</title>
    </head>
    <body>
        
        <%
            
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.starttls.enable","true"); // perchè il server hotmail necessita una comunicazione con TLS.
            // ci sono anche altre proprietà se necessarie
            
            Session sendMailSession = Session.getInstance(props);
            
            MimeMessage newMessage = new MimeMessage(sendMailSession);
             // settiamo gli attributi della mail
            newMessage.setSubject(request.getParameter("oggetto"), "UTF-8");
            newMessage.setText(request.getParameter("testo"));
            
            newMessage.setHeader("Content-Type", "text/plain; charset=UTF-8"); // Impostiamo la codifica della mail
            
            InternetAddress from = new InternetAddress("rocco@hackerstribe.com"); // Mittente - IN REALTà NON FUNZIONA se ci si autentica su hotmail.
            newMessage.setFrom(from);
            
            InternetAddress to = new InternetAddress(request.getParameter("destinatario")); // Destinatario
            newMessage.addRecipient(RecipientType.TO, to); // ma anche RecipientType.CC/BCC  (carbon copy, blind carbon copy etc.) // si possono aggiungere più destinatari, chiamando nuovamente questo metodo
            // OPPURE: newMessage.addRecipients(RecipientType.TO, "roccomusolino92@gmail.com, rocco@hackerstribe.com, io@roccomusolino.com"); // notare il metodo che si chiama addRecipients <-- con la se finale.
            
            Transport.send(newMessage, "rhp@hotmail.it", "password"); // Username e Password per l'autenticazione al server SMTP.
            
        %>
        
        Mail inviata correttamente!
    </body>
</html>
