<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="entrepreneur.investment-application.list.label.ticker" path="ticker" width="30%"/>
    <acme:list-column code="entrepreneur.investment-application.list.label.creationMoment" path="creationMoment" width="30%"/>
    <acme:list-column code="entrepreneur.investment-application.list.label.statement" path="statement" width="40%"/>
    <acme:list-column code="entrepreneur.investment-application.list.label.tickerRound" path="tickerRound" width="40%"/>
    <acme:list-column code="entrepreneur.investment-application.list.label.descriptionRound" path="descriptionRound" width="40%"/>
	<acme:list-column code="entrepreneur.investment-application.list.label.status" path="status" width="40%" />

</acme:list> 