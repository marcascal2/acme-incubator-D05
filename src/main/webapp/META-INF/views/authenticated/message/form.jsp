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

	<acme:form-textbox code="authenticated.message.form.label.title" path="title" />
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.message.form.label.moment" path="moment" />
	</jstl:if>
	<acme:form-textbox code="authenticated.message.form.label.body" path="body" />
	<jstl:if test="${not empty tags and command != 'create'}">
		<h5>
			<acme:message code="authenticated.message.form.label.tags" />
		</h5>
		<jstl:forEach items="${tags}" var="tags">
			<p>${tags}</p>
		</jstl:forEach>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:form-textarea code="authenticated.message.form.label.tags" path="tags" />
		<acme:message code="authenticated.message.form.tag.message" />
		<acme:form-checkbox code="authenticated.message.form.label.confirmation" path="confirmation" />
	</jstl:if>

	<acme:form-errors path="spam" />
	<acme:form-submit test="${command == 'create'}" code="authenticated.message.form.button.create"
		action="/authenticated/message/create?forumId=${forumId}" />

	<acme:form-return code="authenticated.message.form.button.return" />
</acme:form>
