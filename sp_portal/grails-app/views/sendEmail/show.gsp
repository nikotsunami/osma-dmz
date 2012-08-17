
<%@ page import="sp_portal.local.StandardizedPatient" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="userMain">
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
	<body style="height:90%">
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>	
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		<g:form action="showPreviewEmail" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit action="selectAll" value="${message(code: 'user.button.selectAll')}" />
					<g:actionSubmit action="clearAll" value="${message(code: 'user.button.clearAll')}" />
                    <g:submitButton name="showPreviewEmail" value="${message(code: 'user.button.sendEmail')}" />
					<g:link controller="sendEmail" action="showSentEmail" ><g:message code="user.button.SentEmail"/></g:link>
				</fieldset>
		</g:form>
    </body>
</html>
