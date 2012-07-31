
<%@ page import="sp_portal.User" %>
<%@ page import="sp_portal.local.AnamnesisCheck" %>
<%@ page import="ch.unibas.medizin.osce.shared.AnamnesisCheckTypes" %>
<!doctype html>
<html>
<head>
      <meta name="layout" content="stdpnt">
<title>showPage</title>
</head>
<body>

              <iframe id="questionPage" src="${createLink(action:'showPage',controller:'checkQuestions')}" width="100%" height="600"  frameborder="0" scrolling="auto">
                </iframe>

    </body>
</html>
