<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">

</head>
<body>
<div class="body">
    <div class="background">
    </div>
    <div class="header">
        <div class = "logo"><p>CLIENT MANAGEMENT SYSTEM</p></div>
        <div class="navigation">
            <div class="nav3">
                <a href="../../index.html">Home</a>
            </div>
            <div class="nav1">
                <a href="createclient.html">Create Client</a>
            </div>
            <div class="nav2">
                <a href="showclient.html">Show Client</a>
            </div>
        </div>
    </div>
    <div class="middle">
        <div class="retrieve" id = "retrieve">
             <p style="color:white;text-align:center;font-size:20px;">SHOW CLIENT INFORMATION</p>
             <form:form action="retrieve" method="get" modelAttribute="client">
                 <form:input path="id" maxlength="5"/>
                 <from:errors path="id" />
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