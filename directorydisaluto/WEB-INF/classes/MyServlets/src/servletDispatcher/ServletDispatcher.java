package servletDispatcher;

//Affinchè venga correttamente interpretato dall'IDE è necessario aggiungere le librerie servlet-api.jar al ClassPath.
// Creato il progetto (senza main class), tasto destro su Libraries e add JAR/Folder e aggiungere tutte le librerie (.jar) presenti in C:\xampp\tomcat\lib (non la directory ma tutti i jar).
// Modificare nel file project.properties la voce build.classes.dir=../../classes

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;


public class ServletDispatcher extends HttpServlet{

    public String myServletParam = null;
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException{
        this.myServletParam = servletConfig.getInitParameter("parametro_saluto"); // preso direttamente dal file web.xml della web-app. E associato solamente a questa servlet. E' un parametro di servlet non di contesto.
    }
    
// processiamo le richieste GET dal client
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response); // passamo i parametri POST come se fossero GET. (volendo si può fare anche il viceversa).
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String operation = request.getParameter("destinazione");
        if (operation == null)
            operation ="sconosciuta";
        
        switch (operation){
        
            case "chisono":
               gotoPage("/jsp/get_or_post.jsp?nome=Rocco", request, response);
                break;
            
            default:
                gotoPage("/jsp/get_or_post.jsp", request, response);
                break;
        }
      
        
    }
    
    public void gotoPage(String percorso, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // il percorso dev'essere all'interno della web-app, non può essere un url esterno, altrimenti bisognava usare sendRedirect().
        // la redirezione avviene in modo trasparente al client. Può essere usato sia per richieste GET che POST.
        RequestDispatcher dispatcher = request.getRequestDispatcher(percorso);
        dispatcher.forward(request, response);
        
    }

    
    
}