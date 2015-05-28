/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servletPOST;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletPOST extends HttpServlet{

// processiamo le richieste POST dal client, con il metodo doPost()
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Otteniamo i dati in POST che andremo ad elaborare e stampare nell'OUT:
        int x = Integer.parseInt(request.getParameter("x")); // in generale getParameter restituisce una String. Il cast è necessario.
        int y = Integer.parseInt(request.getParameter("y"));
        
        int totale = x * y;
        
        
        // Inviamo la pagina HTML di risposta al client
        
        out.println("<html><head><title>Risposta output HTML</title></head>");
        out.println("<body>");
        out.println("<table border='1'><thead><tr><th>Fattore 1</th><th>Fattore 2</th><th>Totale</th></tr></thead><tbody>"
                + "<tr><td>"+x+"</td><td>"+y+"</td><td>"+totale+"</td></tr>"
                + "</tbody><caption><i>Risultato moltiplicazione</i></caption></table");
        out.println("</body></html>");
        
    }

}