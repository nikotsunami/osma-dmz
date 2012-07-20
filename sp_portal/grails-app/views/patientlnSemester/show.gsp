
<%@ page import="sp_portal.local.PatientlnSemester" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="userMain">
		<g:set var="entityName" value="${message(code: 'patientlnSemester.label', default: 'PatientlnSemester')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-patientlnSemester" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<!-- <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li> -->
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="patientlnSemester.create.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-patientlnSemester" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list patientlnSemester">
			
				<g:if test="${patientlnSemesterInstance?.acceptedOsceDay}">
				<li class="fieldcontain">
					<span id="acceptedOsceDay-label" class="property-label"><g:message code="patientlnSemester.acceptedOsceDay.label" default="Accepted Osce Day" /></span>
					
						<g:each in="${patientlnSemesterInstance.acceptedOsceDay}" var="a">
						<span class="property-value" aria-labelledby="acceptedOsceDay-label">${a?.encodeAsHTML()}</span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${patientlnSemesterInstance?.acceptedTraining}">
				<li class="fieldcontain">
					<span id="acceptedTraining-label" class="property-label"><g:message code="patientlnSemester.acceptedTraining.label" default="Accepted Training" /></span>
					
						<g:each in="${patientlnSemesterInstance.acceptedTraining}" var="a">
						<span class="property-value" aria-labelledby="acceptedTraining-label">${a?.encodeAsHTML()}</span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${patientlnSemesterInstance?.standardizedPatient}">
				<li class="fieldcontain">
					<span id="standardizedPatient-label" class="property-label"><g:message code="patientlnSemester.standardizedPatient.label" default="Standardized Patient" /></span>
					
						<span class="property-value" aria-labelledby="standardizedPatient-label">${patientlnSemesterInstance?.standardizedPatient?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${patientlnSemesterInstance?.id}" />
					<g:link class="edit" action="edit" id="${patientlnSemesterInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
