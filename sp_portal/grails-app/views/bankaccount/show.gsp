
<%@ page import="sp_portal.local.Bankaccount" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="stdpnt">
        <g:set var="entityName" value="${message(code: 'bankaccount.label', default: 'Bankkonto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-bankaccount" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div id="show-bankaccount" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list bankaccount">

                <g:if test="${bankaccountInstance?.bic}">
                <li class="fieldcontain">
                    <span id="bic-label" class="property-label"><g:message code="bankaccount.bic.label" default="Bic" /></span>

                        <span class="property-value" aria-labelledby="bic-label"><g:fieldValue bean="${bankaccountInstance}" field="bic"/></span>

                </li>
                </g:if>

                <g:if test="${bankaccountInstance?.iban}">
                <li class="fieldcontain">
                    <span id="iban-label" class="property-label"><g:message code="bankaccount.iban.label" default="Iban" /></span>

                        <span class="property-value" aria-labelledby="iban-label"><g:fieldValue bean="${bankaccountInstance}" field="iban"/></span>

                </li>
                </g:if>

                <g:if test="${bankaccountInstance?.bankName}">
                <li class="fieldcontain">
                    <span id="bankName-label" class="property-label"><g:message code="bankaccount.bankName.label" default="Bank Name" /></span>

                        <span class="property-value" aria-labelledby="bankName-label"><g:fieldValue bean="${bankaccountInstance}" field="bankName"/></span>

                </li>
                </g:if>

                <g:if test="${bankaccountInstance?.city}">
                <li class="fieldcontain">
                    <span id="city-label" class="property-label"><g:message code="bankaccount.city.label" default="City" /></span>

                        <span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${bankaccountInstance}" field="city"/></span>

                </li>
                </g:if>

                <g:if test="${bankaccountInstance?.ownerName}">
                <li class="fieldcontain">
                    <span id="ownerName-label" class="property-label"><g:message code="bankaccount.ownerName.label" default="Owner Name" /></span>

                        <span class="property-value" aria-labelledby="ownerName-label"><g:fieldValue bean="${bankaccountInstance}" field="ownerName"/></span>

                </li>
                </g:if>

                <g:if test="${bankaccountInstance?.postalCode}">
                <li class="fieldcontain">
                    <span id="postalCode-label" class="property-label"><g:message code="bankaccount.postalCode.label" default="Postal Code" /></span>

                        <span class="property-value" aria-labelledby="postalCode-label"><g:fieldValue bean="${bankaccountInstance}" field="postalCode"/></span>

                </li>
                </g:if>




            </ol>
            <g:form>
                <fieldset class="buttons">
                    <g:hiddenField name="id" value="${bankaccountInstance?.id}" />
                    <g:link class="edit" action="edit" id="${bankaccountInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>

                </fieldset>
            </g:form>
        </div>
    </body>
</html>
