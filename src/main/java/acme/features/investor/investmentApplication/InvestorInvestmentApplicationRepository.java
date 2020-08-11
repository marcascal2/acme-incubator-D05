/*
 * AuthenticatedUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.investor.investmentApplication;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorInvestmentApplicationRepository extends AbstractRepository {

	@Query("select o from InvestmentApplication o where o.id = ?1")
	InvestmentApplication findOneById(int id);

	@Query("select a from InvestmentApplication a where a.investor.id = ?1")
	Collection<InvestmentApplication> findManyByInvestorId(int jobId);

	@Query("select e from Investor e where e.id = ?1")
	Investor findInvestorById(int accountId);

	@Query("select e from InvestmentRound e where e.id = ?1")
	InvestmentRound findInvestmentRoundById(int invId);

	@Query("select e from UserAccount e where e.id = ?1")
	UserAccount findOneUserAccountById(int idUA);
}
