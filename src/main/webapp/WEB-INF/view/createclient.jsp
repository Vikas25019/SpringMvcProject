<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>

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
                <div class="insert" id ="insert" style="display:block">
                    <p style="color:white;text-align:center;font-size:20px;">CREATE NEW CLIENT</p>
                    <form:form action="saveClient" method="get" modelAttribute="client">

                        <form:input path="id" maxlength="5"/>

                        <from:errors path="id" />

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