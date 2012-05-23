
<%@ page import="sp_portal.local.StandardizedPatient" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'standardizedPatient.label', default: 'StandardizedPatient')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-standardizedPatient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-standardizedPatient" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="birthday" title="${message(code: 'standardizedPatient.birthday.label', default: 'Birthday')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'standardizedPatient.city.label', default: 'City')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'standardizedPatient.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="gender" title="${message(code: 'standardizedPatient.gender.label', default: 'Gender')}" />
					
						<g:sortableColumn property="height" title="${message(code: 'standardizedPatient.height.label', default: 'Height')}" />
					
						<g:sortableColumn property="immagePath" title="${message(code: 'standardizedPatient.immagePath.label', default: 'Immage Path')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${standardizedPatientInstanceList}" status="i" var="standardizedPatientInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${standardizedPatientInstance.id}">${fieldValue(bean: standardizedPatientInstance, field: "birthday")}</g:link></td>
					
						<td>${fieldValue(bean: standardizedPatientInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: standardizedPatientInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: standardizedPatientInstance, field: "gender")}</td>
					
						<td>${fieldValue(bean: standardizedPatientInstance, field: "height")}</td>
					
						<td>${fieldValue(bean: standardizedPatientInstance, field: "immagePath")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${standardizedPatientInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
