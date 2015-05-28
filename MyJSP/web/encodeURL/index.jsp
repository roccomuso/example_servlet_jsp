<%-- 

Senza cookie e dunque sessioni, bisogna usare encodeURL, e fare in modo che ogni URL nella pagina, ed ogni redirect, siano codificate
con questo metodo!

--%>

<%@page import="java.io.IOException"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="varie.Carrello"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Esempio encodeURL</title>
    </head>
    <body>
        <%!
        
        public static Map<String, String> catalogo = new HashMap();
        
        public void init(){
            
            catalogo.put("item1", "DELL XPS 13 pollici");
            catalogo.put("item2", "Moto 360");
            catalogo.put("item3", "Raspberry Pi");
            
            
        }
        
        public static void stampaCatalogo(JspWriter out, HttpServletResponse response) throws IOException{
            
            out.print("<h2>Ecco il Catalogo:</h2><ul>");
            
            for (Entry e: catalogo.entrySet())
                out.print("<li><a href='"+response.encodeURL("aggiungi_carrello.jsp?item="+e.getKey())+"'>"+e.getKey()+"</a>:"+e.getValue()+"</li>");
             
            out.print("</ul><br/><hr/>");
        }
        
        public static void stampaCarrello(JspWriter out, HttpSession session) throws IOException{
            
            Carrello cart = (Carrello) session.getAttribute("carrello");
            
            out.print("<h2>Il tuo Carrello:</h2>");
            
            if (cart != null)
                out.print(cart.stampa_per_il_web());
            
        }
        
        %>
        <%
        
        if (session.getAttribute("loggato") != null){
        
            stampaCatalogo(out, response);
            stampaCarrello(out, session);
            
        }else{ // non esiste la sessione.
        
            String action_page = response.encodeURL("login.jsp");
        %>
        
        <fieldset style="width:300px;height:100px">
            <legend>Effettua il Login</legend>

        <form action="<%= action_page %>" method="POST" name="form1">   
            <input type="text" name="username" placeholder="Inserisci username" required /><br/>
            <input type="password" name="password" placeholder="Inserisci password" required /><br/>
            <input type="submit" value="Login" />
        </form>

        </fieldset>
        
        
        <%
        }
        %>
        
    </body>
</html>
