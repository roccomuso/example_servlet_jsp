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
    
	public int doStartTag() throws JspException { // nello start tag solo o si processa il contenuto del body o si ignora.
	return collection.size() > 0? EVAL_BODY_BUFFERED : SKIP_BODY;
    }
    
	public void doInitBody() throws JspException {
	iterator=collection.iterator();
	pageContext.setAttribute("item", iterator.next());
	try {
		getPreviousOut().print("current doInitBody \"<font size=\"4\" color=\"blue\">" + getBodyContent().getString()+ "</font>\"<br/>");
	}
	catch (java.io.IOException e) {
	    throw new JspException (e.getMessage());
	}
    }
    
	public int doAfterBody() throws JspException {
	if (iterator.hasNext()) {
	    try {
		getPreviousOut().print("current doAfterBody \"<font size=\"4\" color=\"blue\">" + getBodyContent().getString()+ "</font>\"<br/>");
	    }
	    catch (java.io.IOException e) {
	    	throw new JspException (e.getMessage());
	    }
	    pageContext.setAttribute("item", iterator.next());
	    return EVAL_BODY_AGAIN;
	}
	else {
	    try {
	    	//  abstract  void 	writeOut(java.io.Writer out)
	    	// Write the contents of this BodyContent into a Writer.
		getPreviousOut().print("<font size=\"4\" color=\"red\">One way: </font>" + getBodyContent().getString());
		getPreviousOut().print("<br/><font size=\"4\" color=\"red\">Other way: </font>");
	    	getBodyContent().writeOut(getPreviousOut());
	    	getPreviousOut().print("Ciao2");
	    }
	    catch (java.io.IOException e) {
	    	throw new JspException (e.getMessage());
	    }
	    return SKIP_BODY;
	}
    }

	
}
