
<%@ page import="sp_portal.local.StandardizedPatient" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="userMain">
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>	
		
		<g:form action="sendEmail" >
				<fieldset class="form">
				<div>
					<h2><g:message code="user.label.emailSubject" /></h2>
					<g:textField class="editEmailSubject" name="editedEmailSubject" value="${defaultSubject}" />
				</div><br/></br>
				<div>
				
					<h2> <g:message code="user.label.emailBody" /></h2>
					<g:textArea class="editEmailBody" name="editedEmailText" value="${defaultEmail}" rows="15" cols="90"/>
				</div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit action="cancel" value="${message(code: 'user.button.cancel')}" />
                    <g:submitButton name="sendEmail" value="${message(code: 'user.button.send')}" />
				</fieldset>
		</g:form>       
    </body>
</html>