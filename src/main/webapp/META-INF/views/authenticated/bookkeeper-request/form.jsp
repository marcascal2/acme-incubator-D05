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

<acme:form readonly="false">

	<acme:form-textbox code="authenticated.bookkeeper-request.form.firmName" path="firmName" />
	<acme:form-textbox code="authenticated.bookkeeper-request.form.responsibility" path="responsibility" />

	<acme:form-submit test="${command == 'create'}" code="authenticated.bookkeeper-request.form.label.create"
		action="/authenticated/bookkeeper-request/create" />
		
		<acme:form-submit test="${command == 'update'}" code="authenticated.bookkeeper-request.form.button.update"
		action="/authenticated/bookkeeper-request/update" />
	<acme:form-submit test="${command == 'show'}" code="authenticated.bookkeeper-request.form.button.update"
		action="/authenticated/bookkeeper-request/update" />

	<acme:form-return code="authenticated.bookkeeper-request.button.return" />
</acme:form>
