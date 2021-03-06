
<%@ page import="sp_portal.local.PatientlnSemester" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="userMain">
		<g:set var="entityName" value="${message(code: 'patientlnSemester.label', default: 'PatientlnSemester')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-patientlnSemester" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<!-- <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li> -->
				<li><g:link class="create" action="create"><g:message code="patientlnSemester.create.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-patientlnSemester" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="patientlnSemester.standardizedPatient.label" default="StandardizedPatient" /></th>
						<th><g:message code="patientlnSemester.acceptedOsceDay.label" default="OsceDay" /></th>
						<th><g:message code="patientlnSemester.acceptedTraining.label" default="TrainingDay" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${patientlnSemesterInstanceList}" status="i" var="patientlnSemesterInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${patientlnSemesterInstance.id}">${fieldValue(bean: patientlnSemesterInstance, field: "standardizedPatient")}</g:link></td>
						<td><g:link action="show" id="${patientlnSemesterInstance.id}">${fieldValue(bean: patientlnSemesterInstance, field: "acceptedOsceDay")}</g:link></td>
						<td><g:link action="show" id="${patientlnSemesterInstance.id}">${fieldValue(bean: patientlnSemesterInstance, field: "acceptedTraining")}</g:link></td>
					</tr>
					
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${patientlnSemesterInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
