
package varie;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;

/**
 *
 * @author Rocco
 */
public class CookieTools {
    
    public static boolean isSignedIn(Cookie[] cookies){ // controlla se esiste il cookie che contraddistingue un login effettuato con successo.
    
        boolean loggato = false;
        
        for (Cookie e: cookies)
            if (e.getName().equals("loggato"))
                loggato = true;
        
        return loggato;
        
    }
    
    public static boolean isSet(String name, Cookie[] cookies){ // controlla l'esistenza di un cookie dato il nome.
    
        boolean esiste = false;
        
        for (Cookie e: cookies)
            if (e.getName().equals(name))
                esiste = true;
        
        return esiste;
            
    }
    
    public static Map<String, String> convert_Cookie_to_Map(Cookie[] cookies){ // Converte un array di Cookie in un HashMap.
    
        // Facciamo una mappa, perchè è più pratica dell'array cookies.
        Map<String, String> COOKIES = new HashMap();
   
        
        for (Cookie e: cookies)
            COOKIES.put(e.getName(), e.getValue());
        
        return COOKIES;
    }
    
}
