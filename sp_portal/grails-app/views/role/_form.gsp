<%@ page import="sp_portal.Role" %>



<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'roleDescription', 'error')} ">
	<label for="roleDescription">
		<g:message code="role.roleDescription.label" default="Role Description" />
		
	</label>
	<g:textField name="roleDescription" value="${roleInstance?.roleDescription}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'roleName', 'error')} ">
	<label for="roleName">
		<g:message code="role.roleName.label" default="Role Name" />
		
	</label>
	<g:textField name="roleName" value="${roleInstance?.roleName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'users', 'error')} ">
	<label for="users">
		<g:message code="role.users.label" default="Users" />
		
	</label>
	
</div>

