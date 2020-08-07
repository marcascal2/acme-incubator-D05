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
	<acme:form-textbox code="authenticated.inquire.label.title" path="title"/>
	<acme:form-textbox code="authenticated.inquire.label.creationMoment" path="creationDate"/>
	<acme:form-textbox code="authenticated.inquire.label.deadline" path="deadline"/>
	<acme:form-textbox code="authenticated.inquire.label.description" path="description"/>
	<acme:form-textbox code="authenticated.inquire.label.maxMoney" path="maxMoney"/>
	<acme:form-textbox code="authenticated.inquire.label.minMoney" path="minMoney"/>
	<acme:form-textbox code="authenticated.inquire.label.email" path="email"/>
	  
  	<acme:form-return code="authenticated.inquire.button.return"/>
</acme:form>
