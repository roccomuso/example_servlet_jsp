

package tagHandler.autenticazione;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tagHandler.autenticazione.LoginDB;
import tagHandler.autenticazione.User;

public class AuthenticateServlet extends HttpServlet {
    private LoginDB loginDB;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	loginDB=new LoginDB();
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
	HttpSession session=req.getSession();
	String uname = req.getParameter("userName");
	String pwd = req.getParameter("password");
	User user = loginDB.getUser(uname,pwd);
	//ricerca nella base dati il bean utente con nome e password del form
	if (user != null) { //authorized
	    String protectedPage = (String)session.getAttribute("protected-page");
	    session.removeAttribute("login-page");
	    session.removeAttribute("error-page");
	    session.removeAttribute("protected-page");
	    session.removeAttribute("login-error");
	    //inserisce il bean utente nella sessione
	    session.setAttribute("user",user);
	    // res.sendRedirect(res.encodeURL(protectedPage));
            
            // ci facciamo stampare sui log, protectedPage:
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info("VALORE protectedPage: "+protectedPage);
	    
            getServletContext().getRequestDispatcher(res.encodeURL(protectedPage)).forward(req,res); // getRequestDispatcher dal contesto richiede un URL che inizi per /
	    // req.getRequestDispatcher(res.encodeURL(protectedPage)).forward(req,res);
	}
	//l’utente con i dati digitati nel form non è stato trovato nella base dati
	else {//not authorized
	    String loginPage = (String) session.getAttribute("login-page");
	    String errorPage = (String) session.getAttribute("error-page");
	    String forwardTo = errorPage!=null?errorPage:loginPage;
	    session.setAttribute("login-error", "Username '"+uname+"' and pass are not valid");
	    //la richiesta viene rediretta alla pagina di errore se è stata
	    //configurata, altrimenti alla pagina di login
	    // req.getRequestDispatcher(
	    // 					     res.encodeURL(forwardTo)).forward(req,res); // il Dispatcher da request non ha bisogno che il path inizi per /
	    getServletContext().getRequestDispatcher(
	    					     res.encodeURL(forwardTo)).forward(req,res);
	}
    }
}
