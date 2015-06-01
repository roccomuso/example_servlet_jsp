<!--

Ci sono 3 tipi di autenticazione DICHIARATIVA:
- BASIC (viene mostrata la finestra di dialogo che chiede le credenziali)
- basata su Form  (Il metodo basato su form funziona come il metodo BASIC ma viene visualizzata la pagina di login anzichè una finestra di dialogo. (password trasmessa con codifica base64)
- DIGEST

La scelta viene specificata nel web.xml con i tag login-config e auth-method. E' possibile usare un solo metodo d'autenticazione per ogni web.xml e quindi per ogni web-app:
    <login-config>
        <auth-method>FORM</auth-method>
        <realm-name>Esempio di autenticazione Form</realm-name>
    </login-config>

Il vantaggio della tipologia d'autenticazione FORM è che è possibile definire una pagina di login, un form e una pagina d'errore.
Quando si tenta di accedere ad una risorsa protetta (specificata in web.xml) si viene rimandati automaticamente a questa pagina di login.
-->

<html><head><title>Pagina di login</title></head>
    <body>
        <h2>Login</h2>
        <font size="4" color="blue">
        Per favore inserisci i tuoi dati:<br></font>
        <form action="j_security_check" method="POST"> <!-- j_security_check fa in modo che il login venga gestito dal web server, (autenticazione DICHIARATIVA). -->
            <table>
                <tr><td>Name:</td>
                    <td><input type="text" name="j_username"> (rocco)</td></tr> <!-- necessariamente username dev'essere j_username -->
                <tr><td>Password:</td>
                    <td><input type="password" name="j_password" size="8"> (123456)</td><!-- necessariamente dev'essere j_password con l'autenticazione dichiarativa. -->
                </tr>
            </table>
            <br>
            <input type="submit" value="login">
        </form>
        <br/><br/>
        
        Credenziali per GlassFish definite da:<br/>
        127.0.0.1:4848 > Configurazioni >  server-config > Sicurezza > Realm > File > Gestisci Utenti.
        
        <br/><br/>
        I vantaggi dell'autenticazione dichiarativa FORM-based risiedono proprio nella possibilità di specificare una pagina di login e d'errore.<br/>
        Ogniqualvolta si tenta di accedere ad una risorsa protetta (definita in web.xml) si viene rimandari a questa pagina di Login. Dopo il login si verrà rimandat alla risorsa richiesta.<br/>
        Motivo per cui non bisogna forzare gli utenti su questa pagina di login, perchè non c'è modo di rimandarli ad una pagina desiderata se la pagina richiesta è effettivamente login.jsp.<br/>
        Piuttosto richiedendo direttamente la pagina protetta, verranno rimandati qui e poi rimandati nuovamente alla pagina che avevano richiesto.
        
    </body>
</html>

