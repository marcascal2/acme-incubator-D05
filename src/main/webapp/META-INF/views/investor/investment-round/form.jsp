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

<acme:form readonly="true">
	<acme:form-textbox code="investor.investment-round.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN" />
	<acme:form-moment code="investor.investment-round.form.label.creationDate" path="creationDate" />
	<acme:form-textbox code="investor.investment-round.form.label.kindOfRound" path="kindOfRound" />
	<acme:form-textbox code="investor.investment-round.form.label.title" path="title" />
	<acme:form-textarea code="investor.investment-round.form.label.description" path="description" />
	<acme:form-money code="investor.investment-round.form.label.amount" path="amount" />
	<acme:form-url code="investor.investment-round.form.label.link" path="link" />
	<acme:form-errors path="application" />
	<acme:form-errors path="finalMode" />

	<acme:form-submit method="get" code="investor.investment-round.form.button.activity" action="/investor/activity/list?id=${invId}" />
	<acme:form-submit method="get" code="investor.investment-round.form.button.application"
		action="/investor/investment-application/create?invId=${invId}" />
	<acme:form-return code="investor.investment-round.form.button.return" />
</acme:form>
