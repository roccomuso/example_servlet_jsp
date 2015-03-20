
package servletREDIR;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;


public class ServletREDIRECT extends HttpServlet {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
    
      String pagina_richiesta = request.getParameter("page");
    
      if (pagina_richiesta != null)
        if (pagina_richiesta.equals("hacker"))
           response.sendRedirect("http://www.hackerstribe.com"); // Redirect a percorso assoluto (anche domini esterni)
        else if (pagina_richiesta.equals("servlet"))
           response.sendRedirect("AliasServletDiSaluto"); // Redirect a percorso relativo (se manca lo / iniziale viene ugualmente interpretata come richiesta relativa)
        
      // Se non avviene il redirect la logica dell'applicazione prosegue
      response.setContentType("text/html");
      
      PrintWriter out = response.getWriter();
      out.println("<html><head><title>Nessun redirect rilevato.....</title></head><body>"
              + "<h2 align='center'>Nessun redirect rilevato....</h2>"
              + "</body></html>");
      
      out.close();
    }
    
    
    
}
