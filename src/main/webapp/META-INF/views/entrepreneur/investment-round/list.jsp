<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="entrepreneur.investment-round.list.label.ticker" path="ticker" width="30%" />
	<acme:list-column code="entrepreneur.investment-round.list.label.creationDate" path="creationDate" width="35%" />
	<acme:list-column code="entrepreneur.investment-round.list.label.kindOfRound" path="kindOfRound" width="35%" />
</acme:list>
