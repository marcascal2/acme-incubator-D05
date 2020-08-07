
package acme.features.entrepreneur.investmentApplication;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentApplicationRepository extends AbstractRepository {

	@Query("select a from InvestmentApplication a where a.investmentApplied.entrepreneur.id = ?1")
	Collection<InvestmentApplication> findManyByEntrepeneurId(int activeRoleId);

	@Query("select o from InvestmentApplication o where o.id = ?1")
	InvestmentApplication findOneById(int id);

	@Query("select a from Entrepreneur a where a.id = ?1")
	Entrepreneur findEntrepreneurBy(int entrepreneurId);

}
