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
	<acme:form-double code="administrator.dashboard.form.label.totalNumberOfNotices" path="totalNumberOfNotices"/>
	<acme:form-double code="administrator.dashboard.form.label.totalNumberOfTechnologyRecords" path="totalNumberOfTechnologyRecords"/>
	<acme:form-double code="administrator.dashboard.form.label.totalNumberOfToolRecords" path="totalNumberOfToolRecords"/>
	
	<acme:form-double code="administrator.dashboard.form.label.minimumOfMoneyIntervalsOfActiveInquiriesMin" path="minimumOfMoneyIntervalsOfActiveInquiriesMin"/>
	<acme:form-double code="administrator.dashboard.form.label.minimumOfMoneyIntervalsOfActiveInquiriesMax" path="minimumOfMoneyIntervalsOfActiveInquiriesMax"/>
	
	<acme:form-double code="administrator.dashboard.form.label.maximumOfMoneyIntervalsOfActiveInquiriesMin" path="maximumOfMoneyIntervalsOfActiveInquiriesMin"/>
	<acme:form-double code="administrator.dashboard.form.label.maximumOfMoneyIntervalsOfActiveInquiriesMax" path="maximumOfMoneyIntervalsOfActiveInquiriesMax"/>
	
	<acme:form-double code="administrator.dashboard.form.label.averageOfMoneyIntervalsOfActiveInquiriesMin" path="averageOfMoneyIntervalsOfActiveInquiriesMin"/>
	<acme:form-double code="administrator.dashboard.form.label.averageOfMoneyIntervalsOfActiveInquiriesMax" path="averageOfMoneyIntervalsOfActiveInquiriesMax"/>
	
	<acme:form-double code="administrator.dashboard.form.label.desviationOfMoneyIntervalsOfActiveInquiriesMin" path="desviationOfMoneyIntervalsOfActiveInquiriesMin"/>
	<acme:form-double code="administrator.dashboard.form.label.desviationOfMoneyIntervalsOfActiveInquiriesMax" path="desviationOfMoneyIntervalsOfActiveInquiriesMax"/>
	
	
	<acme:form-double code="administrator.dashboard.form.label.minimumOfMoneyIntervalsOfActiveOverturesMin" path="minimumOfMoneyIntervalsOfActiveOverturesMin"/>
	<acme:form-double code="administrator.dashboard.form.label.minimumOfMoneyIntervalsOfActiveOverturesMax" path="minimumOfMoneyIntervalsOfActiveOverturesMax"/>
	
	<acme:form-double code="administrator.dashboard.form.label.maximumOfMoneyIntervalsOfActiveOverturesMin" path="maximumOfMoneyIntervalsOfActiveOverturesMin"/>
	<acme:form-double code="administrator.dashboard.form.label.maximumOfMoneyIntervalsOfActiveOverturesMax" path="maximumOfMoneyIntervalsOfActiveOverturesMax"/>
	
	<acme:form-double code="administrator.dashboard.form.label.averageOfMoneyIntervalsOfActiveOverturesMin" path="averageOfMoneyIntervalsOfActiveOverturesMin"/>
	<acme:form-double code="administrator.dashboard.form.label.averageOfMoneyIntervalsOfActiveOverturesMax" path="averageOfMoneyIntervalsOfActiveOverturesMax"/>
	
	<acme:form-double code="administrator.dashboard.form.label.desviationOfMoneyIntervalsOfActiveOverturesMin" path="desviationOfMoneyIntervalsOfActiveOverturesMin"/>
	<acme:form-double code="administrator.dashboard.form.label.desviationOfMoneyIntervalsOfActiveOverturesMax" path="desviationOfMoneyIntervalsOfActiveOverturesMax"/>
	
	<acme:form-double code="administrator.dashboard.form.label.averageInvestmentRoundPerEntrepreneur" path="averageInvestmentRoundPerEntrepreneur"/>
	<acme:form-double code="administrator.dashboard.form.label.averageApplicationPerEntrepreneur" path="averageApplicationPerEntrepreneur"/>
	<acme:form-double code="administrator.dashboard.form.label.averageApplicationPerInvestor" path="averageApplicationPerInvestor"/>
	
</acme:form>
<br>
<br>

<h3>
	<acme:message code="administrator.dashboard.form.label.techPerSector" />
</h3>

<canvas id="techPerSector" width="400" height="400"></canvas>
<script>
var data = {
	labels: [
		<jstl:forEach var="sector" items="${technologyPerSector}">
	    	"<jstl:out value="${sector[0]}"/>",
		</jstl:forEach>
	],
	datasets: [
		{
			data: [
				<jstl:forEach var="sector" items="${technologyPerSector}">
		    		"<jstl:out value="${sector[1]}"/>",
				</jstl:forEach>
			]
		}	
	]
};
var options = {
	scales: {
		yAxes: [
			{
				ticks: {
					suggestedMin: 0.0,
					suggestedMax: 5.0
				}
			}
		]
	},
	legend: {
		display: false
	}
};
var context = document.getElementById('techPerSector').getContext('2d');
new Chart(context, {
    type: 'bar',
    data: data,
    options: options
});
</script>

<h3>
	<acme:message code="administrator.dashboard.form.label.techComparation" />
</h3>

<canvas id="techComparation" width="400" height="400"></canvas>
<script>
var data = {
		labels: [
		    	"Open-source",
		    	"Closed-source",
		],
		datasets: [
			{
				data: [
					<jstl:out value="${ratioOfOpenSourceTechnologies}"/>,
					<jstl:out value="${ratioOfClosedSourceTechnologies}"/>,
				]
			}	
		]
	};
var options = {
	scales: {
		yAxes: [
			{
				ticks: {
					suggestedMin: 0.0,
					suggestedMax: 1.0
				}
			}
		]
	},
	legend: {
		display: false
	}
};
var context = document.getElementById('techComparation').getContext('2d');
new Chart(context, {
    type: 'bar',
    data: data,
    options: options
});
</script>


<h3>
	<acme:message code="administrator.dashboard.form.label.toolsPerSector" />
</h3>

<canvas id="toolsPerSector" width="400" height="400"></canvas>
<script>
var data = {
	labels: [
		<jstl:forEach var="sector" items="${toolPerSector}">
	    	"<jstl:out value="${sector[0]}"/>",
		</jstl:forEach>
	],
	datasets: [
		{
			data: [
				<jstl:forEach var="sector" items="${toolPerSector}">
		    		"<jstl:out value="${sector[1]}"/>",
				</jstl:forEach>
			]
		}	
	]
};
var options = {
	scales: {
		yAxes: [
			{
				ticks: {
					suggestedMin: 0.0,
					suggestedMax: 5.0
				}
			}
		]
	},
	legend: {
		display: false
	}
};
var context = document.getElementById('toolsPerSector').getContext('2d');
new Chart(context, {
    type: 'bar',
    data: data,
    options: options
});
</script>

<h3>
	<acme:message code="administrator.dashboard.form.label.toolsComparation" />
</h3>

<canvas id="toolsComparation" width="400" height="400"></canvas>
<script>
var data = {
	labels: [
	    	"Open-source",
	    	"Closed-source",
	],
	datasets: [
		{
			data: [
				<jstl:out value="${ratioOfOpenSourceTools}"/>,
				<jstl:out value="${ratioOfClosedSourceTools}"/>,
			]
		}	
	]
};
var options = {
	scales: {
		yAxes: [
			{
				ticks: {
					suggestedMin: 0.0,
					suggestedMax: 1.0
				}
			}
		]
	},
	legend: {
		display: false
	}
};
var context = document.getElementById('toolsComparation').getContext('2d');
new Chart(context, {
    type: 'bar',
    data: data,
    options: options
});
</script>

<h3>
	<acme:message code="administrator.dashboard.form.label.investmentRounds" />
</h3>

<canvas id="investmentRoundsComparation" width="400" height="400"></canvas>
<script>
var data = {
	labels: [
	    	"Seed kind",
	    	"Angel kind",
	    	"Series A kind",
	    	"Series B kind",
	    	"Series C kind",
	    	"Bridge kind",
	],
	datasets: [
		{
			data: [
				<jstl:out value="${ratioOfSeedInvestmentRounds}"/>,
				<jstl:out value="${ratioOfAngelInvestmentRounds}"/>,
				<jstl:out value="${ratioOfSeriesAInvestmentRounds}"/>,
				<jstl:out value="${ratioOfSeriesBInvestmentRounds}"/>,
				<jstl:out value="${ratioOfSeriesCInvestmentRounds}"/>,
				<jstl:out value="${ratioOfBridgeInvestmentRounds}"/>,
			]
		}	
	]
};
var options = {
	scales: {
		yAxes: [
			{
				ticks: {
					suggestedMin: 0.0,
					suggestedMax: 1.0
				}
			}
		]
	},
	legend: {
		display: false
	}
};
var context = document.getElementById('investmentRoundsComparation').getContext('2d');
new Chart(context, {
    type: 'bar',
    data: data,
    options: options
});
</script>

<h3>
	<acme:message code="administrator.dashboard.form.label.applications" />
</h3>

<canvas id="applicationsComparation" width="400" height="400"></canvas>
<script>
var data = {
	labels: [
	    	"Accepted applications",
	    	"Pending applications",
	    	"Rejected applications",
	],
	datasets: [
		{
			data: [
				<jstl:out value="${ratioOfAcceptedApplications}"/>,
				<jstl:out value="${ratioOfPendingApplications}"/>,
				<jstl:out value="${ratioOfRejectedApplications}"/>,
			]
		}	
	]
};
var options = {
	scales: {
		yAxes: [
			{
				ticks: {
					suggestedMin: 0.0,
					suggestedMax: 1.0
				}
			}
		]
	},
	legend: {
		display: false
	}
};
var context = document.getElementById('applicationsComparation').getContext('2d');
new Chart(context, {
    type: 'bar',
    data: data,
    options: options
});
</script>
<br>
<br>
<acme:message code="administrator.dashboard.form.title.applicationsInLast15Days" />

<div>
	<canvas id="canvas5"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: [
					<jstl:forEach var="label" items="${labels2}">
					<jstl:out value="${label}" escapeXml="false"/>,
					</jstl:forEach>
				],
				datasets : [
					{
						label: "Rejected",
						data: [
							<jstl:forEach var = "rejected" items = "${numberOfRejectedApplications}">
								<jstl:out value="${rejected}" escapeXml="false"/>,
							</jstl:forEach>
						],backgroundColor: ["#FF8C00"]	
					},
					{
						label: "Accepted",
						data: [
							<jstl:forEach var = "accepted" items = "${numberOfAcceptedApplications}">
								<jstl:out value="${accepted}" escapeXml="false"/>,
							</jstl:forEach>
						],backgroundColor: ["#7FFF00"]
					},
					{
						label: "Pending",
						data: [
							<jstl:forEach var = "pending" items = "${numberOfPendingApplications}">
								<jstl:out value="${pending}" escapeXml="false"/>,
							</jstl:forEach>
						],backgroundColor: ["#00FFFF"]	
					}
				]		
		};
		
		var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 10.0
							}
						}
					]
				},
				legend : {
					display : true
				}
		};
		
		var canvas, context;
		
		canvas = document.getElementById("canvas5");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "line",
			data : data,
			options : options
		});
	});
</script>

<acme:form-return code="administrator.dashboard.form.button.return"/>


