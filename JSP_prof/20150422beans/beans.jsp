<%--Cosi' non va bene: all'attributo class e' possibile passare solamente stringhe costanti --%>
<jsp:useBean id="a_bean" class='<%=request.getParameter("class")%>'/>

