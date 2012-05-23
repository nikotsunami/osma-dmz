<%@ page import="sp_portal.local.AnamnesisForm" %>



<div class="fieldcontain ${hasErrors(bean: anamnesisFormInstance, field: 'createDate', 'error')} ">
	<label for="createDate">
		<g:message code="anamnesisForm.createDate.label" default="Create Date" />
		
	</label>
	<g:datePicker name="createDate" precision="day"  value="${anamnesisFormInstance?.createDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: anamnesisFormInstance, field: 'origId', 'error')} required">
	<label for="origId">
		<g:message code="anamnesisForm.origId.label" default="Orig Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="origId" required="" value="${fieldValue(bean: anamnesisFormInstance, field: 'origId')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: anamnesisFormInstance, field: 'standardizedPatients', 'error')} ">
	<label for="standardizedPatients">
		<g:message code="anamnesisForm.standardizedPatients.label" default="Standardized Patients" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${anamnesisFormInstance?.standardizedPatients?}" var="s">
    <li><g:link controller="standardizedPatient" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="standardizedPatient" action="create" params="['anamnesisForm.id': anamnesisFormInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient')])}</g:link>
</li>
</ul>

</div>

