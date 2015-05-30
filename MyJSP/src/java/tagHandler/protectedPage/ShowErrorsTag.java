/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tagHandler.protectedPage;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
public class ShowErrorsTag extends TagSupport {
    
    
    @Override
    public int doStartTag() throws JspException {
	String error = (String)pageContext.getSession().
	    getAttribute("login-error");
	if (error!=null) {
	    try {
		pageContext.getOut().print(error);
	    }
	    catch (java.io.IOException ex) {
		throw new JspException(ex.getMessage());
	    }
	}
	return SKIP_BODY;
    }
}
