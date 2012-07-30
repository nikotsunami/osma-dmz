
<%@ page import="sp_portal.local.StandardizedPatient" %>
<%@ page import="ch.unibas.medizin.osce.shared.*" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="stdpnt">
        <g:set var="entityName" value="${message(code: 'standardizedPatient.label', default: 'StandardizedPatient')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-standardizedPatient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div id="show-standardizedPatient" class="content scaffold-show" role="main">
            <h1><%= standardizedPatientInstance.preName + "  " + standardizedPatientInstance.name %></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list standardizedPatient">

                <g:if test="${standardizedPatientInstance?.birthday}">
                <li class="fieldcontain">
                    <span id="birthday-label" class="property-label"><g:message code="standardizedPatient.birthday.label" default="Birthday" /></span>

                        <span class="property-value" aria-labelledby="birthday-label">
						
						${standardizedPatientInstance?.birthday}
						</span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.city}">
                <li class="fieldcontain">
                    <span id="city-label" class="property-label"><g:message code="standardizedPatient.city.label" default="City" /></span>

                        <span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${standardizedPatientInstance}" field="city"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.email}">
                <li class="fieldcontain">
                    <span id="email-label" class="property-label"><g:message code="standardizedPatient.email.label" default="Email" /></span>

                        <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${standardizedPatientInstance}" field="email"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.gender != null}">
                <li class="fieldcontain">
                    <span id="gender-label" class="property-label"><g:message code="standardizedPatient.gender.label" default="Gender" /></span>

                        <span class="property-value" aria-labelledby="gender-label">${standardizedPatientInstance?.gender == 0 ? message(code: 'default.gender.male') : message(code: 'default.gender.female')}
						<!--<g:fieldValue bean="${standardizedPatientInstance}" field="gender"/>--></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.height}">
                <li class="fieldcontain">
                    <span id="height-label" class="property-label"><g:message code="standardizedPatient.height.label" default="Height" /></span>

                        <span class="property-value" aria-labelledby="height-label"><g:fieldValue bean="${standardizedPatientInstance}" field="height"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.immagePath}">
                <li class="fieldcontain">
                    <span id="immagePath-label" class="property-label"><g:message code="standardizedPatient.immagePath.label" default="Immage Path" /></span>

                        <span class="property-value" aria-labelledby="immagePath-label"><g:fieldValue bean="${standardizedPatientInstance}" field="immagePath"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.maritalStatus != null}">			  
				<g:each var="mStatus" status="i" in="${MaritalStatus?.values()}">
					<g:if test="${mStatus.getValue() == standardizedPatientInstance?.maritalStatus}">
						<g:set var="value" value="${mStatus.name()}" />
					</g:if>
				 </g:each>
				<li class="fieldcontain">
                    <span id="maritalStatus-label" class="property-label"><g:message code="standardizedPatient.maritalStatus.label" default="Marital Status" /></span>
					
				    <span class="property-value" aria-labelledby="maritalStatus-label">${value}
					<!--<g:fieldValue bean="${standardizedPatientInstance}" field="maritalStatus"/>--></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.mobile}">
                <li class="fieldcontain">
                    <span id="mobile-label" class="property-label"><g:message code="standardizedPatient.mobile.label" default="Mobile" /></span>

                        <span class="property-value" aria-labelledby="mobile-label"><g:fieldValue bean="${standardizedPatientInstance}" field="mobile"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.name}">
                <li class="fieldcontain">
                    <span id="name-label" class="property-label"><g:message code="standardizedPatient.name.label" default="Name" /></span>

                        <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${standardizedPatientInstance}" field="name"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.postalCode}">
                <li class="fieldcontain">
                    <span id="postalCode-label" class="property-label"><g:message code="standardizedPatient.postalCode.label" default="Postal Code" /></span>

                        <span class="property-value" aria-labelledby="postalCode-label"><g:fieldValue bean="${standardizedPatientInstance}" field="postalCode"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.preName}">
                <li class="fieldcontain">
                    <span id="preName-label" class="property-label"><g:message code="standardizedPatient.preName.label" default="Pre Name" /></span>

                        <span class="property-value" aria-labelledby="preName-label"><g:fieldValue bean="${standardizedPatientInstance}" field="preName"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.socialInsuranceNo}">
                <li class="fieldcontain">
                    <span id="socialInsuranceNo-label" class="property-label"><g:message code="standardizedPatient.socialInsuranceNo.label" default="Social Insurance No" /></span>

                        <span class="property-value" aria-labelledby="socialInsuranceNo-label"><g:fieldValue bean="${standardizedPatientInstance}" field="socialInsuranceNo"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.street}">
                <li class="fieldcontain">
                    <span id="street-label" class="property-label"><g:message code="standardizedPatient.street.label" default="Street" /></span>

                        <span class="property-value" aria-labelledby="street-label"><g:fieldValue bean="${standardizedPatientInstance}" field="street"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.telephone}">
                <li class="fieldcontain">
                    <span id="telephone-label" class="property-label"><g:message code="standardizedPatient.telephone.label" default="Telephone" /></span>

                        <span class="property-value" aria-labelledby="telephone-label"><g:fieldValue bean="${standardizedPatientInstance}" field="telephone"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.telephone2}">
                <li class="fieldcontain">
                    <span id="telephone2-label" class="property-label"><g:message code="standardizedPatient.telephone2.label" default="Telephone2" /></span>

                        <span class="property-value" aria-labelledby="telephone2-label"><g:fieldValue bean="${standardizedPatientInstance}" field="telephone2"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.videoPath}">
                <li class="fieldcontain">
                    <span id="videoPath-label" class="property-label"><g:message code="standardizedPatient.videoPath.label" default="Video Path" /></span>

                        <span class="property-value" aria-labelledby="videoPath-label"><g:fieldValue bean="${standardizedPatientInstance}" field="videoPath"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.weight}">
                <li class="fieldcontain">
                    <span id="weight-label" class="property-label"><g:message code="standardizedPatient.weight.label" default="Weight" /></span>

                        <span class="property-value" aria-labelledby="weight-label"><g:fieldValue bean="${standardizedPatientInstance}" field="weight"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.workPermission != null}">
                <g:each var="permission" status="i" in="${WorkPermission?.values()}">
					<g:if test="${permission.getKey() == standardizedPatientInstance?.workPermission}">
						<g:set var="value" value="${permission.name()}" />
					</g:if>
				 </g:each>
				<li class="fieldcontain">
                    <span id="workPermission-label" class="property-label"><g:message code="standardizedPatient.workPermission.label" default="Work Permission" /></span>

                        <span class="property-value" aria-labelledby="workPermission-label">${value}
						<!--<g:fieldValue bean="${standardizedPatientInstance}" field="workPermission"/>--></span>

                </li>
                </g:if>


                <g:if test="${standardizedPatientInstance?.description}">
                <li class="fieldcontain">
                    <span id="description-label" class="property-label"><g:message code="standardizedPatient.description.label" default="Description" /></span>

                        <span class="property-value" aria-labelledby="description-label">
                          <g:fieldValue bean="${standardizedPatientInstance}" field="description"/>
                        </span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.profession}">
                <li class="fieldcontain">
                    <span id="profession-label" class="property-label"><g:message code="standardizedPatient.profession.label" default="Profession" /></span>

                        <span class="property-value" aria-labelledby="profession-label">
                        <g:fieldValue bean="${standardizedPatientInstance}" field="profession"/>
                        </span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.nationality}">
                <li class="fieldcontain">
                    <span id="nationality-label" class="property-label"><g:message code="standardizedPatient.nationality.label" default="Nationality" /></span>

                        <span class="property-value" aria-labelledby="nationality-label">
                        <g:fieldValue bean="${standardizedPatientInstance}" field="nationality"/></span>

                </li>
                </g:if>

                <g:if test="${standardizedPatientInstance?.bankaccount}">
                <li class="fieldcontain">
                    <span id="bankaccount-label" class="property-label"><g:message code="standardizedPatient.bankaccount.label" default="Bankaccount" /></span>

                    <span class="property-value" aria-labelledby="bankaccount-label">
                    <g:fieldValue bean="${standardizedPatientInstance}" field="bankaccount"/></span>

                </li>
                </g:if>



            </ol>

            <g:form>
                <fieldset class="buttons">
                    <g:hiddenField name="id" value="${standardizedPatientInstance?.id}" />
                    <g:link class="edit" action="edit" id="${standardizedPatientInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>

                </fieldset>
            </g:form>

        </div>
    </body>
</html>
