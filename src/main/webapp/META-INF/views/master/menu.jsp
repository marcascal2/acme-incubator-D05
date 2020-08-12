<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link1" action="http://www.lsi.us.es" />
			<acme:menu-suboption code="master.menu.anonymous.coleto-favourite-link" action="http://www.twitch.com/" />
			<acme:menu-suboption code="master.menu.anonymous.Maria-favourite-link" action="http://www.youtube.com/" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.javruimunbulletin-list" action="/anonymous/ruizbulletin/list" />
			<acme:menu-suboption code="master.menu.anonymous.javruimunbulletin-create" action="/anonymous/ruizbulletin/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.casasola-bulletin.list" action="/anonymous/casasola-bulletin/list" />
			<acme:menu-suboption code="master.menu.anonymous.casasola-bulletin.create" action="/anonymous/casasola-bulletin/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.coleto-bulletin.list" action="/anonymous/coleto-bulletin/list" />
			<acme:menu-suboption code="master.menu.anonymous.coleto-bulletin.create" action="/anonymous/coleto-bulletin/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.notice.list" action="/anonymous/notice/list" />
			<acme:menu-suboption code="master.menu.anonymous.technologyrecords.list" action="/anonymous/technology-record/list" />
			<acme:menu-suboption code="master.menu.anonymous.tool-record.list" action="/anonymous/tool-record/list" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.overture" action="/authenticated/overture/list" />
			<acme:menu-suboption code="master.menu.authenticated.inquire" action="/authenticated/inquire/list" />
			<acme:menu-suboption code="master.menu.authenticated.challenge" action="/authenticated/challenge/list" />
			<acme:menu-suboption code="master.menu.authenticated.investmentRound" action="/authenticated/investment-round/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.notice.list" action="/authenticated/notice/list" />
			<acme:menu-suboption code="master.menu.authenticated.technologyrecords.list" action="/authenticated/technology-record/list" />
			<acme:menu-suboption code="master.menu.authenticated.tool-record.list" action="/authenticated/tool-record/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-suboption code="master.menu.administrator.spam-words" action="/administrator/spam-list/list" />
			<acme:menu-suboption code="master.menu.administrator.activity-sectors" action="/administrator/activity-sector/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.notices" action="/administrator/notice/list" />
			<acme:menu-suboption code="master.menu.administrator.inquires" action="/administrator/inquire/list" />
			<acme:menu-suboption code="master.menu.administrator.challenges" action="/administrator/challenge/list" />
			<acme:menu-suboption code="master.menu.administrator.technologyrecords.list" action="/administrator/technology-record/list" />
			<acme:menu-suboption code="master.menu.administrator.banners" action="/administrator/banner/list" />
			<acme:menu-suboption code="master.menu.administrator.overtures" action="/administrator/overture/list" />
			<acme:menu-suboption code="master.menu.administrator.toolrecords" action="/administrator/tool-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.inquires.create" action="/administrator/inquire/create" />
			<acme:menu-suboption code="master.menu.administrator.challenges.create" action="/administrator/challenge/create" />
			<acme:menu-suboption code="master.menu.administrator.technologyrecords.create" action="/administrator/technology-record/create" />
			<acme:menu-suboption code="master.menu.administrator.notice.create" action="/administrator/notice/create" />
			<acme:menu-suboption code="master.menu.administrator.overtures.create" action="/administrator/overture/create" />
			<acme:menu-suboption code="master.menu.administrator.banners.create" action="/administrator/banner/create" />
			<acme:menu-suboption code="master.menu.administrator.toolrecord.create" action="/administrator/tool-record/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.bookkeeper-request" action="/administrator/bookkeeper-request/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.investment-applications" action="/investor/investment-application/list_mine" />
			<acme:menu-suboption code="master.menu.investor.investment-round" action="/investor/investment-round/list_mine" />
			<acme:menu-suboption code="master.menu.authenticated.discussionForum" action="/authenticated/discussion-forum/list_mine" />
		</acme:menu-option>
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.applications" action="/entrepreneur/investment-application/list_mine" />
			<acme:menu-suboption code="master.menu.entrepreneur.investment-rounds" action="/entrepreneur/investment-round/list_mine" />
			<acme:menu-suboption code="master.menu.authenticated.discussionForum" action="/authenticated/discussion-forum/list_mine" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round.create" action="/entrepreneur/investment-round/create" />
		</acme:menu-option>
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.banner" action="/patron/banner/list" />
			<acme:menu-suboption code="master.menu.patron.banner.create" action="/patron/banner/create" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.bookkeeper" access="hasRole('Bookkeeper')">
			<acme:menu-suboption code="master.menu.bookkeeper.investment-rounds" action="/bookkeeper/investment-round/list_mine" />
			<acme:menu-suboption code="master.menu.bookkeeper.investment-rounds-not-mine" action="/bookkeeper/investment-round-not-mine/list" />
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create"
				access="!hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update"
				access="hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create"
				access="!hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update"
				access="hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create"
				access="!hasRole('Investor')" />
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update"
				access="hasRole('Investor')" />
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create"
				access="!hasRole('Entrepreneur')" />
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update"
				access="hasRole('Entrepreneur')" />
			<acme:menu-suboption code="master.menu.user-account.become-bookkeeper" action="/authenticated/bookkeeper-request/create"
				access="!hasRole('Bookkeeper')" />
							<acme:menu-suboption code="master.menu.user-account.bookkeeper" action="/authenticated/bookkeeper-request/update"
				access="hasRole('Bookkeeper')" />
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create"
				access="!hasRole('Patron')" />
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update"
				access="hasRole('Patron')" />
<%-- 			<acme:menu-suboption code="master.menu.user-account.bookkeeper" action="/authenticated/bookkeeper-request/create" --%>
<%-- 				access="hasRole('Bookkeeper')" /> --%>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>

