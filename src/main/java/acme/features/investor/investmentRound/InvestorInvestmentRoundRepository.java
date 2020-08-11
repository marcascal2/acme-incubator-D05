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

package acme.features.investor.investmentRound;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.entities.spamWords.SpamList;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorInvestmentRoundRepository extends AbstractRepository {

	@Query("select o from InvestmentRound o where o.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select a from InvestmentRound a")
	Collection<InvestmentRound> findMany();

	@Query("select e from Investor e where e.id = ?1")
	Investor findInvestorById(int accountId);

	@Query("select i.workProgramme from InvestmentRound i where i.id = ?1")
	List<Activity> findByInvestmentRoundId(int id);

	@Query("select j from InvestmentRound j where j.ticker = ?1")
	InvestmentRound findOneByTicker(String newTicker);

	@Query("select w from SpamList w where w.id = 1115")
	SpamList findSpamList();
}
