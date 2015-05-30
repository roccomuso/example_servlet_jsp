<%-- 
    Esempio che mostra se valutare il contenuto dopo il tag, oppure ignorarlo.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%@taglib uri="/WEB-INF/tlds/mialib.tld" prefix="c" %>
        
        <h4>Esempio di valutazione del contenuto dopo il tag di chiusura</h4>
        <p>Provare a giocare con gli attributi del tag per mostrare o meno body del tag, e i contenuti della pagina DOPO il tag di chiusura:</p>
        
        <c:return valutaPagina="true" valutaBodyTag="true"> <!-- il doEndTag() restituisce dunque EVAL_PAGE o SKIP_PAGE e il doStartTag() restituisce EVAL_BODY_INCLUDE o SKIP_BODY -->
            - BODY del TAG presente
        </c:return>
        
        <br/><br/>- Questo viene valutato in base al valore dell'attributo 'valutaPagina' del tag return. <!-- Non solo questa frase, ma tutto il codice HTML che segue il tag personalizzato. -->
        
    </body>
</html>
