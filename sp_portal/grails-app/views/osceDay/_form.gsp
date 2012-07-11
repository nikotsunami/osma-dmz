<%@ page import="sp_portal.local.OsceDay" %>
<calendar:resources lang="en" theme="tiger"/> 


<div class="fieldcontain ${hasErrors(bean: osceDayInstance, field: 'osceDate', 'error')} required">
	<label for="osceDate">
		<g:message code="osceDay.osceDate.label" default="Osce Date" />
		<span class="required-indicator">*</span>
	</label>
	
	<calendar:datePicker name="osceDate" value="${osceDayInstance?.osceDate}" /> 
</div>

