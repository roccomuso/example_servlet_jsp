/*
 * Servlet per l'aggiunta al carrello
 */

package utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap.SimpleEntry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import strutture_dati.Carrello;

/**
 *
 * @author Rocco
 */
public class operazioniSulCarrello extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            if (session.getAttribute("username") != null){ // sessione valida.
                if (request.getParameter("action") != null)
                    switch(request.getParameter("action")){

                        case "add":
                            // aggiungiamo il prodotto al carrello:

                            int id_prodotto = Integer.parseInt(request.getParameter("prod"));
                            if (id_prodotto != 0) {
                                Carrello cart = (Carrello) session.getAttribute("carrello");
                                if (cart == null) // se non esiste il carrello lo creiamo e aggiungiamo subito il prodotto acquistato.
                                {
                                    cart = new Carrello(new SimpleEntry<Integer, Integer>(id_prodotto, 1)); // per scontato diamo come quantità sempre 1
                                } else {
                                    cart.aggiungi(id_prodotto, 1);
                                }
                                session.setAttribute("carrello", cart);
                            }
                            break;
                        case "remove":
                            int id_prodott = Integer.parseInt(request.getParameter("prod"));
                            if (id_prodott != 0) {
                                Carrello cart = (Carrello) session.getAttribute("carrello");
                                if (cart != null){
                                    cart.rimuovi(id_prodott, 1); // di default rimuoviamo 1 solo oggetto per volta.
                                }
                                session.setAttribute("carrello", cart);
                            }
                            break;
                }
                // redirect
                response.sendRedirect("home.jsp");
            }else{
                // non si è loggati.
                out.print("<h2>Verificare che la sessione sia valida! <a href='index.jsp'>Vai al Login</a>.</h2>");
                
            }
                
        }
    }

    
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

}
