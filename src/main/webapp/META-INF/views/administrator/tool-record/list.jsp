<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.toolrecord.list.label.title" path="title" width="25%" />
	<acme:list-column code="administrator.toolrecord.list.label.activitySector" path="activitySector.sector" width="25%" />
	<acme:list-column code="administrator.toolrecord.list.label.stars" path="stars" width="25%" />
	<acme:list-column code="administrator.toolrecord.list.label.openSource" path="openSource" width="25%" />
</acme:list>
