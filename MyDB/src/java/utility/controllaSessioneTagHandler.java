/*
 * Tag Handler del tag controllaSessione.
 */

package utility;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Rocco
 */
public class controllaSessioneTagHandler extends SimpleTagSupport {
    
    private HttpSession sessione; // Ricevere come attributo del tag, la sessione!

   
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            
            if (sessione.getAttribute("username") != null){
                // sessione già esistente, utente loggato.
                // Redirect...
                
            }else{
                // Stampiamo form di login.
                out.print("<form action='/doLogin' method='POST'>");
                out.print("<input type='text' name='username' placeholder='Username...'/><br/>");
                out.print("<input type='password' name='password' placeholder='Password...'/><br/>");
                out.print("<input type='submit' value='Login' />");
                out.print("</form>");
            }
            
            
        } catch (java.io.IOException ex) {
            throw new JspException("Error in controllaSessione tag", ex);
        }
    }

    public void setSessione(HttpSession sessione) {
        this.sessione = sessione;
    }
    
}
