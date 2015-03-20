/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servletUrlEncode;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class ServletEncode extends HttpServlet{
    
    
    public static Map<String, String> utenti = new HashMap<>(); // HashMap<String, String>
    
    //public List<String> x = new LinkedList<>(); // LinkedList<String>
    //public List<String> y = new ArrayList<>(); // ArrayList<String>
    
    
    @Override
    public void init(){ // E' un metodo della Classe HttpServlet!! eseguito all'avvio.
        utenti.put("Rocco", "password");
        utenti.put("Ilaria", "password2");
        utenti.put("admin", "admin");
        utenti.put("123456", "123456");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        // ottenere i cookie del client può avvenire soltanto nel metodo doGet()
        Cookie cookie[] = request.getCookies();
       
        
        String mex = "Non puoi accedere a questa pagina senza prima aver effettuato il <a href='./encoding.html'>login</a>.";
        if (cookie != null){
            
        // Inseriamo gli oggetti Cookie dell'array cookie[] in una mappa chiamata cookies
        Map<String, String> cookies = new HashMap<>();
        for (Cookie a: cookie)
            cookies.put(a.getName(), a.getValue());
        
        // controlliamo se c'è stato un cambio username
        if (cookies.containsKey("username") && request.getParameter("usr") != null)
            response.addCookie(new Cookie("username",request.getParameter("usr")));
            
        
        if (cookies.containsKey("login"))
        if (cookies.get("login").equals("OK")){
            // Utente già loggato nel sistema.
            mex = "<h2>Benvenuto <u>"+cookies.get("username")+"</u></h2><p><a href='"+response.encodeURL("./AliasServletEncoding?usr=pippo")+"'>Modifica il tuo username</a> usando l'econding.</p>";
        }else{
            // Prima volta utente.
            mex = "Non hai i permessi per accedere a questa pagina.";
        }
        
        
        }
        
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Risposta</title></head><body>"+mex+"</body></html>");
        out.close();
    
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
    
      String mex = "Prego inserire delle credenziali d'accesso e <a href='./encoding.html'>riprovare</a>.";
      
      if (request.getParameter("username") != null && request.getParameter("password") != null){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
          
          if (utenti.containsKey(username))
          if (utenti.get(username).equals(password)){
              //Login avvenuto con successo
              Cookie cookie = new Cookie("login", "OK");
              Cookie cookie2 = new Cookie("username", username);
              response.addCookie(cookie);
              response.addCookie(cookie2);
              
              mex = "Login avvenuto con successo. <a href='javascript:location.reload(true);'>Ricarica la pagina</a>, per accedere al tuo pannello e aggiungere le info!";
          }else{
              //Credenziali errate.
              mex = "Password errata! <a href='./encoding.html'>Riprova</a>";
              
          }
          else mex = "Username inesistente! <a href='./encoding.html'>Riprova</a>";
    
        }
    
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>esito</title></head><body>"+mex+"</body></html>");
              
            out.close();
      
    }
    
    @Override
    public void destroy(){ // Chiamato alla fine d'esecuzione di una servlet
    
    }

    
}
