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
	<acme:form-textbox code="anonymous.technologyrecords.form.label.title" path="title" />
	<acme:form-textbox code="anonymous.technologyrecords.form.label.activitySector" path="activitySector.sector" />
	<acme:form-textbox code="anonymous.technologyrecords.form.label.inventor" path="inventor" />
	<acme:form-textarea code="anonymous.technologyrecords.form.label.description" path="description" />
	<acme:form-textbox code="anonymous.technologyrecords.form.label.email" path="email" />
	<acme:form-textbox code="anonymous.technologyrecords.form.label.website" path="website" />
	<acme:form-textbox code="anonymous.technologyrecords.form.label.source" path="openSource" />
	<acme:form-integer code="anonymous.technologyrecords.form.label.stars" path="stars" />


	<acme:form-return code="anonymous.technologyrecords.form.button.return" />
</acme:form>
