<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
<style>
            <%@ include file="../../../css/style.css"%>
</style>
</head>
<body>
    <div class="body">
     <%@ include file="header.jsp"%>
        <div class="middle">
            <div class="insert" id ="insert" style="display:block">
                <p style="color:white;text-align:center;font-size:20px;">CREATE NEW EMPLOYEE</p>
                <form:form action="saveEmployee" method="get" modelAttribute="employee">
                    <form:input path="id" maxlength="5"/>
                    <form:input path="clientId" />
                    <form:input path="name"/>
                    <form:input path="department" />
                    <form:input path="email"/>
                    <form:input path="dateOfBirth"/>
                    <input type="submit" style="text-align:center; cursor:pointer;">
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>