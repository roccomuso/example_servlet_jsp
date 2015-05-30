

package tagHandler.autenticazione;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import tagHandler.autenticazione.User;

public class provaUser extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws IOException, ServletException {
	User user = new User("Ciao", "Sono", "Io");
	HttpSession session = req.getSession();
	session.setAttribute("user",user);
	req.getRequestDispatcher(res.encodeURL("strangeBean.jsp")).forward(req,res);
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws IOException, ServletException {
	doPost(req, res);
    }
}
