package varie;

/*
 * Classe usata per gli esempi di 'sharing_data' fra risorse.
 */


public class Animale {
            private int zampe;
            private String tipo;
            private boolean coda;
            
            public Animale(String tipologia, int Zampe, boolean Coda){
                this.tipo = tipologia;
                this.zampe = Zampe;
                this.coda = Coda;
            }

    /* Getter and Setter */
    public int getZampe() {
        return zampe;
    }

    public void setZampe(int zampe) {
        this.zampe = zampe;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isCoda() {
        return coda;
    }

    public void setCoda(boolean coda) {
        this.coda = coda;
    }
            
            
}
