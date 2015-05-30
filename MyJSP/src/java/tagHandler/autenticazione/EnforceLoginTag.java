/*
 * 
 */

package tagHandler.autenticazione;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class EnforceLoginTag extends TagSupport {
    private String loginPage, errorPage;
    public void setLoginPage (String loginPage) {
	this.loginPage=loginPage;
    }
    public void setErrorPage (String errorPage) {
	this.errorPage=errorPage;
    }
    
    @Override
    public int doEndTag() throws JspException { // Il metodo doEndTag() decide se permettere la visualizzazione del resto della pagina
	HttpSession session = pageContext.getSession();
	HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
	//usa una var protectedPage per memorizzare la pagina richiesta
	//cui fare ritorno dopo l’eventuale redirezione verso la login-page
	String protectedPage = req.getRequestURI();
	protectedPage = protectedPage.substring(protectedPage.indexOf("/", 2));
	if (session.getAttribute("user")==null) {
	    session.setAttribute("login-page", loginPage);
	    session.setAttribute("error-page", errorPage);
	    session.setAttribute("protected-page", protectedPage);
	    try {
		pageContext.forward(loginPage);
		return SKIP_PAGE; // Ignora il resto della pagina
	    }
	    catch (Exception ex) {
		throw new JspException(ex.getMessage());
	    }
	}
	return EVAL_PAGE; //eseguito se l’attributo user viene trovato nella sessione
    }
    public void release() {
	loginPage=errorPage=null;
    }
}
