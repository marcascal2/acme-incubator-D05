
package acme.entities.dashboard;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						totalNumberOfNotices;
	Integer						totalNumberOfTechnologyRecords;
	Integer						totalNumberOfToolRecords;

	Double						minimumOfMoneyIntervalsOfActiveInquiriesMin;
	Double						maximumOfMoneyIntervalsOfActiveInquiriesMin;
	Double						averageOfMoneyIntervalsOfActiveInquiriesMin;
	Double						desviationOfMoneyIntervalsOfActiveInquiriesMin;

	Double						minimumOfMoneyIntervalsOfActiveOverturesMin;
	Double						maximumOfMoneyIntervalsOfActiveOverturesMin;
	Double						averageOfMoneyIntervalsOfActiveOverturesMin;
	Double						desviationOfMoneyIntervalsOfActiveOverturesMin;

	Double						minimumOfMoneyIntervalsOfActiveInquiriesMax;
	Double						maximumOfMoneyIntervalsOfActiveInquiriesMax;
	Double						averageOfMoneyIntervalsOfActiveInquiriesMax;
	Double						desviationOfMoneyIntervalsOfActiveInquiriesMax;

	Double						minimumOfMoneyIntervalsOfActiveOverturesMax;
	Double						maximumOfMoneyIntervalsOfActiveOverturesMax;
	Double						averageOfMoneyIntervalsOfActiveOverturesMax;
	Double						desviationOfMoneyIntervalsOfActiveOverturesMax;

	Double						ratioOfOpenSourceTechnologies;
	Double						ratioOfClosedSourceTechnologies;

	Double						ratioOfOpenSourceTools;
	Double						ratioOfClosedSourceTools;

	Double						ratioOfSeedInvestmentRounds;
	Double						ratioOfAngelInvestmentRounds;
	Double						ratioOfSeriesAInvestmentRounds;
	Double						ratioOfSeriesBInvestmentRounds;
	Double						ratioOfSeriesCInvestmentRounds;
	Double						ratioOfBridgeInvestmentRounds;

	Double						ratioOfPendingApplications;
	Double						ratioOfAcceptedApplications;
	Double						ratioOfRejectedApplications;

	List<String[]>				technologyPerSector;
	List<String[]>				toolPerSector;

	Double						averageInvestmentRoundPerEntrepreneur;
	Double						averageApplicationPerEntrepreneur;
	Double						averageApplicationPerInvestor;

	List<Integer>				numberOfPendingApplicationsPerDay;
	List<Integer>				numberOfAcceptedApplicationsPerDay;
	List<Integer>				numberOfRejectedApplicationsPerDay;
}
