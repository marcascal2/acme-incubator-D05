
package acme.features.administrator.dashboard;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select 1.0 * count(a) / (select count(b) from TechnologyRecord b) from TechnologyRecord a where a.openSource = True")
	Double ratioOfOpenSourceTechnologies();

	@Query("select 1.0 * count(a) / (select count(b) from TechnologyRecord b) from TechnologyRecord a where a.openSource = False")
	Double ratioOfClosedSourceTechnologies();

	@Query("select 1.0 * count(a) / (select count(b) from ToolRecord b) from ToolRecord a where a.openSource = True")
	Double ratioOfOpenSourceTools();

	@Query("select 1.0 * count(a) / (select count(b) from ToolRecord b) from ToolRecord a where a.openSource = False")
	Double ratioOfClosedSourceTools();

	@Query("select c.activitySector.sector, count(c) from TechnologyRecord c group by c.activitySector.sector")
	List<String[]> technologyPerSector();

	@Query("select c.activitySector.sector, count(c) from ToolRecord c group by c.activitySector.sector")
	List<String[]> toolPerSector();

	@Query("select count(*) from Notice")
	Integer totalNumberOfNotices();

	@Query("select count(*) from TechnologyRecord")
	Integer totalNumberOfTechnologyRecords();

	@Query("select count(*) from ToolRecord")
	Integer totalNumberOfToolRecords();

	@Query("select AVG(n.minMoney.amount) from Inquire n where n.deadline >= now()")
	Double averageOfMoneyIntervalsOfActiveInquiriesMin();

	@Query("select MAX(n.minMoney.amount) from Inquire n where n.deadline >= now()")
	Double maximumOfMoneyIntervalsOfActiveInquiriesMin();

	@Query("select MIN(n.minMoney.amount) from Inquire n where n.deadline >= now()")
	Double minimumOfMoneyIntervalsOfActiveInquiriesMin();

	@Query("select STDDEV(n.minMoney.amount) from Inquire n where n.deadline >= now()")
	Double desviationOfMoneyIntervalsOfActiveInquiriesMin();

	@Query("select AVG(n.minMoney.amount) from Overture n where n.deadline >= now()")
	Double averageOfMoneyIntervalsOfActiveOverturesMin();

	@Query("select MAX(n.minMoney.amount) from Overture n where n.deadline >= now()")
	Double maximumOfMoneyIntervalsOfActiveOverturesMin();

	@Query("select MIN(n.minMoney.amount) from Overture n where n.deadline >= now()")
	Double minimumOfMoneyIntervalsOfActiveOverturesMin();

	@Query("select STDDEV(n.minMoney.amount) from Overture n where n.deadline >= now()")
	Double desviationOfMoneyIntervalsOfActiveOverturesMin();

	//

	@Query("select AVG(n.maxMoney.amount) from Inquire n where n.deadline >= now()")
	Double averageOfMoneyIntervalsOfActiveInquiriesMax();

	@Query("select MAX(n.maxMoney.amount) from Inquire n where n.deadline >= now()")
	Double maximumOfMoneyIntervalsOfActiveInquiriesMax();

	@Query("select MIN(n.maxMoney.amount) from Inquire n where n.deadline >= now()")
	Double minimumOfMoneyIntervalsOfActiveInquiriesMax();

	@Query("select STDDEV(n.maxMoney.amount) from Inquire n where n.deadline >= now()")
	Double desviationOfMoneyIntervalsOfActiveInquiriesMax();

	@Query("select AVG(n.maxMoney.amount) from Overture n where n.deadline >= now()")
	Double averageOfMoneyIntervalsOfActiveOverturesMax();

	@Query("select MAX(n.maxMoney.amount) from Overture n where n.deadline >= now()")
	Double maximumOfMoneyIntervalsOfActiveOverturesMax();

	@Query("select MIN(n.maxMoney.amount) from Overture n where n.deadline >= now()")
	Double minimumOfMoneyIntervalsOfActiveOverturesMax();

	@Query("select STDDEV(n.maxMoney.amount) from Overture n where n.deadline >= now()")
	Double desviationOfMoneyIntervalsOfActiveOverturesMax();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentApplication b) from InvestmentApplication a where a.status = acme.entities.investmentApplications.ApplicationStatus.PENDING")
	Double ratioOfPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentApplication b) from InvestmentApplication a where a.status = acme.entities.investmentApplications.ApplicationStatus.ACCEPTED")
	Double ratioOfAcceptedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentApplication b) from InvestmentApplication a where a.status = acme.entities.investmentApplications.ApplicationStatus.REJECTED")
	Double ratioOfRejectedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = acme.entities.investmentRounds.KindOfRound.SEED")
	Double ratioOfSeedInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = acme.entities.investmentRounds.KindOfRound.ANGEL")
	Double ratioOfAngelInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = acme.entities.investmentRounds.KindOfRound.SERIES_A")
	Double ratioOfSeriesAInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = acme.entities.investmentRounds.KindOfRound.SERIES_B")
	Double ratioOfSeriesBInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = acme.entities.investmentRounds.KindOfRound.SERIES_C")
	Double ratioOfSeriesCInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = acme.entities.investmentRounds.KindOfRound.BRIDGE")
	Double ratioOfBridgeInvestmentRounds();

	@Query("select AVG(select count(j) from InvestmentRound j where j.entrepreneur.id = e.id) from Entrepreneur e")
	Double averageInvestmentRoundPerEntrepreneur();

	@Query("select AVG(select count(a) from InvestmentApplication a where exists(select j from InvestmentRound j where j.entrepreneur.id = e.id and a.investmentApplied.id = j.id)) from Entrepreneur e")
	Double averageApplicationPerEntrepreneur();

	@Query("select AVG(select count(a) from InvestmentApplication a where a.investor.id = w.id) from Investor w")
	Double averageApplicationPerInvestor();

	@Query("select a.creationMoment, count(a) from InvestmentApplication a where a.status = acme.entities.investmentApplications.ApplicationStatus.PENDING and a.creationMoment >= ?1 group by a.creationMoment")
	List<String[]> numberOfPendingApplicationsPerDay(Date limit_date);

	@Query("select a.creationMoment, count(a) from InvestmentApplication a where a.status = acme.entities.investmentApplications.ApplicationStatus.ACCEPTED and a.creationMoment >= ?1 group by a.creationMoment")
	List<String[]> numberOfAcceptedApplicationsPerDay(Date limit_date);

	@Query("select a.creationMoment, count(a) from InvestmentApplication a where a.status = acme.entities.investmentApplications.ApplicationStatus.REJECTED and a.creationMoment >= ?1 group by a.creationMoment")
	List<String[]> numberOfRejectedApplicationsPerDay(Date limit_date);

}
