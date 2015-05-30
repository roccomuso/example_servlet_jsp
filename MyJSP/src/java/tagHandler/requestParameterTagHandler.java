
package tagHandler;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.*;

/*
 *
 * @author Rocco
 */
public class requestParameterTagHandler extends TagSupport { // Si implementa l'interfaccia Tag estendendo la classe TagSupport.
    
    private String property;

    public void setProperty(String valore) {
        this.property = valore;
    }

    @Override
    public int doStartTag() throws JspException {
        ServletRequest req = pageContext.getRequest();
        HttpSession session = pageContext.getSession();
        String value = (String) session.getAttribute(property); // Prendiamo i parametri già impostati e li riproponiamo.
        
        try {
            pageContext.getOut().print(value == null ? "" : value);
        } catch (java.io.IOException ex) {
            throw new JspException(ex.getMessage());
        }
        return SKIP_BODY;
    }

}
