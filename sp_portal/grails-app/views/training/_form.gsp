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
		<g:message code="training.trainingDate.label" default="Training Date" />
		<span class="required-indicator">*</span>
	</label>
	<!-- <g:datePicker name="trainingDate" precision="day"  value="${trainingInstance?.trainingDate}"  /> -->
	<calendar:datePicker name="trainingDate" value="${trainingInstance?.trainingDate}" /> 
</div>

<div class="fieldcontain ${hasErrors(bean: trainingInstance, field: 'timeStart', 'error')} required">
	<label for="timeStart">
		<g:message code="training.timeStart.label" default="Time Start" />
		<span class="required-indicator">*</span>
	</label>
	<!-- <g:datePicker name="timeStart" precision="day" value="${trainingInstance?.timeStart}"  /> -->

	<g:select name="timeStartHour" from="${[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]}" value="${trainingInstance?.timeStart?.getHours()}" noSelection="['': '']" optionKey="value"/> : 
    <g:if test="${trainingInstance?.timeStart?.getMinutes()<10}">
		<g:select name="timeStartMin" from="${['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59']}" value="${'0'+(String)trainingInstance?.timeStart?.getMinutes()}" noSelection="['': '']" optionKey="value"/>
	</g:if>
	<g:else>
		<g:select name="timeStartMin" from="${['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59']}" value="${(String)trainingInstance?.timeStart?.getMinutes()}" noSelection="['': '']" optionKey="value"/>
	</g:else>
</div>

<div class="fieldcontain ${hasErrors(bean: trainingInstance, field: 'timeEnd', 'error')} required">
	<label for="timeEnd">
		<g:message code="training.timeEnd.label" default="Time End" />
		<span class="required-indicator">*</span>
	</label>
	<!-- <g:datePicker name="timeEnd" precision="day" value="${trainingInstance?.timeEnd}"/> -->
	<g:select name="timeEndHour" from="${[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]}" value="${trainingInstance?.timeEnd?.getHours()}" noSelection="['':'']" optionKey="value"/> : 
	<g:if test="${trainingInstance?.timeEnd?.getMinutes()<10}">
		<g:select name="timeEndMin" from="${['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59']}" value="${'0'+(String)trainingInstance?.timeEnd?.getMinutes()}" noSelection="['': '']" optionKey="value"/>
	</g:if>
	<g:else>
		<g:select name="timeEndMin" from="${['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59']}" value="${(String)trainingInstance?.timeEnd?.getMinutes()}" noSelection="['': '']" optionKey="value"/>
	</g:else>
</div>





