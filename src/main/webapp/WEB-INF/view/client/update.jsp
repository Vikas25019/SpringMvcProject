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
           <div class="update" id ="update" style="display:block">
             <form action='updateClient' method='get'>
               <p style="color:white;text-align:center;font-size:20px;">UPDATE CLIENT INFORMATION</p>
               <input type="text" name="name" placeholder="Enter client name"><br>
               <input type="text" name="address" placeholder="Enter client address"><br>
               <input type="submit" style="text-align:center;cursor:pointer;">
              </form>
           </div>

        </div>
     </div>

</body>
</html>