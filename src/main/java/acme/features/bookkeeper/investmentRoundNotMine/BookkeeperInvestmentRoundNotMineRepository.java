
package acme.features.bookkeeper.investmentRoundNotMine;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperInvestmentRoundNotMineRepository extends AbstractRepository {

	@Query("select ua from InvestmentRound ua where ua.id = ?1")
	InvestmentRound findOneInvestmentRoundById(int id);

	@Query("select a from InvestmentRound a")
	Collection<InvestmentRound> findMany();

	@Query("select e from Bookkeeper e where e.id = ?1")
	Bookkeeper findBookkeeperById(int keeperId);

	@Query("select b.bookkeeper from AccountingRecord b where b.round.id != ?1")
	Collection<Bookkeeper> findManyBookkeepers(int roundId);

	@Query("select a.round from AccountingRecord a where a.bookkeeper.id != ?1")
	List<InvestmentRound> findNotInvestmentRoundsByBookkeeperId(int bookkeeperId);

}
