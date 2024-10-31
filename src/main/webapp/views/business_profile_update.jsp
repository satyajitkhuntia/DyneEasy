<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Business Profile Update</title>
</head>
<body>
    <h1>Business Profile Update</h1>
    <form:form action="business_profile_update" method="post" modelAttribute="businessProfileUpdateForm">
        <form:label path="brand">Brand Name:</form:label>
        <form:input path="brand"/><br><br>
        <form:label path="password">Password:</form:label>
        <form:password path="password"/><br><br>
        <form:label path="mobile">Mobile Number:</form:label>
        <form:input path="mobile"/><br><br>
        <form:label path="email">Email:</form:label>
        <form:input path="email"/><br><br>
        <form:label path="tagline">Tagline:</form:label>
        <form:input path="tagline"/><br><br>
        <input type="submit" value="Update Profile">
    </form:form>
</body>
</html>