
package varie;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Rocco
 */
public class Carrello {
    
     public Map<String, Integer> prodotti = new HashMap();
     public Map<String, String> catalogo = new HashMap();
     
                public Carrello(){ // inizializziamo il catalogo.
                    catalogo.put("item1", "Dell XPS 13 pollici");
                    catalogo.put("item2", "Moto 360");
                    catalogo.put("item3", "Raspberry Pi");
                    
                }
                
                public void aggiungi(String prodotto, int quantita){
                    // aggiunta con incremento sulla quantita ...
                    if (prodotti.containsKey(prodotto))
                        prodotti.put(prodotto, prodotti.get(prodotto)+quantita);
                    else
                        prodotti.put(prodotto, 1);
                }
                
                public void rimuovi(String prodotto){
                    // Rimozione
                    prodotti.remove(prodotto);
                }
                
                public String stampa_per_il_web(){
                    
                    String output = "<ul>";
                    if (!prodotti.isEmpty())
                    for (Entry e: prodotti.entrySet())
                        output = output + "<li>"+ catalogo.get(e.getKey())+" x "+e.getValue()+"</li>";
                    
                    output += "</ul>";
                    return output;
                }
                
}
