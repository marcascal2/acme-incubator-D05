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
	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" path="ticker" readonly="true" />
		<acme:form-moment code="entrepreneur.investment-round.form.label.creationDate" path="creationDate" readonly="true" />
	</jstl:if>
	<jstl:if test="${not finalMode}">
		<acme:form-textbox code="entrepreneur.investment-round.form.label.kindOfRound" path="kindOfRound"
			placeholder="SEED, ANGEL, SERIES_A, SERIES_B, SERIES_C, BRIDGE" />
		<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title" />
		<acme:form-textarea code="entrepreneur.investment-round.form.label.description" path="description" />
		<acme:form-money code="entrepreneur.investment-round.form.label.amount" path="amount" />
		<acme:form-url code="entrepreneur.investment-round.form.label.link" path="link" />

		<acme:form-errors path="application" />
		<acme:form-errors path="finalMode" />
	</jstl:if>
	<jstl:if test="${finalMode}">
		<acme:form-textbox code="entrepreneur.investment-round.form.label.kindOfRound" path="kindOfRound"
			placeholder="SEED, ANGEL, SERIES_A, SERIES_B, SERIES_C, BRIDGE" readonly="true" />
		<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title" readonly="true" />
		<acme:form-textarea code="entrepreneur.investment-round.form.label.description" path="description" readonly="true" />
		<acme:form-money code="entrepreneur.investment-round.form.label.amount" path="amount" readonly="true" />
		<acme:form-url code="entrepreneur.investment-round.form.label.link" path="link" readonly="true" />
	</jstl:if>



	<acme:form-submit method="post" test="${command == 'create'}" code="entrepreneur.investment-round.form.button.create"
		action="/entrepreneur/investment-round/create" />


	<acme:form-submit test="${command != 'create' and not finalMode}" code="entrepreneur.investment-round.form.button.update"
		action="/entrepreneur/investment-round/update" />


	<acme:form-submit test="${command != 'create' and canDelete}" code="entrepreneur.investment-round.form.button.delete"
		action="/entrepreneur/investment-round/delete" />

	<jstl:if test="${command != 'create'}">
		<jstl:if test="${not sumUp}">
			<acme:form-submit method="get" code="entrepreneur.activity.form.button.create" action="/entrepreneur/activity/create?id=${invId}" />
		</jstl:if>
		<acme:form-submit method="get" code="entrepreneur.investment-round.form.button.activity"
			action="/entrepreneur/activity/list?id=${invId}" />
	</jstl:if>

	<acme:form-return code="entrepreneur.investment-round.form.button.return" />
	<input id="invId" name="invId" value="${invId}" type="hidden" />

	<acme:form-submit test="${createForum and command != 'create'}" method="get"
		code="entrepreneur.investment-round.form.button.create-forum" action="/authenticated/discussion-forum/create?invId=${invId}" />
</acme:form>
