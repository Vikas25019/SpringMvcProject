<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
<style>
            <%@ include file="../../css/style.css"%>
</style>
</head>
<body>

     <div class="body">
     <%@ include file="header.jsp"%>

            <div class="middle">
                <div class="insert" id ="insert" style="display:block">
                    <p style="color:white;text-align:center;font-size:20px;">CREATE NEW CLIENT</p>
                    <div class="errors" style="padding:30px;background:white;border-radius: 25px;">

                    <c:forEach items="${listError}" var="item">
                                <c:out value="Hello text"></c:out>
                                <c:out value="${item}"></c:out>
                    </c:forEach>


                    </div>
                    <form:form action="saveClient" method="get" modelAttribute="client">

                        <form:input path="id" maxlength="5"/>


                        <br>

                        <form:input path="name" maxlength="20"/><from:errors path="name" /><br>

                        <form:input path="address" maxlength="40"/><from:errors path="address" /><br>

                        <input type="submit" style="text-align:center; cursor:pointer;">

                     </form:form>
                </div>
            </div>
        </div>
</body>
</html>