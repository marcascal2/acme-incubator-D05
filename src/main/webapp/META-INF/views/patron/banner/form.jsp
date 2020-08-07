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
	<acme:form-url code="patron.banner.form.label.picture" path="picture" />
	<acme:form-textbox code="patron.banner.form.label.slogan" path="slogan" />
	<acme:form-url code="patron.banner.form.label.url" path="url" />
	<jstl:if test="${hasCard}">
		<h3>
			<acme:message code="patron.banner.form.label.card" />
		</h3>
		<acme:form-textbox code="patron.banner.form.label.card.holder" path="card.holder" readonly="true" />
		<acme:form-textbox code="patron.banner.form.label.card.number" path="card.number" readonly="true" />
		<acme:form-textbox code="patron.banner.form.label.card.brand" path="card.brand" readonly="true" />
		<acme:form-textbox code="patron.banner.form.label.card.expirationDate" path="card.expirationDate" readonly="true" />
		<acme:form-textbox code="patron.banner.form.label.card.cvv" path="card.cvv" readonly="true" />
	</jstl:if>
	<acme:form-return code="patron.banner.form.button.return" />
</acme:form>
