<%@ page import="sp_portal.local.AnamnesisCheck" %>
<%@ page import="sp_portal.User" %>




<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->

    <head>
		<meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
        <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
        <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'portal.css')}" type="text/css">
        <g:layoutHead/>
        <r:layoutResources />
    </head>
    <body>
    	<div id="mainContainer">
      		<div id="grailsLogo" role="banner">
        		<img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/>
        		<g:if test="${session.user}">
           			<g:link class="right" controller="authentication" action="logout" ><g:message code="default.logout.label"/></g:link>
        		</g:if>
        	</div>
		

    		<div class="stdpnt-menu">
        		<ul>
					
	          		<g:link controller="myAccount" action="show" ><li><g:message code="default.myAccount.link"/></li></g:link>
			  		<g:if test="${session.user.standardizedPatient!=null}">
						<g:if test="${session.user.standardizedPatient.status==2}">
	          			<g:link controller="personalDetails" action="show" ><li><g:message code="default.personalDetails.link"/></li></g:link>
	          			<g:link controller="bankaccount" action="show" ><li><g:message code="default.banksDetails.link"/></li></g:link>
	          			<g:link controller="checkQuestions" action="index" ><li><g:message code="default.questions.message"/></li></g:link>
						</g:if>
						<g:elseif test="${session.user.standardizedPatient.status==4}">
						<g:link controller="selectAvailableDates" action="showSemester" ><li><g:message code="default.selectAvailableDates.message"/></li></g:link> 
						</g:elseif>
			  			
					</g:if>
	        	</ul>
	    	</div>
    	
    		<div class="stdpnt-main-panel">
	        	<g:layoutBody/>
	        </div>
	        
	        <div class="footer" role="contentinfo"></div>
	        <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	        <g:javascript library="application"/>
        	<r:layoutResources />
        </div>
    </body>
</html>
