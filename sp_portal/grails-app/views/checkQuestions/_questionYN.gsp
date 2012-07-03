<%@ page import="sp_portal.User" %>

<div class="fieldcontain ${hasErrors(bean: question, field: 'value', 'error')} ">
    <h1><%= question.text %></h1>
     <g:each var="check" status="i" in="${checkValue}">
				<g:if test="${check.anamnesisCheck == question}">
				<g:set var="validValue" value="${check.truth==true ? true: false}" />
				<g:set var="valid" value="${check.truth==false ? true: false}" />
				  
		    </g:if>
    </g:each>
    <g:radio name="question.${question.id}" value="true" checked="${validValue}"/>&nbsp;&nbspYes&nbsp;&nbsp;&nbsp;&nbsp;
		<g:radio name="question.${question.id}" value="false" checked="${valid}"/>&nbsp;&nbspNo		    	  
</div>