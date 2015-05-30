

package tagHandler.autenticazione;

import java.util.Iterator;
import java.util.Vector;
import tagHandler.autenticazione.User;

public class LoginDB implements java.io.Serializable { // Questo è un BEAN
    
    private Vector users = new Vector();
    private User[] defaultUsers = {
	new User("Picasso","Pablo","Il mio nome"), };
    
    //costruttore (aggiunge al vettore users tutti gli utenti di default)
    public LoginDB() {
	for (int i=0;i<defaultUsers.length; i++)
	    users.add((User)defaultUsers[i]);
    }
    
    //metodo adder (aggiunge al vettore users il bean utente con attributi dati)
    public void addUser(String uname, String pwd, String hint) {
	users.add((User)new User(uname,pwd,hint));
    }
    
    //metodo di ricerca del bean utente identificato da nome e password
    public User getUser(String uname, String pwd) {
	Iterator it = users.iterator();
	User bean;
	synchronized (users) {
	    while (it.hasNext()) {
		bean = (User)it.next();
		if (bean.equals(uname,pwd))
		    return bean;
	    }
	}
	return null;
    }
    
    //metodo di ricerca del suggerimento di un bean utente identificato da
    // un certo nome
    public String getHint(String uname) {
	Iterator it = users.iterator();
	User bean;
	synchronized (users) {
	    while (it.hasNext()) {
		bean = (User) it.next();
		if (bean.getUserName().equals(uname))
		    return bean.getHint();
	    }
	}
	return null;
    }
    
    
    
}
