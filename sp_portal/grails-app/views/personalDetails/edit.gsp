<%@ page import="sp_portal.local.StandardizedPatient" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="stdpnt">
        <g:set var="entityName" value="${message(code: 'default.personalDetails.link', default: 'Persoenliche Informationen')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-standardizedPatient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

            <g:form method="post" >
        <div id="edit-standardizedPatient" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${standardizedPatientInstance}">
            <ul class="errors" role="alert">
                <g:eachError bean="${standardizedPatientInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
                <g:hiddenField name="id" value="${standardizedPatientInstance?.id}" />
                <g:hiddenField name="version" value="${standardizedPatientInstance?.version}" />
                <fieldset class="form">
                    <g:render template="form"/>
                </fieldset>
        </div>
                <fieldset class="buttons">
                    <g:actionSubmit class="save" action="update" value="${message(code: 'default.button.save', default: 'Update')}" />
                </fieldset>
            </g:form>
    </body>
</html>
