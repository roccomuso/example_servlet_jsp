package tagHandler.bodyTag;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

/*
Gli esempi di questa cartella mostrano come manipolare il contenuto dei Tag personalizzati in modo interattivo.
*/

public class esempiTagHandler extends BodyTagSupport{ // Se vogliamo interagire col body del tag, il nostro tag Handler deve estendere questa classe BodyTagSupport
	
    private Collection collection; // Attributo del tag
    private Iterator iterator;
    
    
	public void setCollection (Collection collection) {
	this.collection=collection;
    }
    
    @Override
	public int doStartTag() throws JspException { // Solamente nello doStartTag() si processa il contenuto del body o si ignora.
	return collection.size() > 0? EVAL_BODY_BUFFERED : SKIP_BODY; // se la collezione passata non è vuota, processiamo il body, altrimenti ignoriamolo.
        
    }
    
        int chiamate_dib = 0;
    @Override
	public void doInitBody() throws JspException { // Eseguito una volta sola a inizializzazione del body.
            chiamate_dib++;
            iterator=collection.iterator();
        try{
            
            getPreviousOut().print("chiamato doInitBody().<br/><br/>");
            pageContext.setAttribute("item", iterator.next()); // Così il primo print da parte di doAfterBody, non sarà null.
            
            
        }catch (IOException e){
        
        }
        
        }
    
    
        int chiamate_dab = 0;
    @Override
	public int doAfterBody() throws JspException {
            chiamate_dab++;
            String body_attuale = getBodyContent().getString(); // alla prima iterazione è il body così come scritto nell'HTML. Salvo aggiunte da parte di doInitBody(), che serve di solito a evitare che alla prima iterazione eventuali <%= item %> siano vuoti.

        try {
            if (iterator.hasNext()) {
	    // getPreviousOut().print("..."); //
                // getBodyContent().getString() //
                
                pageContext.setAttribute("item", iterator.next());
                return EVAL_BODY_AGAIN; // Riesegue doAfterBody() fintanto che iterator.hasNext() e valuta il body del tag col nuovo valore.
            } else {

                // Se non ci sono altri elementi su cui iterate, stampa il body creato fin'ora più FINE:
                getPreviousOut().print(body_attuale + "<br/>FINE");
                getPreviousOut().print("<br/>Chiamate totali a doInitBody: "+chiamate_dib);
                getPreviousOut().print("<br/>Chiamate totali a doAfterBody: "+chiamate_dab);
                
                return SKIP_BODY; // EVAL_BODY_AGAIN Riesegue doAfterBody() fintanto che iterator.hasNext() - SKIP_BODY qui serve a niente, il body del tag è già stato stampato.
            }

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        
    }

	
}
