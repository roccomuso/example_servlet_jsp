<%-- 
    Tag che includono il corpo...  ex. <j:tag>corpo</j:tag>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Tag</title>
    </head>
    <body>
        
<%@ taglib uri="/WEB-INF/tlds/mialib.tld" prefix="csajsp" %>
<csajsp:heading bgColor="#C0C0C0">
Default Heading
</csajsp:heading>
<P>
<csajsp:heading bgColor="BLACK" color="WHITE">
White on Black Heading
</csajsp:heading>
<P>
<csajsp:heading bgColor="#EF8429" fontSize="60" border="5">
Large Bordered Heading
</csajsp:heading>
<P>
<csajsp:heading bgColor="CYAN" width="100%">
Heading with Full-Width Background
</csajsp:heading>
<P>
<csajsp:heading bgColor="CYAN" fontSize="60"
fontList="Brush Script MT, Times, serif">
Heading with Non-Standard Font
</csajsp:heading>
<P>
        
    </body>
</html>
