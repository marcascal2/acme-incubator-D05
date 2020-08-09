<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="entrepreneur.investment-application.form.label.ticker" path="ticker"/>
	<acme:form-moment code="entrepreneur.investment-application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="entrepreneur.investment-application.form.label.statement" path="statement"/>
	<acme:form-money code="entrepreneur.investment-application.form.label.moneyOffer" path="moneyOffer"/>
	
	<h4><acme:message code="entrepreneur.investment-application.form.label.round"/></h4>
	<acme:form-textarea code="entrepreneur.investment-application.form.label.titleRound" path="roundTitle"/> 
	<acme:form-textarea code="entrepreneur.investment-application.form.label.desciptionRound" path="roundDescription"/> 
	<acme:form-textarea code="entrepreneur.investment-application.form.label.linkRound" path="roundLink"/> 
	
  	<acme:form-return code="entrepreneur.investment-application.button.return"/>
</acme:form>