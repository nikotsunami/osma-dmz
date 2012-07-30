
<%@ page import="sp_portal.local.StandardizedPatient" %>
<!doctype html>
<html>
    <head>
    </head>
    <body>
        <g:form action="syncJson" >
                <fieldset class="form">
                <div>
                    <h2>Test JSON Import</h2>
                </div>
                <div>
                    <h2>Enter JSON</h2>
                    <textArea class="editEmailBody" id="data" name="data" value="${defaultEmail}" rows="15" cols="90"></textArea>
                </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="submit" value="${message(code: 'user.button.send')}" />
                </fieldset>
        </g:form>
    </body>
</html>
