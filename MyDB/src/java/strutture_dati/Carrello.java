
package strutture_dati;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Rocco
 */
public class Carrello {
    
     public Map<Integer, Integer> prodotti = new HashMap(); // <Id_prodotto, Quantità>
     
     
                public Carrello(SimpleEntry<Integer, Integer>... x ){ // Costruttore carrello con un numero arbitratrio di prodotti.
                    for (SimpleEntry<Integer, Integer> e: x)
                        aggiungi(e.getKey(), e.getValue());
                    
                }
                
                public void aggiungi(int prodotto, int quantita){
                    // aggiunta con incremento sulla quantita' ...
                    if (prodotti.containsKey(prodotto))
                        prodotti.put(prodotto, prodotti.get(prodotto)+quantita);
                    else
                        prodotti.put(prodotto, 1);
                }
                
                public void rimuovi(int prodotto, int quantita){
                    // Rimozione con decremento sulla quantita' ...
                    if (prodotti.containsKey(prodotto))
                        if ((prodotti.get(prodotto) - quantita) < 1)
                            prodotti.remove(prodotto);
                        else
                            prodotti.put(prodotto, prodotti.get(prodotto)-quantita);
                    
                    //prodotti.remove(prodotto); // Elimina completamente il prodotto dal carrello qualuque sia la quantità
                    
                }
                
                /*
                public String stampa_per_il_web(){
                    
                    String output = "<ul>";
                    if (!prodotti.isEmpty())
                    for (Entry e: prodotti.entrySet())
                        output = output + "<li>"+ catalogo.get(e.getKey())+" x "+e.getValue()+"</li>";
                    
                    output += "</ul>";
                    return output;
                }
                */
                
}