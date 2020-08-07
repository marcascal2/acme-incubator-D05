<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.coleto-bulletin.list.label.name" path="name" width="15%"/>
	<acme:list-column code="anonymous.coleto-bulletin.list.label.age" path="age" width="15%"/>
	<acme:list-column code="anonymous.coleto-bulletin.list.label.degree" path="degree" width="35%"/>
	<acme:list-column code="anonymous.coleto-bulletin.list.label.favouriteSubject" path="favouriteSubject" width="35%"/>
</acme:list> 