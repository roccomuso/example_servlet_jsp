

package tagHandler.protectedPage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import tagHandler.protectedPage.LoginDB;
import tagHandler.protectedPage.User;

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
	    getServletContext().getRequestDispatcher(res.encodeURL(protectedPage)).forward(req,res);
	    // req.getRequestDispatcher(res.encodeURL(protectedPage)).forward(req,res);
	}
	//l’utente con i dati digitati nel form non è stato trovato nella base dati
	else {//not authorized
	    String loginPage = (String) session.getAttribute("login-page");
	    String errorPage = (String) session.getAttribute("error-page");
	    String forwardTo = errorPage!=null?errorPage:loginPage;
	    session.setAttribute("login-error", "Username and pass are not valid");
	    //la richiesta viene rediretta alla pagina di errore se è stata
	    //configurata, altrimenti alla pagina di login
	    // req.getRequestDispatcher(
	    // 					     res.encodeURL(forwardTo)).forward(req,res);
	    getServletContext().getRequestDispatcher(
	    					     res.encodeURL(forwardTo)).forward(req,res);
	}
    }
}
