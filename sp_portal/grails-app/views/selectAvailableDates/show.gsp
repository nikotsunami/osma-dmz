
<%@ page import="sp_portal.User" %>

<!DOCTYPE html>
<head>
<meta name="layout" content="stdpnt">
<title>show select Available Dates</title>
</head>

<body>
	<g:form action="update">
		<a href="#show-Patients" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		  <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
	<div id="Layer1">
		<h1>Training Days</h1>
		<div id="Layer2" align="center">
  		<p>Plase Select Which of the</p>
  		<p>following date you can attend</p>
  		<p>Thank you.</p>
		</div>
		<div id="Layer3">
			<g:if test="${availableTrainingDays!=null}">
  				<table>
					<tr>
							<g:sortableColumn property="trainingDate" title="${message(code: 'training.date',default: 'Date')}" />
							<g:sortableColumn property="timeStart" title="${message(code: 'training.startTime', default: 'StartTime')}" />
							<g:sortableColumn property="timeEnd" title="${message(code: 'trining.endTime', default: 'EndTime')}" />
							<g:sortableColumn property="trainingDate" title="${message(code: 'IsAccepted',default: 'IsAccepted')}" />
					</tr>
  			<g:each in="${availableTrainingDays}" status="i" var="availableTrainingDay">
    				<tr>
						  <td align="center"><g:formatDate format="yyyy-MM-dd" date="${availableTrainingDay.trainingDate}"/></td>
						  <td align="center"><g:formatDate type="time" date="${availableTrainingDay.timeStart}"/></td>
						  <td align="center"><g:formatDate type="time" date="${availableTrainingDay.timeEnd}"/></td>
						  <g:if test="${acceptedTrainingDays!=null}">
							<g:if test="${acceptedTrainingDays.contains(availableTrainingDay)}">
								<td align="center"><g:checkBox name="training.${availableTrainingDay.id}" checked="true"/></td>
							</g:if>
							<g:else>
								<td align="center"><g:checkBox name="training.${availableTrainingDay.id}" checked="false"/></td>
							</g:else>
	  						</g:if>
      
	  
    				</tr>
			</g:each>
  				</table>
  			</g:if>
		</div>
		<div id="Layer4">
  			<h1>OSCE Exams</h1></div>
		<div id="Layer5" align="center">
  			<p>Plase Select Which of the</p>
  			<p>following osce date you can attend</p>
		</div>
  		<div id="Layer6">
    		<table>
				<tr>
					<g:sortableColumn property="osceDate" title="${message(code: 'osce.date', default: 'Date')}"/><g:sortableColumn property="trainingDate" title="${message(code: 'IsAccepted',default: 'IsAccepted')}"/></tr>
					
					<g:if test="${availableOsceDays!=null}">
						<g:each in="${availableOsceDays}" status="i" var="availableOsceDay">
      						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td align="center"><g:formatDate format="yyyy-MM-dd" date="${availableOsceDay.osceDate}"/></td>
							<g:if test="${acceptedOsceDays!=null}">
							<g:if test="${acceptedOsceDays.contains(availableOsceDay)}">
							<td align="center">
		 					<g:checkBox name="osce.${availableOsceDay.id}" checked="true"/></td>
							</g:if>
							<g:else>
								<td align="center">
		 						<g:checkBox name="osce.${availableOsceDay.id}" checked="false"/></td>
							</g:else>
							</g:if>
        
      						</tr>
	 				 		</g:each>
	  
							</g:if>
    		</table>
  		</div>
  		<div id="Layer7">
				<fieldset class="buttons">		
                    <g:submitButton name="update" value="Save" />
				</fieldset>
            
		</div>
</div>

</g:form>

</body>
</html>
