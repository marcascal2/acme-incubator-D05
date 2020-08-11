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

package acme.features.investor.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorActivityRepository extends AbstractRepository {

	@Query("select o from Activity o where o.id = ?1")
	Activity findOneById(int id);

	@Query("select i.workProgramme from InvestmentRound i where i.id = ?1")
	Collection<Activity> findManyByInvestmentId(int invId);

	@Query("select e from Investor e where e.id = ?1")
	Investor findInvestorById(int accountId);

	@Query("select o from InvestmentRound o where o.id = ?1")
	InvestmentRound findInvestmentRound(int roundId);
}
