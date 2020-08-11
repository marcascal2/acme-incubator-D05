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

	<acme:form-textbox readonly="true" code="administrator.bookkeeper-request.form.firmName" path="firmName" />
	<acme:form-textbox readonly="true" code="administrator.bookkeeper-request.form.responsibility" path="responsibility" />

	<jstl:if test="${oldstatus == 'PENDING'||command =='update'}">
		<acme:form-select code="administrator.bookkeeper-request.form.label.status" path="status">
			<acme:form-option code="administrator.bookkeeper-request.form.label.rejected" value="REJECTED" />
			<acme:form-option code="administrator.bookkeeper-request.form.label.accepted" value="ACCEPTED" />
		</acme:form-select>
	</jstl:if>


	<acme:form-submit test="${command == 'show' && oldstatus == 'PENDING'}" code="administrator.bookkeeper-request.form.label.update"
		action="/administrator/bookkeeper-request/update" />
	<acme:form-submit test="${command == 'update'}" code="administrator.bookkeeper-request.form.label.update"
		action="/administrator/bookkeeper-request/update" />

	<acme:form-return code="administrator.bookkeeper-request.button.return" />
</acme:form>
