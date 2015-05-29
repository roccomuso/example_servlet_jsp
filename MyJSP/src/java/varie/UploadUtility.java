/*
 * Classe d'utility per le funzionalità d'upload.
 */

package varie;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.fileupload.FileItem; // libreria commons-fileupload-1.0.jar

/**
 *
 * @author Rocco
 */
public class UploadUtility {
    
    public static String seek(List items, String parameter_name){
    
        Iterator itr = items.iterator();
        while(itr.hasNext()){
            FileItem item = (FileItem) itr.next();
                // controlliamo se si tratta di un campo del form oppure di un file caricato
                if (item.isFormField()){ // se non è del tipo: type="file".
                    String parameterName = item.getFieldName();
                    String parameterValue = item.getString();
                    if (parameterName.equals(parameter_name))
                        return parameterValue; // restituiamo il valore del parametro cercato (parameter_name)
                }
        }
        
        return null; // se non trova il parametro cercato, restituisce null
    }


}
