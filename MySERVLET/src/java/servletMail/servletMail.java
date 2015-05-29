/*
 * Servlet per l'invio di una Mail.
 */

package servletMail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*; // librerie per l'invio di mail
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servletMail extends HttpServlet {

    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletMail</title>");            
            out.println("</head>");
            out.println("<body>");
            
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
            
            out.println("Mail inviata con successo.");
            
            out.println("</body>");
            out.println("</html>");
        }
        catch(MessagingException e){
            //PrintWriter out = response.getWriter();
            out.print(e.getMessage());
        }
        catch(Exception e){
            //PrintWriter out = response.getWriter();
            out.print(e.getMessage());
        }
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        processRequest(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        processRequest(request, response);
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet per l'invio di Email.";
    }// </editor-fold>

}
