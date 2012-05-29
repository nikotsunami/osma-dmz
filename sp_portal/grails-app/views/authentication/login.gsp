<html>
  <head>
    <meta name="layout" content="main" />
    <title><g:message code="login.button"/> </title>
  </head>
  <body>
    <div class="body">
      <h1><g:message code="login.button"/></h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:form action="authenticate" method="post" >
        <div class="dialog">
          <table>
            <tbody>
              <tr class="prop">
                <td class="name">
                  <label for="login"><g:message code="username.label"/></label>
                </td>
                <td>
                  <input type="text" id="userName" name="userName"/>
                </td>
              </tr>

              <tr class="prop">
                <td class="name">
                  <label for="password"><g:message code="password.label"/></label>
                </td>
                <td>
                  <input type="password" id="passwordHash" name="passwordHash"/>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="buttons">
          <span class="button">
            <input class="save" type="submit" value=<g:message code="login.button"/> />
          </span>
        </div>
      </g:form>
    </div>
  </body>
</html>
