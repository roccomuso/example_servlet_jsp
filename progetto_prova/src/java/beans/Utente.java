/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

/**
 *
 * @author Rocco
 */
public class Utente {
    
    private String nome, cognome;

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
