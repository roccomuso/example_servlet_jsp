
package beans; // importante specificare il package.

/**
 *
 * @author Rocco
 */
public class Utente {
    
    private String nome, cognome; // I bean devono avere sempre gli attributi di classe privati.

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    public Utente() { // costruttore vuoto. (quando nel bean si specifica class='' altrimenti col costruttore pieno va bene anche type='')
       
        
    }
    
    
    
}
