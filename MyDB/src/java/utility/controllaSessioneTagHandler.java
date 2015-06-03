/*
 * Tag Handler del tag controllaSessione.
 */

package utility;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Rocco
 */
public class controllaSessioneTagHandler extends SimpleTagSupport {
    
    private HttpSession sessione; // Ricevere come attributo del tag, la sessione!
    private HttpServletRequest richiesta;
    private HttpServletResponse risposta;
   
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            
            if (sessione.getAttribute("username") != null){
                // sessione già esistente, utente loggato.
                // Redirect...
                RequestDispatcher dp = richiesta.getRequestDispatcher("home.jsp");
                dp.forward(richiesta, risposta);
                
            }else{
                // Stampiamo form di login.
                out.print("<h2>Effettua il login!</h2>");
                out.print("<form action='doLogin' method='POST'>");
                out.print("<input type='text' name='username' placeholder='Username...'/><br/>");
                out.print("<input type='password' name='password' placeholder='Password...'/><br/>");
                out.print("<input type='submit' value='Login' />");
                out.print("</form>");
                
            }
            
            
        } catch (java.io.IOException ex) {
            throw new JspException("Error in controllaSessione tag", ex);
        } catch (ServletException ex) {
            Logger.getLogger(controllaSessioneTagHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSessione(HttpSession sessione) {
        this.sessione = sessione;
    }
    
    public void setRichiesta(HttpServletRequest richiesta){
        this.richiesta = richiesta;
    }
    
    public void setRisposta(HttpServletResponse risposta){
        this.risposta = risposta;
    }
    
}
