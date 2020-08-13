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
	<jstl:if test="${command == 'create' }">
		<acme:form-select code="authenticated.discussion-forum.form.label.investor" path="userToAdd" readonly="false">
			<jstl:forEach items="${user_usernames}" var="username" varStatus="loop">
				<acme:form-option code="${username}" value="${user_ids[loop.index]}" />
			</jstl:forEach>
		</acme:form-select>

		<acme:form-checkbox code="authenticated.discussion-forum.check.create" path="checkCreate" readonly="false" />
		<acme:form-submit code="authenticated.discussion-forum.form.button.create" method="post"
			action="/authenticated/discussion-forum/create" />
	</jstl:if>

	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="authenticated.discussion-forum.form.label.investment-round.ticker" path="investmentRound.ticker"
			readonly="true" />

		<acme:form-submit method="get" code="authenticated.discussion-forum.form.button.messages"
			action="/authenticated/message/list?id=${forumId}" />

		<acme:form-submit method="get" code="authenticated.discussion-forum.form.button.update.forum"
			action="/authenticated/discussion-forum/update?id=${forumId}" />

		<acme:form-submit method="get" code="authenticated.discussion-forum.form.button.message.create"
			action="/authenticated/message/create?forumId=${forumId}" />

		<acme:form-submit test="${!isInvestor}" code="authenticated.discussion-forum.form.button.delete"
			action="/authenticated/discussion-forum/delete" />
	</jstl:if>

	<jstl:if test="${command == 'update'}">
		<acme:form-select code="authenticated.discussion-forum.form.label.investor" path="userToAdd" readonly="false">
			<jstl:forEach items="${user_usernames}" var="username" varStatus="loop">
				<acme:form-option code="${username}" value="${user_ids[loop.index]}" />
			</jstl:forEach>
		</acme:form-select>

		<acme:form-submit code="authenticated.discussion-forum.form.button.update" method="post"
			action="/authenticated/discussion-forum/update" />
	</jstl:if>

	<input id="invId" name="invId" value="${invId}" type="hidden" />
	<acme:form-return code="authenticated.discussion-forum.form.button.return" />

</acme:form>
