package tagHandler.bodyTag;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class IteratorTagSimpleBis extends BodyTagSupport{
    private Collection collection; // Attributo passato dal Tag
    private Iterator iterator;
    private Object o;
    
    public void setCollection (Collection collection) {
	this.collection=collection;
    }
    
    public int doStartTag() throws JspException { // Solamente nello doStartTag() si processa il contenuto del body o si ignora.
	return collection.size() > 0? EVAL_BODY_BUFFERED : SKIP_BODY;
    }
    
    public void doInitBody() throws JspException { // Eseguito una volta sola
	iterator=collection.iterator();
	o = iterator.next();
	pageContext.setAttribute("item", o); // item preso dall'azione predefinita useBean con scope='page'
    }
    
    public int doAfterBody() throws JspException { // Ci permette di parsare il body del Tag, preso con getBodyContent().
	String repl = getBodyContent().getString(); // body del Tag
        String writeThis = "";
	
        do {
	    writeThis += repl.replace("REPL_ITEM", o.toString());
	    o = iterator.next();
	    pageContext.setAttribute("item", o);
	} while (iterator.hasNext());
	writeThis += repl.replace("REPL_ITEM", o.toString());
	try {
	    getPreviousOut().print(writeThis); // stampiamo il body del tag
	}
	catch (java.io.IOException e) {
	    throw new JspException (e.getMessage());
	}
	return SKIP_BODY;
    }



}
