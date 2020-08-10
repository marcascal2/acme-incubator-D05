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
	<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" path="ticker" />
	<acme:form-moment code="entrepreneur.investment-round.form.label.creationDate" path="creationDate" />
	<acme:form-textbox code="entrepreneur.investment-round.form.label.kindOfRound" path="kindOfRound" />
	<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title" />
	<acme:form-textarea code="entrepreneur.investment-round.form.label.description" path="description" />
	<acme:form-money code="entrepreneur.investment-round.form.label.amount" path="amount" />
	<acme:form-url code="entrepreneur.investment-round.form.label.link" path="link" />
	
	<acme:form-return code="entrepreneur.investment-round.form.button.return" />
	<acme:form-submit method="get" code="entrepreneur.investment-round.form.button.activity" action="/entrepreneur/activity/list?id=${invId}"/>
	
	<acme:form-submit test="${createForum}" method="get" code="entrepreneur.investment-round.form.button.create-forum" action="/authenticated/discussion-forum/create?invId=${invId}"/>
	
</acme:form>
