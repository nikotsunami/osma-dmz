
<%@ page import="sp_portal.User" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="userMain">
     <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

			<table>
      <g:each in="${messages}" status="i" var="message">
				<tr>
					<td><%= message %></td>
				</tr>

      </g:each>
      </table>
    </body>
</html>
