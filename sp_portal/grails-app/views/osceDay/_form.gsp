<%@ page import="sp_portal.local.OsceDay" %>
<calendar:resources lang="en" theme="tiger"/> 


<div class="fieldcontain ${hasErrors(bean: osceDayInstance, field: 'osceDate', 'error')} required">
	<label for="osceDate">
		<g:message code="osceDay.osceDate.label" default="Osce Date" />
		<span class="required-indicator">*</span>
	</label>
	
	<calendar:datePicker name="osceDate" value="${osceDayInstance?.osceDate}" /> 
</div>


<div class="fieldcontain ${hasErrors(bean: osceDayInstance, field: 'osce', 'error')} ">
    <label for="osce">
        <g:message code="osceDay.osce.label" default="Osce" />
		<span class="required-indicator">*</span>
    </label>
    <g:select name="osce" from="${sp_portal.local.Osce.list()}" optionKey="id" value="${osceDayInstance?.osce?.id}" noSelection="['': '']"/>
</div>