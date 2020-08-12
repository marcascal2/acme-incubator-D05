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

	<acme:form-textbox readonly="true" code="entrepreneur.investment-application.form.label.ticker" path="ticker" />
	<acme:form-moment readonly="true" code="entrepreneur.investment-application.form.label.creationMoment" path="creationMoment" />
	<acme:form-textbox readonly="true" code="entrepreneur.investment-application.form.label.statement" path="statement" />
	<acme:form-money readonly="true" code="entrepreneur.investment-application.form.label.moneyOffer" path="moneyOffer" />
	
	<jstl:if test="${!isAccepted}">
		<acme:form-textbox code="entrepreneur.investment-application.form.label.justification" path="justification" />
	</jstl:if>
	<jstl:if test="${command =='update' || oldstatus == 'PENDING'}">
		<acme:form-select code="entrepreneur.investment-application.form.label.status" path="status">
			<acme:form-option code="entrepreneur.investment-application.form.label.rejected" value="REJECTED" />
			<acme:form-option code="entrepreneur.investment-application.form.label.accepted" value="ACCEPTED" />
		</acme:form-select>
	</jstl:if>


	<acme:form-submit test="${command == 'show' && oldstatus == 'PENDING'}"
		code="entrepreneur.investment-application.form.button.update" action="/entrepreneur/investment-application/update" />
	<acme:form-submit test="${command == 'update'}" code="entrepreneur.investment-application.form.button.update"
		action="/entrepreneur/investment-application/update" />


	<acme:form-return code="entrepreneur.investment-application.button.return" />

</acme:form>

