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
	<acme:form-textbox code="authenticated.investment-round.label.title" path="title" />
	<acme:form-moment code="authenticated.investment-round.label.creationDate" path="creationDate" />
	<acme:form-textbox code="authenticated.investment-round.label.ticker" path="ticker" />
	<acme:form-textarea code="authenticated.investment-round.label.description" path="description" />
	<acme:form-double code="authenticated.investment-round.label.amount" path="amount" />
	<acme:form-url code="authenticated.investment-round.label.link" path="link" />

	<acme:form-return code="authenticated.investment-round.button.return" />
	<acme:form-submit method="get" code="authenticated.investment-round.form.button.activity"
		action="/authenticated/activity/list?id=${invId}" />

</acme:form>
