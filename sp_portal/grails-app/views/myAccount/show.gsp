
<%@ page import="sp_portal.User" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="stdpnt">
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title>Account Details</title>
    </head>
    <body>
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div id="show-user" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list user">

                <g:if test="${userInstance?.userName}">
                    <li class="fieldcontain">
                        <span id="userName-label" class="property-label"><g:message code="user.userName.label" default="User Name" /></span>

                            <span class="property-value" aria-labelledby="userName-label"><g:fieldValue bean="${userInstance}" field="userName"/></span>

                    </li>
                </g:if>


                <g:if test="${userInstance?.passwordHash}">
                <li class="fieldcontain">
                    <span id="passwordHash-label" class="property-label"><g:message code="user.passwordHash.label" default="Password Hash" /></span>

                        <span class="property-value" aria-labelledby="passwordHash-label"><g:fieldValue bean="${userInstance}" field="passwordHash"/></span>

                </li>
                </g:if>


                <g:if test="${userInstance?.userEmail}">
                <li class="fieldcontain">
                    <span id="userEmail-label" class="property-label"><g:message code="user.userEmail.label" default="User Email" /></span>

                        <span class="property-value" aria-labelledby="userEmail-label"><g:fieldValue bean="${userInstance}" field="userEmail"/></span>

                </li>
                </g:if>


            </ol>
            <g:form>
                <fieldset class="buttons">
                    <g:hiddenField name="id" value="${userInstance?.id}" />
                    <g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
