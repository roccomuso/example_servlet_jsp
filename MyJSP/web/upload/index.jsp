<%-- 

    Scriptlet per l'upload di File.

Richiede la presenza della libreria commons-fileupload-1.0.jar:
da inserire nella cartella WEB-INF/lib oppure in shared/lib o common/lib di tomcat, anche se servono i permessi d'amministratore del server per poterci inserire li dei file.

Nel caso del progetto Web su Netbeans, è sufficiente trascinare la libreria in libraries.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload File</title>
    </head>
    <body>
        
        <h1>File Upload</h1>
        
        <!-- Una richiesta HTTP di upload di file, secondo la RFC1867 deve essere inoltrata con il metodo POST e codifica multipart/form-data -->
        <form name="form1" action="upload.jsp" method="POST" enctype="multipart/form-data">
            
            <input type="text" name="nome_file" size="35" placeholder="Inserisci il nome del file, senza estensione"><br/><br/>
            <input type="file" name="file_upload" placeholder="Scegli un file da caricare..."> <!-- E' possibile inserire i tipi di file accettati: accept="text/html" -->
            <br/><br/><br/>
            <input type="submit" value="Invia">
            
        </form>
            
        
        
        <br/>
    </body>
</html>
