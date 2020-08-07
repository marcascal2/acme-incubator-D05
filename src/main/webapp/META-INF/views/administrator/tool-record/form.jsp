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

<acme:form>
	<acme:form-textbox code="administrator.toolrecord.form.label.title" path="title" />
	<acme:form-textbox code="administrator.toolrecord.form.label.inventorName" path="inventorName" />
	<acme:form-textarea code="administrator.toolrecord.form.label.description" path="description" />
	<acme:form-url code="administrator.toolrecord.form.label.webSite" path="webSite" />
	<acme:form-textbox code="administrator.toolrecord.form.label.email" path="email" />
	<acme:form-checkbox code="administrator.toolrecord.form.label.openSource" path="openSource" />
	<acme:form-double code="administrator.toolrecord.form.label.stars" path="stars" />
	<acme:form-textbox code="administrator.toolrecord.form.label.sector" path="activitySector.sector" />

	<acme:form-submit test="${ command == 'show' }" code="administrator.toolrecord.form.button.update"
		action="/administrator/tool-record/update" />

	<acme:form-submit test="${ command == 'show' }" code="administrator.toolrecord.form.button.delete"
		action="/administrator/tool-record/delete" />

	<acme:form-submit test="${ command == 'create' }" code="administrator.toolrecord.form.button.create"
		action="/administrator/tool-record/create" />

	<acme:form-submit test="${ command == 'update' }" code="administrator.toolrecord.form.button.update"
		action="/administrator/tool-record/update" />

	<acme:form-submit test="${ command == 'delete' }" code="administrator.toolrecord.form.button.delete"
		action="/administrator/tool-record/delete" />

	<acme:form-return code="administrator.toolrecord.form.button.return" />
</acme:form>
