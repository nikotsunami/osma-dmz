
<%@ page import="sp_portal.local.AnamnesisForm" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'anamnesisForm.label', default: 'AnamnesisForm')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-anamnesisForm" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-anamnesisForm" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list anamnesisForm">
			
				<g:if test="${anamnesisFormInstance?.createDate}">
				<li class="fieldcontain">
					<span id="createDate-label" class="property-label"><g:message code="anamnesisForm.createDate.label" default="Create Date" /></span>
					
						<span class="property-value" aria-labelledby="createDate-label"><g:formatDate date="${anamnesisFormInstance?.createDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${anamnesisFormInstance?.origId}">
				<li class="fieldcontain">
					<span id="origId-label" class="property-label"><g:message code="anamnesisForm.origId.label" default="Orig Id" /></span>
					
						<span class="property-value" aria-labelledby="origId-label"><g:fieldValue bean="${anamnesisFormInstance}" field="origId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${anamnesisFormInstance?.standardizedPatients}">
				<li class="fieldcontain">
					<span id="standardizedPatients-label" class="property-label"><g:message code="anamnesisForm.standardizedPatients.label" default="Standardized Patients" /></span>
					
						<g:each in="${anamnesisFormInstance.standardizedPatients}" var="s">
						<span class="property-value" aria-labelledby="standardizedPatients-label"><g:link controller="standardizedPatient" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${anamnesisFormInstance?.id}" />
					<g:link class="edit" action="edit" id="${anamnesisFormInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
