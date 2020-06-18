<%-- 
    Document   : contact
    Created on : Feb 28, 2020, 11:50:46 AM
    Author     : linhtn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form:form method="post" action="create_product.html">    

            <table>    
                <tr>    
                    <td><form:label path="firstname">First Name</form:label></td>    
                    <td><form:input path="firstname" /></td>     
                </tr>    
                <tr>    
                    <td><form:label path="lastname">Last Name</form:label></td>    
                    <td><form:input path="lastname" /></td>    
                </tr>    
                <tr>    
                    <td><form:label path="lastname">Email</form:label></td>    
                    <td><form:input path="email" /></td>    
                </tr>    
                <tr>    
                    <td><form:label path="lastname">Telephone</form:label></td>    
                    <td><form:input path="telephone" /></td>    
                </tr>    
                <tr>    
                    <td colspan="2">    
                        <input type="submit" value="Add Contact"/>    
                    </td>    
                </tr>    
            </table>       
        </form:form>    
    </body>
</html>
