/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package debugLogging;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rocco
 */
public class servletDebug extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Server Message Log</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("In questo ambiente, Netbeans + GlassFish server, per effettuare il Debug è importante sapere come inserire i Log point e sapere dove leggerli, un pò come avviene nello sviluppo di applicazioni Android.");
            out.println("<br/>Leggere i Log di GlasshFish è possibile da <a href='http://127.0.0.1:4848/'>http://127.0.0.1:4848/</a> > Dati di monitoraggio > server > Visualizza file di log.");
            // Il debugging avviene ad esempio stampando messaggi di Log al server. Ci son 3 tipi di messaggi che si possono inviare:
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.severe("MESSAGGIO DI TIPO severe");
            logger.info("MESSAGGIO DI TIPO info");
            logger.fine("MESSAGGIO DI TIPO fine"); // Questo non viene mostrato in console...
            
            // E' possibile leggerli da http://127.0.0.1:4848/ > Dati di monitoraggio > server > Visualizza file di log.
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Servlet che spiega il Logger usato da GlassFish...";
    }// </editor-fold>

}
