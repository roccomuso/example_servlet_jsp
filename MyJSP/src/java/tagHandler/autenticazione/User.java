

package tagHandler.autenticazione;

public class User implements java.io.Serializable { // Questo è un BEAN
    
    private final String userName, password, hint;
    
    //costruttore
    public User(String userName, String password, String hint) {
	this.userName=userName;
	this.password=password;
	this.hint=hint;
    }
    
    //metodi getter
    public String getUserName() {return userName;}
    public String getPassword() {return password;}
    public String getHint() {return hint;}
    
    //metodo che controlla se il bean utente ha il nome uname e una password pwd
    public boolean equals(String uname, String pwd) {
	return (getUserName().equals(uname) && getPassword().equals(pwd));
    }

}
