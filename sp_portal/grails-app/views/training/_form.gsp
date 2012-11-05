<%@ page import="sp_portal.local.Training" %>
<calendar:resources lang="en" theme="tiger"/> 


<div class="fieldcontain ${hasErrors(bean: trainingInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="training.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" value="${trainingInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: trainingInstance, field: 'trainingDate', 'error')} required">
	<label for="trainingDate">
		<g:message code="training.trainingDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<!-- <g:datePicker name="trainingDate" precision="day"  value="${trainingInstance?.trainingDate}"  /> -->
	<calendar:datePicker name="timeStart" value="${trainingInstance?.timeStart}" dateFormat="%d.%m.%Y" />
	<g:textField name="timeStartHour" value="${trainingInstance?.timeStart?.getHours()}"  style="width:30px" maxlength="2"/> : 
	<g:if test="${trainingInstance?.timeStart?.getMinutes()!=null&&trainingInstance?.timeStart?.getMinutes()<10}">
		<g:textField name="timeStartMin" value="${'0'+(String)trainingInstance?.timeStart?.getMinutes()}" maxlength="2" style="width:30px"/>
	</g:if>
	<g:else>
		<g:textField name="timeStartMin" value="${(String)trainingInstance?.timeStart?.getMinutes()}" maxlength="2" style="width:30px"/>
	</g:else>  


</div>


<!--<div class="fieldcontain ${hasErrors(bean: trainingInstance, field: 'timeStart', 'error')} required">
	<label for="timeStart">
		<g:message code="training.timeStart.label" default="Time Start" />
		<span class="required-indicator">*</span>
	</label>

	<g:textField name="timeStartHour" value="${trainingInstance?.timeStart?.getHours()}"  style="width:30px" maxlength="2"/> : 
	<g:if test="${trainingInstance?.timeStart?.getMinutes()!=null&&trainingInstance?.timeStart?.getMinutes()<10}">
		<g:textField name="timeStartMin" value="${'0'+(String)trainingInstance?.timeStart?.getMinutes()}" maxlength="2" style="width:30px"/>
	</g:if>
	<g:else>
		<g:textField name="timeStartMin" value="${(String)trainingInstance?.timeStart?.getMinutes()}" maxlength="2" style="width:30px"/>
	</g:else>  

</div>-->

<div class="fieldcontain ${hasErrors(bean: trainingInstance, field: 'timeEnd', 'error')} required">
		<label for="timeEnd">
		<g:message code="training.timeEnd.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<!-- <g:datePicker name="trainingDate" precision="day"  value="${trainingInstance?.trainingDate}"  /> -->
	<calendar:datePicker name="timeEnd" value="${trainingInstance?.timeEnd}" dateFormat="%d.%m.%Y" />
	<g:textField name="timeEndHour" value="${trainingInstance?.timeEnd?.getHours()}" maxlength="2" style="width:30px"/> : 
	<g:if test="${trainingInstance?.timeEnd?.getMinutes()!=null&&trainingInstance?.timeEnd?.getMinutes()<10}">
		<g:textField name="timeEndMin" value="${'0'+(String)trainingInstance?.timeEnd?.getMinutes()}" maxlength="2" style="width:30px"/>
	</g:if>
	<g:else>
		<g:textField name="timeEndMin" value="${(String)trainingInstance?.timeEnd?.getMinutes()}" maxlength="2" style="width:30px"/>
	</g:else>  
</div>

<div class="fieldcontain ${hasErrors(bean: trainingInstance, field: 'semester', 'error')} ">
    <label for="semester">
        <g:message code="patientlnSemester.semester.label" default="Semester" />
		<span class="required-indicator">*</span>
    </label>

    <g:select name="semester" from="${sp_portal.local.Semester.list()}" optionKey="id" value="${trainingInstance?.semester?.id}" noSelection="['': '']"/>
</div>




