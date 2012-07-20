
<%@ page import="sp_portal.User" %>
<%@ page import="sp_portal.local.AnamnesisCheck" %>
<!doctype html>
<html>
    <head>
      <meta name="layout" content="userMain">

    </head>
    <body>
         <h1><g:message code="user.sendEmail.failure"/></h1>
		 <g:link controller="sendEmail" action="show" ><g:message code="default.back.label"/></g:link>
    </body>
</html>
