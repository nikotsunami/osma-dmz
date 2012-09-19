<%@ page import="sp_portal.local.StandardizedPatient" %>

<div id="list-user" class="content scaffold-list" role="main">
	<table>
		<thead>
			<tr>			
				<g:sortableColumn property="email" title="${message(code: 'user.label.isSend')}" />
				<g:sortableColumn property="email" title="${message(code: 'user.userEmail.label', default: 'User Email')}" />
				<g:sortableColumn property="name" title="${message(code: 'user.userName.label', default: 'User Name')}" />
			</tr>
		</thead>
		<tbody>
		<g:each in="${patientInstanceList}" status="i" var="patientInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
				
				<td><g:checkBox name="patient.${patientInstance.id}" value="send" checked="${params.isSend}"/></td>
				<td>${fieldValue(bean: patientInstance, field: "email")}</td>
				<td>${fieldValue(bean: patientInstance, field: "name")}</td>
			</tr>
		</g:each>
		</tbody>
	</table> 
</div>