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
	<acme:form-textbox code="authenticated.overture.label.title" path="title"/>
	<acme:form-textbox code="authenticated.overture.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="authenticated.overture.label.deadline" path="deadline"/>
	<acme:form-textbox code="authenticated.overture.label.description" path="description"/>
	<acme:form-textbox code="authenticated.overture.label.maxMoney" path="maxMoney"/>
	<acme:form-textbox code="authenticated.overture.label.minMoney" path="minMoney"/>
	<acme:form-textbox code="authenticated.overture.label.email" path="email"/>
	  
  	<acme:form-return code="authenticated.overture.button.return"/>
</acme:form>
