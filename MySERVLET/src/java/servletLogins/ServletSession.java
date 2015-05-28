
package servletLogins;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletSession extends HttpServlet {

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
    
    public Cookie[] logout(HttpServletRequest request, HttpServletResponse response){
        
            HttpSession sessione = request.getSession();
            sessione.invalidate();
         
            // eliminiamo anche i cookie.
            Cookie cookie[] = request.getCookies();
            
            if (cookie != null)
                for (int i=0; i < cookie.length; i++) {
                    cookie[i] = new Cookie(cookie[i].getName(), ""); // resettiamo i cookie.
                    //cookie[i].setMaxAge(0); // dovrebbe essere ancora più efficace, settando a 0, viene subito eliminato dal client.
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
        else cookie = request.getCookies();
        
        
        HttpSession sessione = request.getSession(); // il parametro true fa sì che venga creata una nuova sessione se non esiste.
        PrintWriter out = response.getWriter();
        
        String mex = "Login non effettuato (session['login'] = null). <a href='./logins.html'>Vai al Login</a>.";
        if (sessione.getAttribute("login") != null){
              if (sessione.getAttribute("login").equals("OK"))
                  mex = "<h2>Sessione valida, benvenuto <u>"+sessione.getAttribute("username")+"</u></h2>";
        
            
            Date date=new Date(sessione.getLastAccessedTime()); // perchè getLastAccessedTime() restituisce i millisecondi passati dal 1970.
            Date date2=new Date(sessione.getCreationTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            out.println("<html><head><title>esito</title></head><body>"+mex+"<br/><br/>"
                    + "E' nuova: " + sessione.isNew()
                    + "<br/>ID sessione: " + sessione.getId()
                    + "<br/>Creata il: " + sdf.format(date2)
                    + "<br/>Massimo tempo inattività: " + sessione.getMaxInactiveInterval()
                    + "<br/>Ultimo accesso: " + sdf.format(date)
                    + "</body></html>");
              
            
            
        }else out.println("<html><head><title>Risposta</title></head><body>"+mex+"</body></html>");
        
        out.close();
    
    }
    
    public boolean check_login(HttpServletRequest request){
    
        String username = request.getParameter("username");
        String password = request.getParameter("password");
            
        if (utenti.containsKey(username))
          if (utenti.get(username).equals(password))
             return true; // Login corretto
        
        return false; // Login errato
              
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
    
      String mex = "Prego inserire delle credenziali d'accesso e <a href='./logins.html'>riprovare</a>.";
      
      HttpSession sessione = request.getSession(true); // il parametro true fa sì che venga creata una nuova sessione se non esiste.
      
      if (request.getParameter("username") != null && request.getParameter("password") != null){
          
          // conrolliamo se la sessione già esiste o è stata appena creata. (Se già esisteva allora utente già loggato).
          
          if (sessione.isNew()){// se è stata appena creata la sessione varrà true.
              
            
          if (check_login(request)){
              //Login avvenuto con successo
              sessione.setAttribute("login", "OK");
              sessione.setAttribute("username", request.getParameter("username"));
              
              mex = "Login avvenuto con successo. <a href='javascript:location.reload(true);'>Ricarica la pagina</a>!";
          }else{
              //Credenziali errate.
              mex = "Password errata! <a href='./logins.html'>Riprova</a>";
              sessione.setAttribute("login", "NO");
              
          }
          
              
              }else{ // la sessione già esiste verifichiamo se è frutto di un login genuino o meno o se sta tentanto nuovamente il login.
                
              
              if (sessione.getAttribute("login") != null)
              if (sessione.getAttribute("login").equals("OK"))
                  mex = "<h2>Sessione gia' presente, benvenuto <u>"+sessione.getAttribute("username")+"</u></h2>";
              
              if (sessione.getAttribute("login") == null){
                  if (check_login(request)){
                      sessione.setAttribute("login", "OK");
                      sessione.setAttribute("username", request.getParameter("username"));
                      mex = "(vecchia sessione gia' presente) - Login avvenuto con successo. <a href='javascript:location.reload(true);'>Ricarica la pagina</a>!";
                  }
                      
              }
                  
                
          }
          

    
        }else{ // non ha inviato nulla nella richiesta POST, controlliamo ugualmente la sessione.
          
          if (sessione.isNew()) mex = "Non hai inviato credenziali di login, sessione creata ugualmente.";
          
      
      }
    
            PrintWriter out = response.getWriter();
            Date date=new Date(sessione.getLastAccessedTime()); // perchè getLastAccessedTime() restituisce i millisecondi passati dal 1970.
            Date date2=new Date(sessione.getCreationTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            out.println("<html><head><title>esito</title></head><body>"+mex+"<br/><br/>"
                    + "E' nuova: " + sessione.isNew()
                    + "<br/>ID sessione: " + sessione.getId()
                    + "<br/>Creata il: " + sdf.format(date2)
                    + "<br/>Massimo tempo inattività: " + sessione.getMaxInactiveInterval()
                    + "<br/>Ultimo accesso: " + sdf.format(date)
                    + "</body></html>");
      
    }
    
    @Override
    public void destroy(){ // Chiamato alla fine d'esecuzione di una servlet
    
    }
    
}
