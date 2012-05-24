
<%@ page import="sp_portal.User" %>
<%@ page import="sp_portal.AnamnesisCheck" %>
<!doctype html>
<html>
    <head>
      <meta name="layout" content="stdPnt">
      
    </head>
    <body>
    <g:each var="check" in="${checkList}">
					<li>${check.text}</li>
    </g:each>
         <h1><g:message code="default.welcome.message"/>&nbsp;&nbsp;&nbsp;<%= session.user.userName %> </h1>

    </body>
</html>
