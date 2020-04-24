<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
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
        <div class="retrieve" id = "retrieve">
             <p style="color:white;text-align:center;font-size:20px;">SHOW CLIENT INFORMATION</p>
             <form:form action="retrieve" method="get" modelAttribute="client">
                 <form:input path="id" maxlength="5"/>
                 <input type="submit" style="text-align:center; cursor:pointer;">
             </form:form>
            <div class="retrieveAll">

            <form:form action="retrieveAll" method="get" modelAttribute="client">
                <input type="submit" style="text-align:center; cursor:pointer;">
            </form:form>

            </div>
        </div>
    </div>
</div>

</body>
</html>