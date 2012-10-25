
<%@ page import="sp_portal.local.Bankaccount" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="stdpnt">
        <g:set var="entityName" value="${message(code: 'bankaccount.label', default: 'Bankkonto')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-bankaccount" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-bankaccount" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>

                        <g:sortableColumn property="bic" title="${message(code: 'bankaccount.bic.label', default: 'Bic')}" />

                        <g:sortableColumn property="iban" title="${message(code: 'bankaccount.iban.label', default: 'Iban')}" />

                        <g:sortableColumn property="bankName" title="${message(code: 'bankaccount.bankName.label', default: 'Bank Name')}" />

                        <g:sortableColumn property="city" title="${message(code: 'bankaccount.city.label', default: 'City')}" />

                        <g:sortableColumn property="ownerName" title="${message(code: 'bankaccount.ownerName.label', default: 'Owner Name')}" />

                        <g:sortableColumn property="postalCode" title="${message(code: 'bankaccount.postalCode.label', default: 'Postal Code')}" />

                    </tr>
                </thead>
                <tbody>
                <g:each in="${bankaccountInstanceList}" status="i" var="bankaccountInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td><g:link action="show" id="${bankaccountInstance.id}">${fieldValue(bean: bankaccountInstance, field: "bic")}</g:link></td>

                        <td>${fieldValue(bean: bankaccountInstance, field: "iban")}</td>

                        <td>${fieldValue(bean: bankaccountInstance, field: "bankName")}</td>

                        <td>${fieldValue(bean: bankaccountInstance, field: "city")}</td>

                        <td>${fieldValue(bean: bankaccountInstance, field: "ownerName")}</td>

                        <td>${fieldValue(bean: bankaccountInstance, field: "postalCode")}</td>

                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${bankaccountInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
