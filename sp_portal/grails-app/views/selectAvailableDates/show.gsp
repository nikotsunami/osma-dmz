
<%@ page import="sp_portal.User" %>



<!DOCTYPE html>
<head>
<meta name="layout" content="stdpnt">
<g:javascript library="jquery" />
<title><g:message code="default.selectAvailableDates.message" /></title>
</head>

<body>
	<a href="#show-Patients" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		  <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
	<div style="width: 100%; height: 50px;">
		<div style="float: right">
			<table>
			<tr>
			<td><strong><g:message code="patientlnSemester.semester.label" /></strong></td>
			<td><g:form controller = "SelectAvailableDates" action="showSemester">
				<g:select name="semester" from="${sp_portal.local.Semester.list()}" style="float: right" optionKey="id" optionValue="${{it.showSemester()}}" value="${session.semester}" noSelection="['': '']" 
					onchange="this.form.submit()"
										/>
			</g:form></td>
			</tr>
			</table>
		</div>
		
		<h1><g:message code="patientlnSemester.acceptedTraining.label" /></h1>
		
	</div>
	<div  align="center">
  		<g:message code="selectAvailableDates.trainingMessage.label" />
	</div>

	<g:form controller = "SelectAvailableDates" action="update">
		<div>				
 			<table>
				<tr>
					<g:sortableColumn property="trainingDate" title="${message(code: 'training.trainingDate.label',default: 'Date')}" defaultOrder="desc"/>
					<g:sortableColumn property="timeStart" title="${message(code: 'training.timeStart.label', default: 'StartTime')}" />
					<g:sortableColumn property="timeEnd" title="${message(code: 'training.timeEnd.label', default: 'EndTime')}" />
					<g:sortableColumn property="trainingDate" title="${message(code: 'selectAvailableDates.accepted.label',default: 'Accepted')}" />
				</tr>
				<g:if test="${availableTrainingDays!=null}">
 						<g:each in="${availableTrainingDays}" status="i" var="availableTrainingDay">
   						<tr in="${availableTrainingDays}" status="i" var="availableTrainingDay">
  							<td align="center">
  								<g:formatDate format="dd.MM.yyyy" date="${availableTrainingDay.trainingDate}"/>
  							</td>
					  		<td align="center">
					  			<g:formatDate type="time" date="${availableTrainingDay.timeStart}"/>
					  		</td>
					  		<td align="center">
					  			<g:formatDate type="time" date="${availableTrainingDay.timeEnd}"/>
					  		</td>
					  		<g:if test="${acceptedTrainingDays!=null}">
								<g:if test="${acceptedTrainingDays.contains(availableTrainingDay)}">
									<td align="center">
										<g:checkBox name="training.${availableTrainingDay.id}" checked="true"/>
									</td>
								</g:if>
							<g:else>
								<td align="center">
									<g:checkBox name="training.${availableTrainingDay.id}" checked="false"/>
								</td>
							</g:else>
  							</g:if>
   						</tr>
					</g:each>
				</g:if>
 			</table>
		</div>
		<div style="margin-top: 50px;">
  			<h1><g:message code="patientlnSemester.acceptedOsceDay.label" /></h1>
  		</div>
		<div id="Layer5" align="center">
  			<g:message code="selectAvailableDates.osceMessage.label" />
		</div>
  		<div>
    		<table>
				<tr>
					<g:sortableColumn property="osceDate" title="${message(code: 'osce.date', default: 'Date')}"defaultOrder="desc"/>
					<g:sortableColumn property="trainingDate" title="${message(code: 'selectAvailableDates.accepted.label',default: 'Accepted')}"/>
				</tr>	
				<g:if test="${availableOsceDays!=null}">
					<g:each in="${availableOsceDays}" status="i" var="availableOsceDay">
      					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td align="center"><g:formatDate format="dd.MM.yyyy" date="${availableOsceDay.osceDate}"/></td>
								<g:if test="${acceptedOsceDays!=null}">
									<g:if test="${acceptedOsceDays.contains(availableOsceDay)}">
										<td align="center">
		 									<g:checkBox name="osce.${availableOsceDay.id}" checked="true"/>
		 								</td>
									</g:if>
								<g:else>
									<td align="center">
		 								<g:checkBox name="osce.${availableOsceDay.id}" checked="false"/>
		 							</td>
								</g:else>
								</g:if>
        
      					</tr>
	 				 </g:each>
	  			</g:if>
    		</table>
  		</div>
  		<div >
			<fieldset class="buttons">		
				<g:submitButton class="save" name="update" value="${message(code: 'default.button.save', default: 'Update')}" />
			</fieldset>
		</div>
	</g:form>
</body>
</html>
