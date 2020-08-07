<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.tool-record.form.label.title" path="title"/>
	<acme:form-textbox code="anonymous.tool-record.form.label.activitySector" path="activitySector.sector"/>
	<acme:form-textarea code="anonymous.tool-record.form.label.inventorName" path="inventorName"/>
	<acme:form-textarea code="anonymous.tool-record.form.label.Description" path="description"/>
	<acme:form-url code="anonymous.tool-record.form.label.webSite" path="webSite"/>
	<acme:form-textbox code="anonymous.tool-record.form.label.email" path="email"/>
	<acme:form-checkbox code="anonymous.tool-record.form.label.openSource" path="openSource"/>
	<acme:form-textbox code="anonymous.tool-record.form.label.stars" path="stars"/>
	<acme:form-return code="anonymous.tool-record.form.button.return"/>
</acme:form>
