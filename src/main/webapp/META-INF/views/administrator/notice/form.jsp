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
	<acme:form-url code="administrator.notice.form.label.headerPicture" path="headerPicture" />
	<acme:form-textbox code="administrator.notice.form.label.title" path="title" />
	<acme:form-moment code="administrator.notice.form.label.deadline" path="deadline" />
	<acme:form-textarea code="administrator.notice.form.label.body" path="body" />
	
	<jstl:if test="${ command != 'create' && not empty relatedNotices}">
		<h5>
			<acme:message code="administrator.notice.form.label.relatedNotices" />
		</h5>
		<jstl:forEach items="${relatedNotices}" var="notice">
			</br>
			<p>${notice}</p>
		</jstl:forEach>
	</jstl:if>

	<jstl:if test="${ command == 'create' }">
		<h5>
			<acme:message code="administrator.notice.form.label.relatedNotices" />
		</h5>
		<h6>Los links deben ser introducidos separados por comas(,)</h6>
		<acme:form-textarea code="" path="relatedNotices" />
	</jstl:if>

	<acme:form-checkbox code="administrator.notice.form.label.check" path="accept"/>

	<acme:form-submit test="${ command == 'create' }" code="administrator.technology-record.form.button.create"
		action="/administrator/notice/create" />
	<acme:form-return code="administrator.notice.form.button.return" />
</acme:form>
