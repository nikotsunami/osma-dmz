<%@ page import="sp_portal.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'isActive', 'error')} ">
	<label for="isActive">
		<g:message code="user.isActive.label" default="Is Active" />
		
	</label>
	<g:checkBox name="isActive" value="${userInstance?.isActive}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordHash', 'error')} ">
	<label for="passwordHash">
		<g:message code="user.passwordHash.label" default="Password Hash" />
		
	</label>
	<g:textField name="passwordHash" value="${userInstance?.passwordHash}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'roles', 'error')} ">
	<label for="roles">
		<g:message code="user.roles.label" default="Roles" />
		
	</label>
	<g:select name="roles" from="${sp_portal.Role.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.roles*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userEmail', 'error')} ">
	<label for="userEmail">
		<g:message code="user.userEmail.label" default="User Email" />
		
	</label>
	<g:textField name="userEmail" value="${userInstance?.userEmail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userName', 'error')} ">
	<label for="userName">
		<g:message code="user.userName.label" default="User Name" />
		
	</label>
	<g:textField name="userName" value="${userInstance?.userName}"/>
</div>

