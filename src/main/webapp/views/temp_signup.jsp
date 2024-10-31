<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="header.jsp" %>

<div class="container mt-4">
    <h2>Temporary Sign Up</h2>
    <form:form action="/temp_signup" method="post" modelAttribute="tempSignupForm">
        <div class="form-group">
            <form:label path="email">Email:</form:label>
            <form:input path="email" required="required" class="form-control"/>
        </div>
        <div class="form-group">
            <form:label path="mobile">Mobile Number:</form:label>
            <form:input path="mobile" required="required" class="form-control"/>
        </div>
        <button type="submit" class="btn btn-primary">Sign Up</button>
    </form:form>
</div>

<%@ include file="footer.jsp" %>
