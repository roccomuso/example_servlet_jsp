<html>

<head>
<title>Esempio di Servlet</title>
</head>

<body>

<h2>Esempio di pagina in JSP</h2>

<p>In questa directory sono presenti varie pagine HTML, ognuna fa richieste differenti a servlet differenti.</p>

<p>
<ul>

<!-- come fare l'import di classi java in JSP: -->
<!--<%@ page import="java.io.File, java.io.FileFilter, java.io.*" %>-->

<%


File folder = new File("C:\\xampp\\tomcat\\webapps\\esempi_prof\\html_prof\\");
File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
		  %>
			<li>File: <a href="./<%=listOfFiles[i].getName()%>" target="_top"><%=listOfFiles[i].getName()%></a></li>
			<%
        //System.out.println("File " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        %>
			<li>Directory: <a href="./<%=listOfFiles[i].getName()%>" target="_top"><%=listOfFiles[i].getName()%></a></li>
		<%
		//System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
	
	
%>
</ul>
</p>
<!--<%="esempio stringa"%>, con questa sintassi %= possiamo far stampare solo oggetti, stringa, booleani o numerici, non eseguire metodi o istruzioni! -->

<p>L'elenco dei file e delle cartelle qui sopra e' generato dinamicamente in JSP</p>

</body>


</html>