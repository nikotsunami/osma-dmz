<div class="fieldcontain ${hasErrors(bean: question, field: 'value', 'error')} ">
<h1><%= question.text %></h1>

<g:each var="value" status="i" in="${question.value.split('\\|')}">
   <g:radio name="question.${question.id}" value="${value}"/><%= value %>&nbsp;&nbsp;
</g:each>
</div>
