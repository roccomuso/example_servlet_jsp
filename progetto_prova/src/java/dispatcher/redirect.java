/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rocco
 */
public class redirect extends HttpServlet {


    @Override
    public void init(){
        if (getServletContext().getAttribute("counter") == null)
            getServletContext().setAttribute("counter", 1);
        else{
             getServletContext().setAttribute("counter", (int)getServletContext().getAttribute("counter")*5);   
                }
            
         
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            /* esempi di comandi dopo l'invio della risposta o prima... */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet redirect</title>");            
            out.println("</head>");
            out.println("<body>Ciao.");

            out.print((int) getServletContext().getAttribute("counter"));
            
            out.println("</body>");
            out.println("</html>");
            
            
            out.close();
            
            // NON funzionano perchè chiamato dopo che l'oggetto risposta viene inviato! vvanno tutte chiamate prima di out.close();
            
            
//            RequestDispatcher dp = getServletContext().getRequestDispatcher("/switch_case.jsp"); // percorso relativo riferito non dalla radice del server ma dalla context root. (cioè la cartella della web-app corrente).
//            dp.forward(request, response);
            
//            response.addCookie(new Cookie("prova", "ciao")); // prima di out.close(); viene immesso, dopo no.
            
//            response.sendRedirect("switch_case.jsp");
            
//out.close();
            
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void destroy(){
        int count = (int) getServletContext().getAttribute("counter");
        count++;
        getServletContext().setAttribute("counter", count);

    }
    
}
