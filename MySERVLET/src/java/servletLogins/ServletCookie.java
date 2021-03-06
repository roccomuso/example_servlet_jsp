
package servletLogins;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.*;

public class ServletCookie extends HttpServlet {

    public static Map<String, String> utenti = new HashMap<>(); // HashMap<String, String>
    
    //public List<String> x = new LinkedList<>(); // LinkedList<String>
    //public List<String> y = new ArrayList<>(); // ArrayList<String>
    
    
    @Override
    public void init(){ // E' un metodo della Classe HttpServlet!! eseguito all'avvio.
        utenti.put("Rocco", "123456");
        utenti.put("Ilaria", "password2");
        utenti.put("admin", "admin");
        utenti.put("123456", "123456");
    }
    
    public Cookie[] logout(HttpServletRequest request, HttpServletResponse response){
        
            HttpSession sessione = request.getSession();
            sessione.invalidate();
         
            // eliminiamo anche i cookie.
            Cookie cookie[] = request.getCookies();
            
            if (cookie != null)
                for (int i=0; i < cookie.length; i++) {
                    cookie[i] = new Cookie(cookie[i].getName(), ""); // resettiamo i cookie.
                    response.addCookie(cookie[i]);
            }
            
            return cookie;
    }
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        Cookie cookie[]; // ottenere i cookie del client può avvenire soltanto nel metodo doGet()
        
        // LOGOUT (se presente il parametro GET "logout")
        if (request.getParameter("logout") != null)
            cookie = logout(request, response); // logout
            //cookie[i].setMaxAge(0); // dovrebbe essere ancora più efficace, settando a 0, viene subito eliminato dal client.
        else cookie = request.getCookies();
        
        
        String mex = "Non puoi accedere a questa pagina senza prima aver effettuato il <a href='./logins.html'>login</a>.";
        if (cookie != null){
        // Inseriamo gli oggetti Cookie dell'array cookie[] in una mappa chiamata cookies
        Map<String, String> cookies = new HashMap<>();
        for (Cookie a: cookie)
            cookies.put(a.getName(), a.getValue());
        
        if (cookies.containsKey("login"))
        if (cookies.get("login").equals("OK")){
            // Utente già loggato nel sistema.
            mex = "<h2>Benvenuto <u>"+cookies.get("username")+"</u></h2>";
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
    
      String mex = "Prego inserire delle credenziali d'accesso e <a href='./logins.html'>riprovare</a>.";
      
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
              
              mex = "Login avvenuto con successo. <a href='./AliasLoginCookie'>Ricarica la pagina</a>!";
          }else{
              //Credenziali errate.
              mex = "Password errata! <a href='./logins.html'>Riprova</a>";
              
          }
          else mex = "Username inesistente! <a href='./logins.html'>Riprova</a>";
    
        }
    
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>esito</title></head><body>"+mex+"</body></html>");
              
            out.close();
      
    }
    
    @Override
    public void destroy(){ // Chiamato alla fine d'esecuzione di una servlet
    
    }
    
}
