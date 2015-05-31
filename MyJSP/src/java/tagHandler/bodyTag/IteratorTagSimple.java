package tagHandler.bodyTag;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class IteratorTagSimple extends BodyTagSupport{
    
    private Collection collection;
    private Iterator iterator; // Attributo passato dal tag
    
    public void setCollection (Collection collection) {
	this.collection=collection;
    }
    
    @Override
    public int doStartTag() throws JspException { // Solamente nello doStartTag() si processa il contenuto del body o si ignora.
	return collection.size() > 0? EVAL_BODY_BUFFERED : SKIP_BODY;
    }
    
    @Override
    public void doInitBody() throws JspException { // Eseguito una volta sola.
	iterator=collection.iterator();
    }
    
    @Override
    public int doAfterBody() throws JspException { // Ci permette di parsare il body del Tag, preso con getBodyContent().
	String repl = getBodyContent().getString(); // Contenuto del body!
        String writeThis = "";
        
	while (iterator.hasNext()) {
	    writeThis += repl.replace("REPL_ITEM", iterator.next().toString());
	}
	try {
	    getPreviousOut().print(writeThis); // stampiamo nel body del tag.
	}
	catch (java.io.IOException e) {
	    throw new JspException (e.getMessage());
	}
	return SKIP_BODY; // Ignoriamo tanto è già stato trattato
    }
}
