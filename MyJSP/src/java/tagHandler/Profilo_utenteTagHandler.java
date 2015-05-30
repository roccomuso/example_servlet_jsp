/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tagHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Rocco
 */
public class Profilo_utenteTagHandler extends SimpleTagSupport {
   
    private int id; // automaticamente gli attributi devono essere definiti come private <tipo> <nome_attributo>.
    
    Map<Integer, String> db = new HashMap();
    
    public void inizializza(){
        db.put(1, "Rocco Musolino ...");
        db.put(2, "Tony Stark ...");
        db.put(3, "Fetorio Bellapanza ...");
    }

    @Override
    public void doTag() throws JspException, IOException {
        inizializza();
        JspWriter out = getJspContext().getOut();
        
        
        if (!db.containsKey(id)) out.print("L'utente non esiste!");
        else
            out.println("L'id passato è: "+id+" corrispondente a "+db.get(id));
        
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
