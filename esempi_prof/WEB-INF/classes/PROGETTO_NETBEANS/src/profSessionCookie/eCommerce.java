import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
 
public class eCommerce extends HttpServlet {

    private String err = null;
    private final Map<String,String> sezioni = new HashMap<String,String>();
    private String sezioniStr = "";
    private String[] fileContent;
    private String dbg = "";
    
    public void init () {
	BufferedReader br = null;
 
	try {
 
	    String sCurrentLine, elementi = "", sezione = "";
 
	    br = new BufferedReader(new FileReader("/var/lib/tomcat7/webapps/20150316/other/ecommerce.txt"));
 
	    while ((sCurrentLine = br.readLine()) != null) {
		if (sCurrentLine.equals(""))
		    continue;
		if (sCurrentLine.charAt(0) == '_') {
		    if (elementi != "") {
			sezioni.put(sezione, elementi);
			dbg += "Sezione : " + sezione + " con elementi: " + elementi;
		    }
		    sezione = sCurrentLine.substring(1);
		    sezioniStr += sezione + " ";
		    elementi = "";
		}
		else
		    elementi += sCurrentLine + "_";
	    }
	    if (elementi != "")
		sezioni.put(sezione, elementi);
 
	} catch (IOException e) {
	    err = "<b>INTERNAL ERROR: Unable to either open or read the file because of " + e.toString() + "</b>";
	} finally {
	    try {
		if (br != null)
		    br.close();
	    } catch (IOException ex) {
		err = "<b>INTERNAL ERROR: Unable to either open or read the file because of " + ex.toString() + "</b>";
	    }
	} 
    }

    private void aggiungiCarrello(HttpServletRequest request, String sezione) {
	HttpSession session = request.getSession(true);
	ArrayList<String> carrello = (ArrayList<String>)session.getAttribute(sezione);
	if (carrello == null)
	    carrello = new ArrayList<String>();
	// if (session.isNew())
	//     carrello = new ArrayList<String>();
	// else
	//     carrello = (ArrayList<String>)session.getAttribute(sezione);
	String[] compra;
	if ((compra = request.getParameterValues("compra")) != null) {
	    for (int i = 0; i < compra.length; i++)
		if (!(carrello.contains(compra[i])))
		    carrello.add(compra[i]);
	}
	if (carrello.size() > 0)
	    session.setAttribute(sezione, carrello);
    }
    
    private void stampaCarrello(HttpServletRequest request, PrintWriter out) {
	HttpSession session = request.getSession(true);
	String[] sezioni_ar = sezioniStr.split(" ");
	for (int i = 0; i < sezioni_ar.length; i++) {
	    ArrayList<String> carrello = ((ArrayList<String>)session.getAttribute(sezioni_ar[i]));
	    if (carrello == null)
		continue;
	    out.println("<p>I seguenti elementi della sezione " + sezioni_ar[i] + " sono gi&agrave; nel carrello:</p>\n<ul>");
	    for (int j = 0; j < carrello.size(); j++)
		out.println("<li>" + carrello.get(j) + "</li>\n");
	    out.println("</ul>");
	}
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
    {
	if (err != null) {
	    response.setContentType( "text/html" );
	    PrintWriter out = response.getWriter();
	    out.println(err);
	    out.close();
	}
	else {
	    String sezione, compraStr = "";
	    if ((sezione = request.getParameter("sezione")) == null) {
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		stampaCarrello(request, out);
		out.println("<b>Scegli la sezione dove comprare:</b><br/><ul>\n");
		String[] sezioni_ar = sezioniStr.split(" ");
		for (int i = 0; i < sezioni_ar.length; i++) 
		    out.println("<li><a href=\"" + request.getRequestURI() + "?sezione=" + sezioni_ar[i] + "\">" + sezioni_ar[i] + "</a></li>\n");
		out.println("</ul>");
		out.close();
	    }
	    else {
		aggiungiCarrello(request, sezione);
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		stampaCarrello(request, out);
		String[] elementi = sezioni.get(sezione).split("_");
		out.println("<b>Scegli che cosa comprare nella sezione " + sezione + ":</b><br/><form method=\"post\" action=\"" + request.getRequestURI() + "?" + request.getQueryString() + "\">\n");
		for (int i = 0; i < elementi.length; i++) {
		    out.println("<input name=\"compra\" type=\"checkbox\" value=\"" + elementi[i] + "\"/><label>" + elementi[i] + "</label><br/>\n");
		}
		out.println("<input type=\"submit\" value=\"Metti nel carrello\"/>\n</form>");
		out.println("<a href=\"" + request.getRequestURI() + "\">Scegli un'altra sezione</a>");
		out.close();
	    }

	}
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
    {
	doPost(request, response);
    }
}
