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
	<acme:form-textbox code="authenticated.card.label.holder" path="holder" />
	<acme:form-textbox code="authenticated.card.label.number" path="number" />
	<acme:form-textbox code="authenticated.card.label.brand" path="brand" />
	<acme:form-textbox code="authenticated.card.label.expirationDate" path="expirationDate" />
	<acme:form-textbox code="authenticated.card.label.cvv" path="cvv" />
	
	<input id="patron" name="patron" value="${patron}" hidden="true"/>

	<acme:form-submit method="post" test="${command == 'create'}" code="authenticated.card.form.button.create" action="/authenticated/credit-card/create?patron=${patron}" />
	<acme:form-submit test="${command == 'update'}" code="authenticated.card.form.button.update" action="/authenticated/credit-card/update" />
	<input id="card" name="card" value="${card}" type="hidden" />
	<acme:form-return code="authenticated.card.button.return" />
</acme:form>
