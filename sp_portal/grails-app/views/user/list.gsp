
<%@ page import="sp_portal.User" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="userMain">
        <g:set var="entityName" value="${message(code: 'user.label.user', default: 'Benutzer')}" />
        <title><g:message code="default.managerUser.link" /></title>
    </head>
    <body style="height:90%">
        <a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <!-- <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li> -->
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="importData"><g:message code="default.importData.link"/></g:link></li>
            </ul>
        </div>
        <div id="list-user" class="content scaffold-list" role="main">
            <h1><g:message code="default.managerUser.link" /></h1>
            <g:if test="${flash.message}">
            	<div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>
						 <g:sortableColumn property="userName" title="${message(code: 'user.userName.label', default: 'User Name')}" />
                        <g:sortableColumn property="isActive" title="${message(code: 'user.isActive.label', default: 'Is Active')}" />
                        
                        

                       

                    </tr>
                </thead>
                <tbody>
                <g:each in="${userInstanceList}" status="i" var="userInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
                        <td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "userName")}</g:link></td>

                        <td><g:formatBoolean boolean="${userInstance?.isActive}" /></td>

                        



                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${userInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
