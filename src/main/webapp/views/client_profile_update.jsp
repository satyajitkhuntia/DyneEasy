<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="header.jsp" %>

<div class="container mt-4">
    <h2>Update Client Profile</h2>
    <form:form action="/client_profile_update" method="post" modelAttribute="clientProfileUpdateForm">
        <div class="form-group">
            <form:label path="name">Name:</form:label>
            <form:input path="name" class="form-control" value="${clientProfileUpdateForm.name}"/>
        </div>
        <div class="form-group">
            <form:label path="password">Password:</form:label>
            <form:password path="password" class="form-control" value="${clientProfileUpdateForm.password}"/>
        </div>
        <div class="form-group">
            <form:label path="mobile">Mobile Number:</form:label>
            <form:input path="mobile" class="form-control" value="${clientProfileUpdateForm.mobile}"/>
        </div>
        <div class="form-group">
            <form:label path="email">Email:</form:label>
            <form:input path="email" class="form-control" value="${clientProfileUpdateForm.email}"/>
        </div>
        <button type="submit" class="btn btn-primary">Update Profile</button>
    </form:form>
</div>

<%@ include file="footer.jsp" %>
