<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.notice.list.label.title" path="title" width="30%" />
	<acme:list-column code="anonymous.notice.list.label.creationDate" path="creationDate" width="35%" />
	<acme:list-column code="anonymous.notice.list.label.deadline" path="deadline" width="35%" />


</acme:list>
