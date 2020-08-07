<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.challenge.list.label.deadline" path="deadline" width="200"/>
	<acme:list-column code="administrator.challenge.list.label.title" path="title" width="200"/>
	<acme:list-column code="administrator.challenge.list.label.expertGoal" path="expertGoal" width="200"/>
	<acme:list-column code="administrator.challenge.list.label.averageGoal" path="averageGoal" width="200"/>
	<acme:list-column code="administrator.challenge.list.label.rookieGoal" path="rookieGoal" width="200"/>
</acme:list>
