<!--

Ci sono 3 tipi di autenticazione DICHIARATIVA:
- BASIC (viene mostrata la finestra di dialogo che chiede le credenziali)
- basata su Form  (Il metodo basato su form funziona come il metodo BASIC ma viene visualizzata la pagina di login anzich� una finestra di dialogo. (password trasmessa con codifica base64)
- DIGEST

La scelta viene specificata nel web.xml con i tag login-config e auth-method. E' possibile usare un solo metodo d'autenticazione per ogni web.xml e quindi per ogni web-app:
    <login-config>
        <auth-method>FORM</auth-method>
        <realm-name>Esempio di autenticazione Form</realm-name>
    </login-config>

-->

<html><head><title>Pagina di login</title></head>
    <body>
        <font size="4" color="blue">
        Per favore inserisci i tuoi dati:<br></font>
        <form action="/j_security_check" method="POST"> <!-- j_security_check fa in modo che il login venga gestito dal web server, (autenticazione DICHIARATIVA). -->
            <table>
                <tr><td>Name:</td>
                    <td><input type="text" name="j_username"> (rocco)</td></tr>
                <tr><td>Password:</td>
                    <td><input type="password" name="j_password" size="8"> (123456)</td>
                </tr>
            </table>
            <br>
            <input type="submit" value="login">
        </form>
        <br/><br/>
        
        Credenziali per GlassFish definite da:<br/>
        127.0.0.1:4848 > Configurazioni >  server-config > Sicurezza > Realm > File > Gestisci Utenti.
        
    </body>
</html>
