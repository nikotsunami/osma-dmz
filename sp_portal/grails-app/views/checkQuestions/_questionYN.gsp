<%@ page import="sp_portal.User" %>

<div class="fieldcontain ${hasErrors(bean: question, field: 'value', 'error')} ">
    <h1><%= question.text %></h1>

    <g:radio name="question.${question.id}" value="true" />Yes&nbsp;&nbsp;&nbsp;&nbsp;
    <g:radio name="question.${question.id}" value="false"/>No

</div>