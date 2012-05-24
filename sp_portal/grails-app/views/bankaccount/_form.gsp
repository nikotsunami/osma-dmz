<%@ page import="sp_portal.local.Bankaccount" %>



<div class="fieldcontain ${hasErrors(bean: bankaccountInstance, field: 'bic', 'error')} ">
    <label for="bic">
        <g:message code="bankaccount.bic.label" default="Bic" />

    </label>
    <g:textField name="bic" maxlength="40" value="${bankaccountInstance?.bic}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bankaccountInstance, field: 'iban', 'error')} ">
    <label for="iban">
        <g:message code="bankaccount.iban.label" default="Iban" />

    </label>
    <g:textField name="iban" maxlength="40" value="${bankaccountInstance?.iban}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bankaccountInstance, field: 'bankName', 'error')} ">
    <label for="bankName">
        <g:message code="bankaccount.bankName.label" default="Bank Name" />

    </label>
    <g:textField name="bankName" maxlength="40" value="${bankaccountInstance?.bankName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bankaccountInstance, field: 'city', 'error')} ">
    <label for="city">
        <g:message code="bankaccount.city.label" default="City" />

    </label>
    <g:textField name="city" maxlength="30" value="${bankaccountInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bankaccountInstance, field: 'ownerName', 'error')} ">
    <label for="ownerName">
        <g:message code="bankaccount.ownerName.label" default="Owner Name" />

    </label>
    <g:textField name="ownerName" maxlength="40" value="${bankaccountInstance?.ownerName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bankaccountInstance, field: 'postalCode', 'error')} ">
    <label for="postalCode">
        <g:message code="bankaccount.postalCode.label" default="Postal Code" />

    </label>
    <g:field type="number" name="postalCode" value="${fieldValue(bean: bankaccountInstance, field: 'postalCode')}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: bankaccountInstance, field: 'standardizedPatients', 'error')} ">


</div>

