
<%@ page import="sp_portal.local.OsceDay" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="userMain">
		<g:set var="entityName" value="${message(code: 'osceDay.label', default: 'OSCE Termin')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-osceDay" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<!-- <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li> -->
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-osceDay" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list osceDay">
			
				<g:if test="${osceDayInstance?.osceDate}">
				<li class="fieldcontain">
					<span id="osceDate-label" class="property-label"><g:message code="osceDay.osceDate.label" default="Osce Date" /></span>
					
						<span class="property-value" aria-labelledby="osceDate-label"><g:formatDate format="yyyy-MM-dd" date="${osceDayInstance?.osceDate}" /></span>
					
				</li>
				</g:if>
				
				<g:if test="${osceDayInstance?.osce}">
                <li class="fieldcontain">
                    <span id="osce-label" class="property-label"><g:message code="standardizedPatient.nationality.label" default="Osce" /></span>

                        <span class="property-value" aria-labelledby="nationality-label">
						${osceDayInstance?.osce?.name}
						
                </li>
                </g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${osceDayInstance?.id}" />
					<g:link class="edit" action="edit" id="${osceDayInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
