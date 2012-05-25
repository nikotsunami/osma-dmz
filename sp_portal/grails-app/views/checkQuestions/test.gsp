
<%@ page import="sp_portal.User" %>
<%@ page import="sp_portal.local.AnamnesisCheck" %>
<!doctype html>
<html>
       
	<g:render template="questionTITLE" model="['question':questionTitle]"/>      
	<g:render template="questionYN" model="['question':question1]"/>   
	<g:render template="questionMultiS" model="['question':question2]"/>   
	<g:render template="questionMultim" model="['question':questionMum]"/> 
	<g:render template="questionOpen" model="['question':questionOpen]"/>
	
	
	
</html>
