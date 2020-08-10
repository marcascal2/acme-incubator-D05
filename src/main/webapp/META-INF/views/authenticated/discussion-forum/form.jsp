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

	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="authenticated.discussion-forum.form.label.investment-round.ticker" path="investmentRound.ticker" />
	</jstl:if>
	
	<acme:form-submit test = "${command != 'create'}" method="get" code="authenticated.discussion-forum.form.button.messages"
		action="/authenticated/message/list?id=${forumId }" />

	<acme:message code="authenticated.discussion-forum.check.create" />
	
	<acme:form-submit method="get" code="authenticated.discussion-forum.form.button.create-forum"
		action="/authenticated/discussion-forum/create?id=${invId}" />

	<acme:form-return code="authenticated.discussion-forum.form.button.return" />

</acme:form>
