<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h4><acme:message code="investor.activity.title"/></h4>
<acme:list>
	<acme:list-column code="investor.activity.list.label.title" path="title" width="25%" />
	<acme:list-column code="investor.activity.list.label.startDate" path="startDate" width="25%" />
	<acme:list-column code="investor.activity.list.label.endDate" path="endDate" width="25%" />
	<acme:list-column code="investor.activity.list.label.amount" path="amount" width="25%" />
</acme:list>
