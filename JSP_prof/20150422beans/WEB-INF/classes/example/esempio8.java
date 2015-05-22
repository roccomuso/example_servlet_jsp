package example;

public class esempio8 {
    private int proprieta_privata;
    int[] vettore = new int[1000];
    public esempio8(int i, int j) {
	proprieta_privata = i;
	for (int k = 0; k < 1000; k++)
	    vettore[k] = j + k;
    }
    public esempio8() {
    	proprieta_privata = 10;
    }
    public int getProprieta_privata() {
	return proprieta_privata;
    }
    public void setProprieta_privata(int i) {
	proprieta_privata = i;
    }
    public int getVettore() {
	return vettore[20];
    }
    public int get_vettore_ind(int i) {
	return vettore[i];
    }
}