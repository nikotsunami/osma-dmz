<%@ page import="sp_portal.User" %>

<div class="fieldcontain ${hasErrors(bean: question, field: 'value', 'error')} ">
	<h3><%= question.text %></h3>
	<g:set var="text" value=" " />
	<g:each var="check" status="i" in="${checkValue}">
		<g:if test="${check.anamnesisChecksValue != null}">
			<g:if test="${check.anamnesisCheck == question}">				
				<g:set var="text" value="${check.anamnesisChecksValue}" />								
		    </g:if>
		</g:if>	
    </g:each> 
	
    <g:textArea name="question.${question.id}" rows="5" cols="40" resize="none" value="${text}"/>
</div>