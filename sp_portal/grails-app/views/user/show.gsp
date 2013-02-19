
<%@ page import="sp_portal.User" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="userMain">
        <g:set var="entityName" value="${message(code: 'user.label.user', default: 'Benutzer')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <!-- <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li> -->
                <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
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
				
				   <g:if test="${userInstance?.userEmail}">
                <li class="fieldcontain">
                    <span id="userEmail-label" class="property-label"><g:message code="user.userEmail.label" default="User Email" /></span>

                        <span class="property-value" aria-labelledby="userEmail-label"><g:fieldValue bean="${userInstance}" field="userEmail"/></span>

                </li>
                </g:if>
				
				
                <g:if test="${userInstance?.passwordHash}">
                <li class="fieldcontain">
                    <span id="passwordHash-label" class="property-label"><g:message code="user.passwordHash.label" default="Password Hash" /></span>

                        <span class="property-value" aria-labelledby="passwordHash-label"><g:fieldValue bean="${userInstance}" field="passwordHash"/></span>

                </li>
                </g:if>

                <g:if test="${userInstance?.roles}">
                <li class="fieldcontain">
                    <span id="roles-label" class="property-label"><g:message code="user.roles.label" default="Roles" /></span>

                        <g:each in="${userInstance.roles}" var="r">
                        <span class="property-value" aria-labelledby="roles-label">${r?.encodeAsHTML()}</span>
                        </g:each>

                </li>
                </g:if>

				
				

                <g:if test="${userInstance?.isActive}">
                <li class="fieldcontain">
                    <span id="isActive-label" class="property-label"><g:message code="user.isActive.label" default="Is Active" /></span>

                        <span class="property-value" aria-labelledby="isActive-label"><g:formatBoolean boolean="${userInstance?.isActive}" /></span>

                </li>
                </g:if>

             

               

                <g:if test="${userInstance?.standardizedPatient}">
                <li class="fieldcontain">
                    <span id="userName-label" class="property-label"><g:message code="user.origId.labell" default="OrigId" /></span>

                        <span class="property-value" aria-labelledby="userName-label"><g:fieldValue bean="${userInstance.standardizedPatient}" field="origId"/></span>

                </li>
                </g:if>

            </ol>
            <g:form>
                <fieldset class="buttons">
                    <g:hiddenField name="id" value="${userInstance?.id}" />
                    <g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
