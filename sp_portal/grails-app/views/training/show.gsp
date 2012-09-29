
<%@ page import="sp_portal.local.Training" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="userMain">
		<g:set var="entityName" value="${message(code: 'training.label', default: 'Training')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-training" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<!-- <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li> -->
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-training" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list training">
			
				<g:if test="${trainingInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="training.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${trainingInstance}" field="name"/></span>
					
				</li>
				</g:if>
				
							
				<g:if test="${trainingInstance?.trainingDate}">
				<li class="fieldcontain">
					<span id="trainingDate-label" class="property-label"><g:message code="training.trainingDate.label" default="Training Date" /></span>
					
						<span class="property-value" aria-labelledby="trainingDate-label"><g:formatDate format="yyyy-MM-dd" date="${trainingInstance?.trainingDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${trainingInstance?.timeStart}">
				<li class="fieldcontain">
					<span id="timeStart-label" class="property-label"><g:message code="training.timeStart.label" default="Time Start" /></span>
					
						<span class="property-value" aria-labelledby="timeStart-label"><g:formatDate format="HH:mm:ss" date="${trainingInstance?.timeStart}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${trainingInstance?.timeEnd}">
				<li class="fieldcontain">
					<span id="timeEnd-label" class="property-label"><g:message code="training.timeEnd.label" default="Time End" /></span>
					
						<span class="property-value" aria-labelledby="timeEnd-label"><g:formatDate format="HH:mm:ss" date="${trainingInstance?.timeEnd}" /></span>
					
				</li>
				</g:if>
				
				<g:if test="${trainingInstance?.semester}">
                <li class="fieldcontain">
                    <span id="semester-label" class="property-label"><g:message code="standardizedPatient.nationality.label" default="Semester" /></span>

                        <span class="property-value" aria-labelledby="nationality-label">
						${trainingInstance?.semester?.showSemester()}
						
                </li>
                </g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${trainingInstance?.id}" />
					<g:link class="edit" action="edit" id="${trainingInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
