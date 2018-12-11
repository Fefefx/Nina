<%-- 
    Document   : redirect
    Created on : 07/11/2018, 08:19:52
    Author     : felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saindo...</title>
    </head>
    <body>
        <%
            String user = (String) session.getAttribute("user");
            if (user != null) {
                session.invalidate();
                response.sendRedirect("../../../Login_v1/index.jsp");
            }
        %>  
    </body>
</html>
