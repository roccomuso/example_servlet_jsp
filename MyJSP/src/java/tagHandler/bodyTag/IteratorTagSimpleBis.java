package tagHandler.bodyTag;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class IteratorTagSimpleBis extends BodyTagSupport{
    private Collection collection;
    private Iterator iterator;
    private Object o;
    public void setCollection (Collection collection) {
	this.collection=collection;
    }
    public int doStartTag() throws JspException {
	return collection.size() > 0? EVAL_BODY_BUFFERED : SKIP_BODY;
    }
    public void doInitBody() throws JspException {
	iterator=collection.iterator();
	o = iterator.next();
	pageContext.setAttribute("item", o);
    }
    public int doAfterBody() throws JspException {
	String repl = getBodyContent().getString(), writeThis = "";
	do {
	    writeThis += repl.replace("REPL_ITEM", o.toString());
	    o = iterator.next();
	    pageContext.setAttribute("item", o);
	} while (iterator.hasNext());
	writeThis += repl.replace("REPL_ITEM", o.toString());
	try {
	    getPreviousOut().print(writeThis);
	}
	catch (java.io.IOException e) {
	    throw new JspException (e.getMessage());
	}
	return SKIP_BODY;
    }
}
