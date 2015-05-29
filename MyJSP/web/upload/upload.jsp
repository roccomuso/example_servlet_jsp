<%-- 

La classe sotto è importata da commons-fileupload-1.0.jar presente in Libraries.

I File caricati si troveranno sempre nella cartella definita, che si trova sotto /build/web/upload/...

--%>

<%@page import="varie.UploadUtility"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@page import="org.apache.commons.fileupload.FileUpload"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uploading...</title>
    </head>
    <body>
        <%!
           
           public ArrayList<String> allowed_extension = new ArrayList<String>(); // estensioni permesse.
           
           public void init(){
               allowed_extension.add("zip");
               allowed_extension.add("rar");
               allowed_extension.add("doc");
               allowed_extension.add("html");
           }
        %>
        <%
        
            // Controlliamo che ci sia una richiesta d'upload:
            boolean isMultipart = FileUpload.isMultipartContent(request);
            
            // Creiamo un handler per la richiesta di upload:
            
            DiskFileUpload upload = new DiskFileUpload();
            
            //upload.setSizeMax(-1); // a -1 vuol dire qualsiasi dimensione.
            
            //upload.setSizeThreshold(5000); // oltre i 5mb il file viene salvato in una dir temporanea:
            //upload.setRepositoryPath(getServletContext().getRealPath("/")+"dir_temporanea");
                      
            
            List items = upload.parseRequest(request); // tutti i campi passati tramite il form. OCCHIO: una volta parsato 'request' questo non sarà più utilizzabile. Inoltre trasferendo in POST non dobbiamo inserire nel form HTML l'attributo enctype, altrimenti non funziona!
            
            Iterator itr = items.iterator();
            while(itr.hasNext()){
                FileItem item = (FileItem) itr.next();
                // controlliamo se si tratta di un campo del form oppure di un file caricato
                if (item.isFormField()){
                    //prendiamo il nome del campo
                    String parameterName = item.getFieldName();
                    // ex. se è il campo è 'nome' lo si può usare per un messagio di benvenuto: Ciao item.getString()
                    // . . .
                    
                }else{ // Siamo nel caso in cui si tratta proprio di un file da caricare sul server
                    File fullFile = new File(item.getName());
                                  
                    // Preleviamo i parametri che ci interessano da 'items', perchè request viene parsato e non è più utilizzabile (il form html inoltre è POST con attributo enctype definito, e non funziona il POST se si definisce un enctype):
                    String nome_file_remoto = UploadUtility.seek(items, "nome_file"); // altro modo di prendere i parametri passati col form tramite gli items di questa libreria file-upload
                    nome_file_remoto = (nome_file_remoto != null) ? nome_file_remoto: fullFile.getName(); // altrimenti lasciamo il nome originario.
                    
                    // CONTROLLO ESTENSIONE
                    String extension = FilenameUtils.getExtension(fullFile.getName()); // FilenameUtils richiede la libreria commons-io-2.4.jar
                    
                    File savedFile = new File(getServletContext().getRealPath("/"),"upload\\file_caricati\\"+nome_file_remoto+"."+extension);
                    // getServletContext().getRealPath("/") restituisce l'intero percorso dove questo script si trova (senza i package): C:\xampp\tomcat\webapps\MyJSP\build\web\ 
                    // fullFile.getName() restituisce il nome del file che si sta caricando.
                    
         
                    if (allowed_extension.contains(extension)){
                         item.write(savedFile); // scriviamo su DISCO il file.
                         out.print("<font color='green' size='15'>File caricato con successo!</font><br/><br/>");
                    }else{
                        out.print("<font color='red' size='10'>File non caricato!</font><br/><br/>");
                        out.print("<b>Estensione errata, le estensioni ammesse sono</b>: <br/>");
                        out.print(allowed_extension.toString()+"<br/><br/>");
                    }
                    
                    // IL FILE VIENE CARICATO NELLA CARTELLA SPECIFICATA IL CUI DIRECTORY TREE FA CAPO A 'build/web' e non 'web'. 
                    out.print("Il file da caricare era: "+item.getName()+"<br/>");
                    out.print("Il nome senza percorso è: "+fullFile.getName()+"<br/>");
                    out.print("Percorso di upload: "+getServletContext().getRealPath("/")+"upload\\file_caricati\\");
                    
                    
                }
            }
            
            // quando si crea una directory, per esempio "file_caricati", assicurarsi di fare sul progetto un Clean and Build, in modo che verrà creata anche nella cartella 'build' dove viene effettivamente eseguito poi.
            
        %>
        
        
    </body>
</html>
