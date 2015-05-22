package example;

public class esempio4 {
    private int proprieta_privata;
    public esempio4(int i) {
	proprieta_privata = i;
    }
    public esempio4() {
    	proprieta_privata = 10;
    }
    public int getProprieta_privata() {
	return proprieta_privata;
    }
    public void setProprieta_privata(int i) {
	proprieta_privata = i;
    }
}