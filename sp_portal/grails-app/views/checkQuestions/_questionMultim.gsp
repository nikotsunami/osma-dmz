

<div class="fieldcontain ${hasErrors(bean: question, field: 'value', 'error')} ">
<h1><%= question.text %></h1>
<g:each var="value" status="i" in="${question.value.split('\\|')}">

 <g:each var="check" status="j" in="${checkValue}">
				<g:if test="${check.anamnesisCheck == question}">
					<g:each var="data" status="c" in="${check.anamnesisChecksValue.split("\\-")}">
			        <g:if test="${i == c}">
			      	 
			      	  <g:set var="validValue" value="${data.equals("1") ? true: false}" />
			  			</g:if>      
			    </g:each>
		    </g:if>
    </g:each>
   <g:checkBox name="question.${question.id}" value="${value}" checked="${validValue}"/>&nbsp;&nbsp;<%= value %>&nbsp;&nbsp;
</g:each>
</div>
