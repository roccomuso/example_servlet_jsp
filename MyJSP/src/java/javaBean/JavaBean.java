

package javaBean;



public class javaBean {
    
    /*
    ESEMPIO DI JAVA BEAN PER PROCESSARE DATI PROVENIENTI DA UN FORM.
    
    Tramite NetBeans, definiti agli attributi con tasto destro si possono inserire automaticamente metodi getter e setter.
    */
    
    private String nome, cognome, email;

    public javaBean(){ // lo standard java bean richiede costruttore vuoto, e setter e getter per ogni attributo.
    }
        
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNome(){
    return nome;
    }
    
    
}
