package tagHandler.bodyTag;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/*
Gli esempi di questa cartella mostrano come manipolare il contenuto dei Tag personalizzati in modo interattivo.
*/

public class IteratorTag extends BodyTagSupport{ // Se vogliamo interagire col body del tag, il nostro tag Handler deve estendere questa classe BodyTagSupport
	
    private Collection collection; // Attributo del tag
    private Iterator iterator;
    
	public void setCollection (Collection collection) {
	this.collection=collection;
    }
    
    @Override
	public int doStartTag() throws JspException { // Solamente nello doStartTag() si processa il contenuto del body o si ignora.
	return collection.size() > 0? EVAL_BODY_BUFFERED : SKIP_BODY;
    }
    
    @Override
	public void doInitBody() throws JspException {
	iterator=collection.iterator();
	pageContext.setAttribute("item", iterator.next());
	try {
		getPreviousOut().print("current doInitBody <br/>\"<font size=\"4\" color=\"blue\">" + getBodyContent().getString()+ "</font>\"<br/>");
	}
	catch (java.io.IOException e) {
	    throw new JspException (e.getMessage());
	}
    }
    
    @Override
	public int doAfterBody() throws JspException {
	if (iterator.hasNext()) {
	    try {
		getPreviousOut().print("current doAfterBody <br/>\"<font size=\"4\" color=\"blue\">" + getBodyContent().getString()+ "</font>\"<br/>");
	    }
	    catch (java.io.IOException e) {
	    	throw new JspException (e.getMessage());
	    }
	    pageContext.setAttribute("item", iterator.next());
	    return EVAL_BODY_AGAIN; // Riesegue doAfterBody() fintanto che iterator.hasNext()
	}
	else {
	    try {
	    	//  abstract  void 	writeOut(java.io.Writer out)
	    	// Write the contents of this BodyContent into a Writer.
		getPreviousOut().print("<font size=\"4\" color=\"red\">Un modo di stampare: </font><br/>" + getBodyContent().getString());
		getPreviousOut().print("<br/><font size=\"4\" color=\"red\">Altro modo: </font><br/>");
	    	getBodyContent().writeOut(getPreviousOut());
	    	
	    }
	    catch (java.io.IOException e) {
	    	throw new JspException (e.getMessage());
	    }
	    return SKIP_BODY;
	}
    }

	
}
