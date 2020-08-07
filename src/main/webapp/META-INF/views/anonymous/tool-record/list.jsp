<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.tool-record.list.label.title" path="title" width="30%" />
	<acme:list-column code="anonymous.tool-record.list.label.activitySector" path="activitySector.sector" width="30%" />
	<acme:list-column code="anonymous.tool-record.list.label.Description" path="description" width="40%" />

</acme:list>
