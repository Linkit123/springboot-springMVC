<%-- 
    Document   : product
    Created on : Feb 28, 2020, 1:34:03 PM
    Author     : linhtn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/core/css/hello.css"/>
    </head>
    <body>

        <div class="jumbotron">
            <div class="container">

                <div><a href="redirectCreateProduct">ADD product</a></div>
                <h1>${title}</h1>
                <p>
                    <c:if test="${not empty name}">
                        Hello ${name}
                    </c:if>

                    <c:if test="${empty name}">
                        Welcome my empire!
                    </c:if>

                    <c:if test="${not empty status}">
                        ${status}
                    </c:if>

                    <c:if test="${not empty status}">
                        ${status}
                    </c:if>
                    <%
                        Object prdList = request.getAttribute("listProduct");
                    %>
                <div>---------------------------------</div>
                <c:forEach var = "prd" items="${listProduct}" varStatus="loop">
                    <div class="coverSingleItem">
                        <div class="singleItem">
                            <div class="properties">
                                <span>Name: ${prd.name}</span>
                            </div>
                            <div class="properties">
                                <span>Price: ${prd.price}</span>
                            </div>
                            <div class="properties">
                                <span>Status: ${prd.status}</span>
                            </div>
                            <div class="properties">
                                <span>Created Date: ${prd.createdDate}</span>
                            </div>
                            <div class="properties">
                                <span>Created By: ${prd.createdBy}</span>
                            </div>
                            <div class="properties">
                                <span>Description: ${prd.description}</span>
                            </div>
                        </div>
                        <spring:url value="/productMVCtitles/delete_product/?productId=${prd.productId}" var="deleteUrl" /> 
                        <div class="buttonManagerGroup">
                            <div class="managerButton update" onclick="location.href='#'">Update</div>
                            <div class="managerButton delete" onclick="post('${deleteUrl}')">Delete</div>
                            
                        </div>    
                    </div>
                </c:forEach>

                </p>
            </div>
        </div>
    </body>
</html>
