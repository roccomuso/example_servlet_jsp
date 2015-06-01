<!--

Ci sono 3 tipi di autenticazione DICHIARATIVA:
- BASIC (viene mostrata la finestra di dialogo che chiede le credenziali)
- basata su Form  (Il metodo basato su form funziona come il metodo BASIC ma viene visualizzata la pagina di login anzichè una finestra di dialogo. (password trasmessa con codifica base64)
- DIGEST

La scelta viene specificata nel web.xml con i tag login-config e auth-method:
    <login-config>
        <auth-method>BASIC</auth-method>
        <realm-name>Esempio di autenticazione Basic</realm-name>
    </login-config>

-->

<html><head><title>Pagina di login</title></head>
    <body>
        <font size="4" color="blue">
        Per favore inserisci i tuoi dati:<br></font>
        <!-- j_security_check fa in modo che il login venga gestito dal web server, (autenticazione DICHIARATIVA). -->
        <form action="/j_security_check" method="POST">
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
        <b>Con l'autenticazione BASIC, un form è praticamente inutile. Perchè tanto viene mostrato il Dialog Box che richiede le credenziali.</b><br/><br/>
        
        Credenziali per GlassFish definite da:<br/>
        127.0.0.1:4848 > Configurazioni >  server-config > Sicurezza > Realm > File > Gestisci Utenti.
        
        Associazioni di principal (utenti) a ruoli, e di ruoli a gruppi in GlassFish nel file: glassfish-web.xml.<br/>
        Però ugualmente bisogna definire i nomi dei ruoli disponibili in web.xml con: <code>&lt;security-role&gt;
    &lt;role-name&gt;USERS&lt;/role-name&gt;
            &lt;/security-role&gt;</code>
        
        <br/><br/>
        
        Per Tomcat la creazione di un utente e l'assegnazione del ruolo all'utente avviene dal file: tomcat-users.xml.<br/>
        
        <br/><br/>
        
        Se si prova ad effettuare l'accesso con un utente appartenente ad un altro gruppo, si ottiene una pagina d'errore 403 - Accesso Negato.
    </body>
</html>

