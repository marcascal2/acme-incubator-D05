<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.technologyrecords.list.label.title" path="title" width="15%" />
	<acme:list-column code="administrator.technologyrecords.list.label.inventor" path="inventor" width="35%" />
	<acme:list-column code="administrator.technologyrecords.list.label.description" path="description" width="35%" />


</acme:list>
