<%-- 

TAG Personalizzati con JSP.
- Questa pagina visualizza un messaggio di benvenuto.

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TAG Personalizzati</title>
    </head>
    <body>
        
        <%@taglib uri="/WEB-INF/tlds/mialib.tld" prefix="util" %> <!-- <-- il prefisso è arbitrario, potremmo mettere anche altro nome. -->
        
        <!-- Quando il container incontra un tag personalizzato, crea l'oggetto tag handler e invoca i metodi dell'interfaccia Tag necessari ad attivare la logica del tag. -->
        
        Questo messagio: <b><util:welcome/></b>. <br/>
        E' stato prodotto da un TAG personalizzato.
    </body>
</html>
