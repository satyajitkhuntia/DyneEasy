<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Client Sign Up</title>
</head>
<body>
    <h1>Client Sign Up</h1>
    <form:form action="client_signup" method="post" modelAttribute="clientSignupForm">
        <form:label path="name">Name:</form:label>
        <form:input path="name" required="required"/><br><br>
        <form:label path="password">Password:</form:label>
        <form:password path="password" required="required"/><br><br>
        <form:label path="mobile">Mobile Number:</form:label>
        <form:input path="mobile" required="required"/><br><br>
        <form:label path="email">Email:</form:label>
        <form:input path="email" required="required"/><br><br>
        <input type="submit" value="Sign Up">
    </form:form>
</body>
</html>