
<%@ page import="sp_portal.local.StandardizedPatient" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="userMain">
        <g:set var="entityName" value="${message(code: 'user.label.user', default: 'Benutzer')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
	
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>	
		<h1><g:message code="default.showEmailDetail.label" args="[emailInstance.receiver]" /></h1>
		<g:form controller="sendEmail">
			<ol class="property-list user">
				 <li class="fieldcontain">
					 <span class="property-label">E-Mail Subject: </span>
					 &nbsp;
					 <span>${emailInstance.subject}</span>
				 </li>
				 <li class="fieldcontain">
					 <span class="property-label">Sender: </span>
					 &nbsp;
					 <span>${emailInstance.sent}</span>
				 </li>
				  <li class="fieldcontain">
					 <span class="property-label">Addressee: </span>
					 &nbsp;
					 <span>${emailInstance.receiver}</span>
				 </li>
				 <li class="fieldcontain">
					 <span class="property-label">Pick-up time: </span>
					 &nbsp;
					 <span>${emailInstance.sendDate}</span>
				 </li>
				  <li class="fieldcontain">
					 <span class="property-label">Content: </span>
					 <br/>
					 <g:textArea class="editEmailBody" name="emailBody" value="${emailInstance.content}" readonly="readonly" rows="15" cols="90"/>
				 </li>
			</ol>
			<fieldset class="buttons">
					<g:link controller="sendEmail" action="showSentEmail" ><g:message code="user.button.emailList"/></g:link>
			</fieldset>
		</g:form>
  
</html>
