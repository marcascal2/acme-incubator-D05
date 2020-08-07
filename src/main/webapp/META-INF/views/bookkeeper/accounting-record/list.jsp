<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="bookkeeper.accounting-record.list.label.title" path="title" width="30%" />
	<acme:list-column code="bookkeeper.accounting-record.list.label.status" path="status" width="35%" />
	<acme:list-column code="bookkeeper.accounting-record.list.label.creationMoment" path="creationMoment" width="35%" />
</acme:list>
