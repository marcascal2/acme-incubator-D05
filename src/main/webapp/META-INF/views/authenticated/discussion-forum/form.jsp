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

	<acme:form-textbox code="authenticated.discussion-forum.form.label.investment-round.ticker" path="investmentRound.ticker"/>
	<acme:form-textbox code="authenticated.discussion-forum.form.label.investor.firmName" path="investor.firmName"/>
	<acme:form-submit method="get" code="authenticated.discussion-forum.form.button.messages" action="/authenticated/message/list?id=${forumId }"/>
	<acme:form-return code="authenticated.discussion-forum.form.button.return" />
</acme:form>
