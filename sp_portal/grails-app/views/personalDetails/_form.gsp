<%@ page import="sp_portal.local.StandardizedPatient" %>
<%@ page import="ch.unibas.medizin.osce.shared.*" %>
<%@ page import="org.joda.time.*" %>
<calendar:resources lang="en" theme="tiger"/> 
<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'preName', 'error')} ">
    <label for="preName">
        <g:message code="standardizedPatient.preName.label" default="Pre Name" />

    </label>
    <g:textField name="preName" maxlength="40" value="${standardizedPatientInstance?.preName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="standardizedPatient.name.label" default="Name" />

    </label>
    <g:textField name="name" maxlength="40" value="${standardizedPatientInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'street', 'error')} ">
    <label for="street">
        <g:message code="standardizedPatient.street.label" default="Street" />

    </label>
    <g:textField name="street" maxlength="60" value="${standardizedPatientInstance?.street}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'postalCode', 'error')} ">
    <label for="postalCode">
        <g:message code="standardizedPatient.postalCode.label" default="Postal Code" />

    </label>
    <g:textField name="postalCode" maxlength="15" value="${standardizedPatientInstance.postalCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'city', 'error')} ">
    <label for="city">
        <g:message code="standardizedPatient.city.label" default="City" />

    </label>
    <g:textField name="city" maxlength="30" value="${standardizedPatientInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'telephone', 'error')} ">
    <label for="telephone">
        <g:message code="standardizedPatient.telephone.label" default="Telephone" />

    </label>
    <g:textField name="telephone" maxlength="30" value="${standardizedPatientInstance?.telephone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'telephone2', 'error')} ">
    <label for="telephone2">
        <g:message code="standardizedPatient.telephone2.label" default="Telephone2" />

    </label>
    <g:textField name="telephone2" maxlength="30" value="${standardizedPatientInstance?.telephone2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'mobile', 'error')} ">
    <label for="mobile">
        <g:message code="standardizedPatient.mobile.label" default="Mobile" />

    </label>
    <g:textField name="mobile" maxlength="30" value="${standardizedPatientInstance?.mobile}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'email', 'error')} ">
    <label for="email">
        <g:message code="standardizedPatient.email.label" default="Email" />

    </label>
    <g:textField name="email" maxlength="40" value="${standardizedPatientInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'birthday', 'error')} ">
    <label for="birthday">
        <g:message code="standardizedPatient.birthday.label" default="Birthday" />

    </label>
    
	 <calendar:datePicker name="birthday" dateFormat="%d.%m.%Y" value="${standardizedPatientInstance?.birthday?.toDate()}" defaultValue="" years="1920,2999"/> 
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'gender', 'error')} ">
    <label for="gender">
        <g:message code="standardizedPatient.gender.label" default="Gender" />
 
    </label>
    <g:radio name="gender" value="0" checked="${fieldValue(bean: standardizedPatientInstance, field: 'gender').equals("0")}"/>&nbsp;&nbsp;${message(code: 'default.gender.male')}&nbsp;&nbsp;&nbsp;&nbsp;
	<g:radio name="gender" value="1" checked="${fieldValue(bean: standardizedPatientInstance, field: 'gender').equals("1")}"/>&nbsp;&nbsp;${message(code: 'default.gender.female')}
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'nationality', 'error')} ">
    <label for="nationality">
        <g:message code="standardizedPatient.nationality.label" default="Nationality" />

    </label>
    <g:select id="nationality" name="nationality.id" from="${sp_portal.local.Nationality.list()}" optionKey="id" value="${standardizedPatientInstance?.nationality?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'profession', 'error')} ">
    <label for="profession">
        <g:message code="standardizedPatient.profession.label" default="Profession" />

    </label>
    <g:select id="profession" name="profession.id" from="${sp_portal.local.Profession.list()}" optionKey="id" value="${standardizedPatientInstance?.profession?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'height', 'error')} ">
    <label for="height">
        <g:message code="standardizedPatient.height.label" default="Height" />

    </label>
    <g:field type="number" name="height" value="${fieldValue(bean: standardizedPatientInstance, field: 'height')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'weight', 'error')} ">
    <label for="weight">
        <g:message code="standardizedPatient.weight.label" default="Weight" />

    </label>
    <g:field type="number" name="weight" value="${fieldValue(bean: standardizedPatientInstance, field: 'weight')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'maritalStatus', 'error')} ">
    <label for="maritalStatus">
        <g:message code="standardizedPatient.maritalStatus.label" default="Marital Status" />
    </label>
    
    <g:select name="maritalStatus" from="${MaritalStatus?.values()}" value="${fieldValue(bean: standardizedPatientInstance, field: 'maritalStatus')}" valueMessagePrefix="enum.maritalStatus.value"  optionKey="value" />
	<%--<g:select name="maritalStatus" from="${MaritalStatus?.values()}" value="${fieldValue(bean: standardizedPatientInstance, field: 'maritalStatus')}" noSelection="['': '']" optionKey="value"/>
	--%><!--  <g:select name="maritalStatus" valueMessagePrefix="enum.value" from="${MaritalStatus?.values()}" keys="${MaritalStatus?.values()}" /> -->
    <!--<g:field type="number" name="maritalStatus" value="${fieldValue(bean: standardizedPatientInstance, field: 'maritalStatus')}"/>-->
</div>


<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'workPermission', 'error')} ">
    <label for="workPermission">
        <g:message code="standardizedPatient.workPermission.label" default="Work Permission" />

    </label>
	<g:select name="workPermission" from="${WorkPermission?.values()}" value="${fieldValue(bean: standardizedPatientInstance, field: 'workPermission')}" noSelection="['': '']" optionKey="key"/>
<!-- <g:field type="number" name="workPermission" value="${fieldValue(bean: standardizedPatientInstance, field: 'workPermission')}"/>-->
</div>

<div class="fieldcontain ${hasErrors(bean: standardizedPatientInstance, field: 'socialInsuranceNo', 'error')} ">
    <label for="socialInsuranceNo">
        <g:message code="standardizedPatient.socialInsuranceNo.label" default="Social Insurance No" />

    </label>
    <g:textField name="socialInsuranceNo" maxlength="20" value="${standardizedPatientInstance?.socialInsuranceNo}"/>
</div>

