
package tagHandler;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;

/**
 *
 * @author Rocco
 */
public class WelcomeTagHandler extends TagSupport { // Si implementa l'interfaccia Tag estendendo la classe TagSupport.
    
    // Metodo chiamato per iniziare il processing dei tag
    @Override
    public int doStartTag() throws JspException{ // Il JSP Container invoca doStartTag() quando incontra l'apertura di un tag personalizzato.
    
    try{
        // Otteniamo il writer per fare l'output.
        JspWriter out = pageContext.getOut(); // pageContext è ereditato da TagSupport.
        
        out.print("Messaggio proveniente dal Tag");
        
    }catch(IOException ioException){
        throw new JspException(ioException.getMessage());
    }
    
    return SKIP_BODY;
    
    }
}
