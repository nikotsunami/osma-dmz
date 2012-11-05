<%@ page import="sp_portal.local.PatientlnSemester" %>

<div class="fieldcontain ${hasErrors(bean: patientlnSemesterInstance, field: 'standardizedPatient', 'error')} required">
	<label for="standardizedPatient">
		<g:message code="patientlnSemester.standardizedPatient.label" default="Standardized Patient" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="standardizedPatient" name="standardizedPatient.id" from="${sp_portal.local.StandardizedPatient.list()}" optionKey="id" required="" value="${patientlnSemesterInstance?.standardizedPatient?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: patientlnSemesterInstance, field: 'acceptedOsceDay', 'error')} ">
	<label for="acceptedOsceDay">
		<g:message code="patientlnSemester.acceptedOsceDay.label" default="Accepted Osce Day" />
		
	</label>
	<g:select name="acceptedOsceDay" from="${sp_portal.local.OsceDay.list()}" multiple="multiple" optionKey="id" size="5" value="${patientlnSemesterInstance?.acceptedOsceDay*.osceDate}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: patientlnSemesterInstance, field: 'acceptedTraining', 'error')} ">
	<label for="acceptedTraining">
		<g:message code="patientlnSemester.acceptedTraining.label" default="Accepted Training" />
		
	</label>
	<g:select name="acceptedTraining" from="${sp_portal.local.Training.list()}" multiple="multiple" optionKey="id" size="5" value="${patientlnSemesterInstance?.acceptedTraining*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: patientlnSemesterInstance, field: 'semester', 'error')} ">
    <label for="semester">
        <g:message code="patientlnSemester.semester.label" default="Semester" />
		<span class="required-indicator">*</span>
    </label>

    <g:select name="semester" from="${sp_portal.local.Semester.list()}" optionKey="id" value="${patientlnSemesterInstance?.semester?.id}" noSelection="['': '']"/>
</div>


