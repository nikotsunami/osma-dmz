
<%@ page import="sp_portal.User" %>
<%@ page import="sp_portal.local.AnamnesisCheck" %>
<%@ page import="ch.unibas.medizin.osce.shared.AnamnesisCheckTypes" %>
<!doctype html>
<html>
<head>
<title>showPage</title>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
        <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
        <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'portal.css')}" type="text/css">
</head>
	<body style="height:700px">
	 <g:render template="questionTITLE" model="['question':title]"/>  
	
	 
	 <g:form action="save" >
			<fieldset class="form">
				<g:each var="value" status="i" in="${questions}">
						<g:if test="${value.type == AnamnesisCheckTypes.QUESTION_YES_NO.getTypeId()}">
				   
							   <g:render template="questionYN" model="['question':value]"/>   
						</g:if>  
					<g:if test="${value.type == AnamnesisCheckTypes.QUESTION_MULT_S.getTypeId()}">
						
							<g:render template="questionMultiS" model="['question':value]"/>   
						</g:if>
						<g:if test="${value.type == AnamnesisCheckTypes.QUESTION_MULT_M.getTypeId()}">
						
							<g:render template="questionMultim" model="['question':value]"/>   
						</g:if>
						<g:if test="${value.type == AnamnesisCheckTypes.QUESTION_OPEN.getTypeId()}">
						
							<g:render template="questionOpen" model="['question':value]"/>   
							
						</g:if>
						</br>
				</g:each>
		
			</fieldset>
			<fieldset class="buttons" style="algin:bottom">
				<g:submitButton name="Save" value="${message(code: 'default.button.save')} " />
				
				<g:if test="${params.int('index') != 0}">
					<g:actionSubmit action="showFirst"  name="First" value="${message(code: 'default.button.first')}" />
					<g:actionSubmit action="showPreviou"  name="Previous" value="${message(code: 'default.button.previous')}" />
				</g:if>
				<g:if test="${params.int('index') != (titleSize-1)}">
					<g:actionSubmit action="showNext" name="Previous" value="${message(code: 'default.button.next')}" />
					<g:actionSubmit action="showEnd"  name="End" value="${message(code: 'default.button.end')}" />
				</g:if>
			</fieldset>
	</g:form>
	</body>
</html>
