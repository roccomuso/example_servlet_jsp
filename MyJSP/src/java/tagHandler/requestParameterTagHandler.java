
package tagHandler;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
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
        String value = req.getParameter(property);
        try {
            pageContext.getOut().print(value == null ? "" : value);
        } catch (java.io.IOException ex) {
            throw new JspException(ex.getMessage());
        }
        return SKIP_BODY;
    }

}
