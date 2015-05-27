<%-- 
    Stampa il contenuto del carrello
--%>

<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="varie.CookieTools"%>
<%

    // lo passiamo come attributo di sessione il carrello.
Map<String, String> COOKIES = CookieTools.convert_Cookie_to_Map(request.getCookies());
    
out.print("<ul>");
for (Entry x : COOKIES.entrySet())
    if (x.getKey().toString().contains("item"))
        out.print("<li>Prodotto: "+x.getKey()+" x "+x.getValue().toString()+"</li>");

out.print("</ul>");
%>