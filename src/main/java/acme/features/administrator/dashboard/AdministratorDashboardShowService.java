
package acme.features.administrator.dashboard;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

		Date limit_date = Date.valueOf(LocalDate.now().minusDays(15));
		List<LocalDate> dates = this.getDates(limit_date);
		List<String> labels = dates.stream().map(d -> d.toString()).collect(Collectors.toList());

		model.setAttribute("labels", labels);

		request.unbind(entity, model, "numberOfPendingApplicationsPerDay", "numberOfAcceptedApplicationsPerDay", "numberOfRejectedApplicationsPerDay");

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

		Date limit_date = Date.valueOf(LocalDate.now().minusDays(15));

		List<String[]> pend = this.repository.numberOfPendingApplicationsPerDay(limit_date);
		List<String[]> accep = this.repository.numberOfAcceptedApplicationsPerDay(limit_date);
		List<String[]> rejec = this.repository.numberOfRejectedApplicationsPerDay(limit_date);

		List<LocalDate> dates = this.getDates(limit_date);

		result.setNumberOfPendingApplicationsPerDay(this.getDataByDate(pend, dates));
		result.setNumberOfAcceptedApplicationsPerDay(this.getDataByDate(accep, dates));
		result.setNumberOfRejectedApplicationsPerDay(this.getDataByDate(rejec, dates));

		return result;
	}

	private List<Integer> getDataByDate(final List<String[]> list, final List<LocalDate> dates) {
		Map<LocalDate, Integer> lp = new HashMap<LocalDate, Integer>();
		List<String[]> l = list;
		for (LocalDate ld1 : dates) {
			lp.put(ld1, 0);
		}

		for (int i = 0; i < list.size(); i++) {
			LocalDate ld2 = LocalDate.parse(list.get(i)[0].substring(0, 10));
			if (lp.containsKey(ld2)) {
				lp.replace(ld2, 0, Integer.parseInt(l.get(i)[1]));
			}
		}

		TreeMap<LocalDate, Integer> lpo = new TreeMap<>(lp);
		List<Integer> result = lpo.values().stream().collect(Collectors.toList());
		return result;
	}

	private List<LocalDate> getDates(final Date limit_date) {
		Date now = Date.valueOf(LocalDate.now());
		long numberOfDaysBetween = (now.getTime() - limit_date.getTime()) / (1000 * 60 * 60 * 24);
		List<LocalDate> dates = IntStream.iterate(0, i -> i + 1).limit(numberOfDaysBetween).mapToObj(i -> limit_date.toLocalDate().plusDays(i)).collect(Collectors.toList());
		return dates;
	}

}
