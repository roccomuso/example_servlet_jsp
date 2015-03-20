package servletSaluto;

//Affinchè venga correttamente interpretato dall'IDE è necessario aggiungere le librerie servlet-api.jar al ClassPath.
// Creato il progetto (senza main class), tasto destro su Libraries e add JAR/Folder e aggiungere tutte le librerie (.jar) presenti in C:\xampp\tomcat\lib (non la directory ma tutti i jar).
// Modificare nel file project.properties la voce build.classes.dir=../../classes

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;


public class ServletDiSaluto extends HttpServlet{

// processiamo le richieste GET dal client
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        
        // Inviamo la pagina HTML di risposta al client
        
        out.println("<html><head><title>Risposta output HTML</title></head>");
        out.println("<body>");
        out.println("<h1>Ciao come va?</h1><br/><p>Prova pagina richiesta GET</p>");
        out.println("</body></html>");
        
    }

}