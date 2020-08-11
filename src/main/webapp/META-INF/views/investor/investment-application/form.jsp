<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="investor.investment-application.form.label.ticker" path="ticker" readonly="true" />
		<acme:form-moment code="investor.investment-application.form.label.creationMoment" path="creationMoment" readonly="true" />
		<acme:form-textbox code="investor.investment-application.form.label.statement" path="statement" readonly="true" />
		<acme:form-money code="investor.investment-application.form.label.moneyOffer" path="moneyOffer" readonly="true" />
		<acme:form-textbox code="investor.investment-application.form.label.status" path="status" readonly="true" />
		<jstl:if test="${isRejected}">
			<acme:form-textarea code="investor.investment-application.form.label.justification" path="justification" readonly="true"/>
		</jstl:if>
	</jstl:if>

	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="investor.investment-application.form.label.statement" path="statement" />
		<acme:form-money code="investor.investment-application.form.label.moneyOffer" path="moneyOffer" />

		<acme:form-submit code="investor.investment-application.form.create" action="/investor/investment-application/create" />
	</jstl:if>

	<input id="invId" name="invId" value="${invId}" hidden="true"/>

	<acme:form-return code="investor.investment-application.button.return" />
</acme:form>
