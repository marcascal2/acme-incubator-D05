
package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dashboard.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	private AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer totalNumberOfNotices = this.repository.totalNumberOfNotices();
		Integer totalNumberOfTechnologyRecords = this.repository.totalNumberOfTechnologyRecords();
		Integer totalNumberOfToolRecords = this.repository.totalNumberOfToolRecords();
		Double minimumOfMoneyIntervalsOfActiveInquiriesMin = this.repository.minimumOfMoneyIntervalsOfActiveInquiriesMin();
		Double maximumOfMoneyIntervalsOfActiveInquiriesMin = this.repository.maximumOfMoneyIntervalsOfActiveInquiriesMin();
		Double averageOfMoneyIntervalsOfActiveInquiriesMin = this.repository.averageOfMoneyIntervalsOfActiveInquiriesMin();
		Double desviationOfMoneyIntervalsOfActiveInquiriesMin = this.repository.desviationOfMoneyIntervalsOfActiveInquiriesMin();

		Double minimumOfMoneyIntervalsOfActiveOverturesMin = this.repository.minimumOfMoneyIntervalsOfActiveOverturesMin();
		Double maximumOfMoneyIntervalsOfActiveOverturesMin = this.repository.maximumOfMoneyIntervalsOfActiveOverturesMin();
		Double averageOfMoneyIntervalsOfActiveOverturesMin = this.repository.averageOfMoneyIntervalsOfActiveOverturesMin();
		Double desviationOfMoneyIntervalsOfActiveOverturesMin = this.repository.desviationOfMoneyIntervalsOfActiveOverturesMin();

		Double minimumOfMoneyIntervalsOfActiveInquiriesMax = this.repository.minimumOfMoneyIntervalsOfActiveInquiriesMax();
		Double maximumOfMoneyIntervalsOfActiveInquiriesMax = this.repository.maximumOfMoneyIntervalsOfActiveInquiriesMax();
		Double averageOfMoneyIntervalsOfActiveInquiriesMax = this.repository.averageOfMoneyIntervalsOfActiveInquiriesMax();
		Double desviationOfMoneyIntervalsOfActiveInquiriesMax = this.repository.desviationOfMoneyIntervalsOfActiveInquiriesMax();

		Double minimumOfMoneyIntervalsOfActiveOverturesMax = this.repository.minimumOfMoneyIntervalsOfActiveOverturesMax();
		Double maximumOfMoneyIntervalsOfActiveOverturesMax = this.repository.maximumOfMoneyIntervalsOfActiveOverturesMax();
		Double averageOfMoneyIntervalsOfActiveOverturesMax = this.repository.averageOfMoneyIntervalsOfActiveOverturesMax();
		Double desviationOfMoneyIntervalsOfActiveOverturesMax = this.repository.desviationOfMoneyIntervalsOfActiveOverturesMax();
    
		Double averageInvestmentRoundPerEntrepreneur = this.repository.averageInvestmentRoundPerEntrepreneur();
		Double averageApplicationPerEntrepreneur = this.repository.averageApplicationPerEntrepreneur();
		Double averageApplicationPerInvestor = this.repository.averageApplicationPerInvestor();
    
    request.unbind(entity, model, "technologyPerSector", "toolPerSector", "ratioOfClosedSourceTools", "ratioOfOpenSourceTools", "ratioOfClosedSourceTechnologies", "ratioOfOpenSourceTechnologies", "ratioOfSeedInvestmentRounds",
			"ratioOfAngelInvestmentRounds", "ratioOfSeriesAInvestmentRounds", "ratioOfSeriesBInvestmentRounds", "ratioOfSeriesCInvestmentRounds", "ratioOfBridgeInvestmentRounds", "ratioOfPendingApplications", "ratioOfAcceptedApplications",
			"ratioOfRejectedApplications");

		model.setAttribute("totalNumberOfNotices", totalNumberOfNotices);
		model.setAttribute("totalNumberOfTechnologyRecords", totalNumberOfTechnologyRecords);
		model.setAttribute("totalNumberOfToolRecords", totalNumberOfToolRecords);

		model.setAttribute("minimumOfMoneyIntervalsOfActiveInquiriesMin", minimumOfMoneyIntervalsOfActiveInquiriesMin);
		model.setAttribute("maximumOfMoneyIntervalsOfActiveInquiriesMin", maximumOfMoneyIntervalsOfActiveInquiriesMin);
		model.setAttribute("averageOfMoneyIntervalsOfActiveInquiriesMin", averageOfMoneyIntervalsOfActiveInquiriesMin);
		model.setAttribute("desviationOfMoneyIntervalsOfActiveInquiriesMin", desviationOfMoneyIntervalsOfActiveInquiriesMin);

		model.setAttribute("minimumOfMoneyIntervalsOfActiveOverturesMin", minimumOfMoneyIntervalsOfActiveOverturesMin);
		model.setAttribute("maximumOfMoneyIntervalsOfActiveOverturesMin", maximumOfMoneyIntervalsOfActiveOverturesMin);
		model.setAttribute("averageOfMoneyIntervalsOfActiveOverturesMin", averageOfMoneyIntervalsOfActiveOverturesMin);
		model.setAttribute("desviationOfMoneyIntervalsOfActiveOverturesMin", desviationOfMoneyIntervalsOfActiveOverturesMin);

		model.setAttribute("minimumOfMoneyIntervalsOfActiveInquiriesMax", minimumOfMoneyIntervalsOfActiveInquiriesMax);
		model.setAttribute("maximumOfMoneyIntervalsOfActiveInquiriesMax", maximumOfMoneyIntervalsOfActiveInquiriesMax);
		model.setAttribute("averageOfMoneyIntervalsOfActiveInquiriesMax", averageOfMoneyIntervalsOfActiveInquiriesMax);
		model.setAttribute("desviationOfMoneyIntervalsOfActiveInquiriesMax", desviationOfMoneyIntervalsOfActiveInquiriesMax);

		model.setAttribute("minimumOfMoneyIntervalsOfActiveOverturesMax", minimumOfMoneyIntervalsOfActiveOverturesMax);
		model.setAttribute("maximumOfMoneyIntervalsOfActiveOverturesMax", maximumOfMoneyIntervalsOfActiveOverturesMax);
		model.setAttribute("averageOfMoneyIntervalsOfActiveOverturesMax", averageOfMoneyIntervalsOfActiveOverturesMax);
		model.setAttribute("desviationOfMoneyIntervalsOfActiveOverturesMax", desviationOfMoneyIntervalsOfActiveOverturesMax);

		model.setAttribute("averageInvestmentRoundPerEntrepreneur", averageInvestmentRoundPerEntrepreneur);
		model.setAttribute("averageApplicationPerEntrepreneur", averageApplicationPerEntrepreneur);
		model.setAttribute("averageApplicationPerInvestor", averageApplicationPerInvestor);
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		Double ratioOfClosedSourceTechnologies = this.repository.ratioOfClosedSourceTechnologies();
		Double ratioOfClosedSourceTools = this.repository.ratioOfClosedSourceTools();
		Double ratioOfOpenSourceTechnologies = this.repository.ratioOfOpenSourceTechnologies();
		Double ratioOfOpenSourceTools = this.repository.ratioOfOpenSourceTools();

		Double ratioOfSeedInvestmentRounds = this.repository.ratioOfSeedInvestmentRounds();
		Double ratioOfAngelInvestmentRounds = this.repository.ratioOfAngelInvestmentRounds();
		Double ratioOfSeriesAInvestmentRounds = this.repository.ratioOfSeriesAInvestmentRounds();
		Double ratioOfSeriesBInvestmentRounds = this.repository.ratioOfSeriesBInvestmentRounds();
		Double ratioOfSeriesCInvestmentRounds = this.repository.ratioOfSeriesCInvestmentRounds();
		Double ratioOfBridgeInvestmentRounds = this.repository.ratioOfBridgeInvestmentRounds();

		Double ratioOfPendingApplications = this.repository.ratioOfPendingApplications();
		Double ratioOfAcceptedApplications = this.repository.ratioOfAcceptedApplications();
		Double ratioOfRejectedApplications = this.repository.ratioOfRejectedApplications();

		result.setRatioOfClosedSourceTechnologies(ratioOfClosedSourceTechnologies);
		result.setRatioOfClosedSourceTools(ratioOfClosedSourceTools);
		result.setRatioOfOpenSourceTechnologies(ratioOfOpenSourceTechnologies);
		result.setRatioOfOpenSourceTools(ratioOfOpenSourceTools);

		result.setRatioOfSeedInvestmentRounds(ratioOfSeedInvestmentRounds);
		result.setRatioOfAngelInvestmentRounds(ratioOfAngelInvestmentRounds);
		result.setRatioOfSeriesAInvestmentRounds(ratioOfSeriesAInvestmentRounds);
		result.setRatioOfSeriesBInvestmentRounds(ratioOfSeriesBInvestmentRounds);
		result.setRatioOfSeriesCInvestmentRounds(ratioOfSeriesCInvestmentRounds);
		result.setRatioOfBridgeInvestmentRounds(ratioOfBridgeInvestmentRounds);

		result.setRatioOfAcceptedApplications(ratioOfAcceptedApplications);
		result.setRatioOfPendingApplications(ratioOfPendingApplications);
		result.setRatioOfRejectedApplications(ratioOfRejectedApplications);

		List<String[]> toolPerSector = this.repository.toolPerSector();

		List<String[]> techPerSector = this.repository.technologyPerSector();

		result.setTechnologyPerSector(techPerSector);
		result.setToolPerSector(toolPerSector);

		return result;
	}
}
