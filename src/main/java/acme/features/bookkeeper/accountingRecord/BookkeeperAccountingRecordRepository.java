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

package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.acountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select o from AccountingRecord o where o.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select i.record from InvestmentRound i where i.id = ?1")
	Collection<AccountingRecord> findManyByInvestmentId(int invId);

	@Query("select e from Entrepreneur e where e.id = ?1")
	Entrepreneur findEntrepreneurById(int accountId);

	@Query("select o from InvestmentRound o where o.id = ?1")
	InvestmentRound findInvestmentRound(int roundId);

	@Query("select b.bookkeeper from AccountingRecord b where b.round.id = ?1")
	Collection<Bookkeeper> findManyBookkeepers(int roundId);

	@Query("select b from Bookkeeper b where b.id = ?1")
	Bookkeeper findBookkeeperById(int id);

	@Query("select a from AccountingRecord a where a.bookkeeper.id = ?1")
	Collection<AccountingRecord> findMine(int id);
}
