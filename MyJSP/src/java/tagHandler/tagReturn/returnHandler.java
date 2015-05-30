/*
 * Handler del tag 'return'
 */

package tagHandler.tagReturn;


import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import static javax.servlet.jsp.tagext.Tag.SKIP_PAGE;
import javax.servlet.jsp.tagext.TagSupport;

public class returnHandler extends TagSupport {
    // Attributi passati al Tag
    private boolean valutaPagina;
    private boolean valutaBodyTag;

    public boolean isvalutaBodyTag() {
        return valutaBodyTag;
    }

    public void setvalutaBodyTag(boolean valutaBodyTag) {
        this.valutaBodyTag = valutaBodyTag;
    }

    public boolean isvalutaPagina() {
        return valutaPagina;
    }

    public void setvalutaPagina(boolean valutarestante) {
        this.valutaPagina = valutarestante;
    }


    @Override
    public int doStartTag(){ // chiamato all'apertura del Tag (valuta il tag d'apertura)
        // decidiamo se permettere la visualizzazione del body del Tag o meno. (Qui uno SKIP_PAGE non funziona, perchè quello va chiamato nel doEndTag().
        
        if (valutaBodyTag)
            return EVAL_BODY_INCLUDE; // Fa processare il Body del tag.
        else
            return SKIP_BODY; // Ignora il contenuto del Tag, rimuove il Body dunque:  <tag>...body...</tag>
        
    }
    
    @Override
    public int doEndTag() throws JspException { // chiamato alla chiusura del Tag (valuta il tag di chiusura)
        // Con doEndTag() decidiamo se permettere la visualizzazione del resto della pagina
	
        if (valutaPagina)
            return EVAL_PAGE; // Valuta il contenuto della pagina dopo il tag di chiusura.
        else
            return SKIP_PAGE; // Ignora il contenuto della pagina dopo il tag di chiusura.
        
    }
   
    
}