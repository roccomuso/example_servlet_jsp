<%-- 
    
--%>


        <%
        
          
            if (session.getAttribute("username") != null){
                
                RequestDispatcher dp = getServletContext().getRequestDispatcher("home.jsp");
                dp.include(request, response);
            
            }else{
                
            }
          
        %>
 