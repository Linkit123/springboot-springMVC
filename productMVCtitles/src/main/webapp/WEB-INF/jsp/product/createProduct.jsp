<%-- 
    Document   : createProduct
    Created on : Feb 28, 2020, 9:13:50 AM
    Author     : linhtn
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
            <title>JSP Page</title>
        </head>
        <body>
            <h3>Welcome, Enter The Product Details</h3>
            
            <form:form method="post" action="create_product">    

            <table>
                <tr>    
                    <td>name</td>    
                    <td><form:input path="name" /></td>     
                </tr>    
                <tr>    
                    <td>price</td>    
                    <td><form:input path="price" type="number"/></td>    
                </tr>    
                <tr>    
                    <td>status</td>    
                    <td><form:input path="status" /></td>    
                </tr>    
                <tr>    
                    <td>description</td>    
                    <td><form:input path="description" /></td>    
                </tr> 
                <tr>    
                    <td>created By</td>    
                    <td><form:input path="createdBy" /></td>    
                </tr> 
                <tr>    
                    <td colspan="2">    
                        <input type="submit" value="Add Product"/>    
                    </td>    
                </tr>    
            </table>      

        </form:form>
        </body>
    </html>
</f:view>
