
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
              <g:render template="questionTITLE" model="['question':title]"/> 
	<div style=" height:100% width:100%;OVERFLOW:scroll;OVERFLOW-X:hidden">
	 <g:form style="height:600px">
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
				<g:actionSubmit action="Save" value="${message(code: 'default.button.save')}" />
				
				<g:if test="${params.int('index') != 0}">
					<g:actionSubmit action="showFirst"  name="First" value="${message(code: 'default.button.first')}" />
					<g:actionSubmit action="showPreviou"  name="Previous" value="${message(code: 'default.button.previous')}" />
				</g:if>
					<g:actionSubmit action="showNext" name="Previous" value="${message(code: 'default.button.next')}" />
					<g:actionSubmit action="showEnd"  name="End" value="${message(code: 'default.button.end')}" />
				
			</fieldset>
	</g:form>
	</div>
    </body>
</html>
