<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.discussion-forum.list.label.investmentRound.ticker" path="investmentRound.ticker" width="15%" />
	<acme:list-column code="authenticated.discussion-forum.list.label.investmentRound.kindOfRound" path="investmentRound.kindOfRound" width="35%" />
	<acme:list-column code="authenticated.discussion-forum.list.label.investmentRound.title" path="investmentRound.title" width="35%" />
</acme:list>
