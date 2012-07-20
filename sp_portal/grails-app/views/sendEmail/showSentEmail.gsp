
<%@ page import="sp_portal.local.StandardizedPatient" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="userMain">
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
	<body style="height:90%">
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>	
		<div id="list-user" class="content scaffold-list" role="main">
			<h1><g:message code="user.label.sentEmails"/></h1>
			<table>
				<thead>
					<tr>			
						<g:sortableColumn property="sent" title="${message(code: 'user.label.Sent')}" />
						<g:sortableColumn property="sendDate" title="${message(code: 'user.label.SendDate')}" />
						<g:sortableColumn property="subject" title="${message(code: 'user.label.Subject')}" />
						<g:sortableColumn property="receiver" title="${message(code: 'user.label.Recipients')}" />
						<g:sortableColumn property="sent" title=" "/>
					</tr>
				</thead>
				<tbody>
				<g:each in="${emailList}" status="i" var="emailInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: emailInstance, field: "sent")}</td>
						<td>${fieldValue(bean: emailInstance, field: "sendDate")}</td>
						<td>${fieldValue(bean: emailInstance, field: "subject")}</td>
						<td>${fieldValue(bean: emailInstance, field: "receiver")}</td>
						<td><g:link controller="sendEmail" action="showEmailDetails" id="${emailInstance.id}"><g:message code="user.label.Show" /></g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:link controller="sendEmail" action="show" ><g:message code="default.back.label"/></g:link>
                <g:paginate total="${emailTotal}" />
            </div>
	    </div>
		  		
    </body>
</html>
