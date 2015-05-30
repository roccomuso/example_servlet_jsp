package tagHandler.bodyTag;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class IteratorTagSimple extends BodyTagSupport{
    private Collection collection;
    private Iterator iterator;
    public void setCollection (Collection collection) {
	this.collection=collection;
    }
    public int doStartTag() throws JspException {
	return collection.size() > 0? EVAL_BODY_BUFFERED : SKIP_BODY;
    }
    public void doInitBody() throws JspException {
	iterator=collection.iterator();
    }
    public int doAfterBody() throws JspException {
	String repl = getBodyContent().getString(), writeThis = "";
	while (iterator.hasNext()) {
	    writeThis += repl.replace("REPL_ITEM", iterator.next().toString());
	}
	try {
	    getPreviousOut().print(writeThis);
	}
	catch (java.io.IOException e) {
	    throw new JspException (e.getMessage());
	}
	return SKIP_BODY;
    }
}
