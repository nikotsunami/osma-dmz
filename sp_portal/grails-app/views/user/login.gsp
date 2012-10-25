<html>
  <head>
    <meta name="layout" content="userMain" />
    <title>SP Portal - Anmeldung</title>
  </head>
  <body>
    <div class="body">
      <h1>SP Portal - Anmeldung</h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:form action="authenticate" method="post" >
        <div class="dialog">
          <table>
            <tbody>
              <tr class="prop">
                <td class="name">
                  <label for="login">Benutzername:</label>
                </td>
                <td>
                  <input type="text" id="userName" name="userName"/>
                </td>
              </tr>

              <tr class="prop">
                <td class="name">
                  <label for="password">Passwort:</label>
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
            <input class="save" type="submit" value="Login" />
          </span>
        </div>
      </g:form>
    </div>
  </body>
</html>
