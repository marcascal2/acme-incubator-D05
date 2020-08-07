<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="entrepreneur.investment-application.form.label.ticker" path="ticker"/>
	<acme:form-moment code="entrepreneur.investment-application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="entrepreneur.investment-application.form.label.statement" path="statement"/>
	<acme:form-money code="entrepreneur.investment-application.form.label.moneyOffer" path="moneyOffer"/>

  	<acme:form-return code="entrepreneur.investment-application.button.return"/>
</acme:form>