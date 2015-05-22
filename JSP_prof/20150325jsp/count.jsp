<%! private int accessCount = 0; %>
Accesses to page since server reboot (<b>NO! since the JSP has been modified!!!!</b>):
<br/>
<%= ++accessCount %>
      <% synchronized(this){++accessCount;} %>
<%= accessCount %>
<br/><br/>
Ovvero se si modifica la pagina JSP e si salva, il counter ricomincia da 0.
<br/> Il valore di accessCount e' associato proprio al file, indipendentemente dall'utente che visita la pagina! come se fosse un contantore di visite.