
<%@ page import="sp_portal.User" %>
<%@ page import="sp_portal.local.AnamnesisCheck" %>
<%@ page import="ch.unibas.medizin.osce.shared.AnamnesisCheckTypes" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="stdpnt">

</head>
 <body>
	
					
	    		
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="questionTITLE" model="['question':title]"/>   
					 <z:iframe style="width:100%; height:400px;border:0px inset;" scrolling="yes" src="http://www.zkoss.org" >
								 
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
								</g:each>
						</z:iframe>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="Save" value="save" />
	        <g:actionSubmit action="showFirst"  name="First" value="fir" />
		      <g:actionSubmit action="showPreviou"  name="Previous" value="pre" />
					<g:actionSubmit action="showNext" name="Previous" value="nex" />
					<g:actionSubmit action="showEnd"  name="End" value="end" />
				</fieldset>
			</g:form>
	</body>
</html>
