<%@ page import="sp_portal.User" %>

<div class="fieldcontain ${hasErrors(bean: question, field: 'value', 'error')} ">
	<h1><%= question.text %></h1>
	<g:each var="check" status="i" in="${checkValue}">
			<g:if test="${check.anamnesisCheck == question}">
				<g:set var="text" value="${check.anamnesisChecksValue}" />
		    </g:if>
			<g:else>
				<g:set var="text" value=" " />
			</g:else>
    </g:each> 
	
    <g:textArea name="question.${question.id}" rows="5" cols="40" resize="none" value="${text}"/>
</div>