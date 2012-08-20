<div class="fieldcontain ${hasErrors(bean: question, field: 'value', 'error')} ">
<h3><%= question.text %></h3>

<g:each var="value" status="i" in="${question.value.split('\\|')}">

	  <g:each var="check" status="j" in="${checkValue}">
					<g:if test="${check.anamnesisCheck == question}">
						<g:if test="${check.anamnesisChecksValue != null}">
							<g:each var="data" status="c" in="${check.anamnesisChecksValue.split("\\-")}">
							<g:if test="${i == c}">
							 
							  <g:set var="validValue" value="${data.equals("1") ? true: false}" />
								</g:if>      
							</g:each>
						</g:if>
				</g:if>
	   </g:each>
   <g:radio name="question.${question.id}" value="${value}" checked="${validValue}"/>&nbsp;&nbsp;<%= value %>&nbsp;&nbsp;
</g:each>
</div>
