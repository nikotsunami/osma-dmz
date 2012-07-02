<%@ page import="sp_portal.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordHash', 'error')} ">
    <label for="passwordHash">
        <g:message code="user.passwordHash.label" default="Password" />

    </label>
    <g:passwordField name="passwordHash" value="${userInstance?.passwordHash}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordHash', 'error')} ">
    <label for="passwordHash">
        <g:message code="user.confirmPasswordHash.label" default="Confirm Password" />

    </label>
    <g:passwordField name="confirmPassword" value="${userInstance?.passwordHash}"/>
</div>

