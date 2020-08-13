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
	<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title" />
	<acme:form-moment code="entrepreneur.activity.form.label.startDate" path="startDate" />
	<acme:form-moment code="entrepreneur.activity.form.label.endDate" path="endDate" />
	<acme:form-double code="entrepreneur.activity.form.label.amount" path="amount" />
	
	<acme:form-errors path="spam"/>
		<acme:form-submit method="post" test="${command == 'create'}" code="entrepreneur.activity.form.button.create"
		action="/entrepreneur/activity/create?id=${id}" />
	
	<acme:form-submit test="${command != 'create'}" code="entrepreneur.activity.form.button.update"
		action="/entrepreneur/activity/update" />
	<acme:form-return code="entrepreneur.activity.form.button.return" />
	
	<input id="invId" name="invId" value="${invId}" type="hidden" />
</acme:form>
