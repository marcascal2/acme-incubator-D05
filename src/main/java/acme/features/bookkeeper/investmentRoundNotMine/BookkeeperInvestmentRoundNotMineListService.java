
package acme.features.bookkeeper.investmentRoundNotMine;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BookkeeperInvestmentRoundNotMineListService implements AbstractListService<Bookkeeper, InvestmentRound> {

	@Autowired
	BookkeeperInvestmentRoundNotMineRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "ticker", "kindOfRound", "amount");

	}

	@Override
	public Collection<InvestmentRound> findMany(final Request<InvestmentRound> request) {
		assert request != null;
		List<InvestmentRound> collection;

		int bookkeeperId = request.getPrincipal().getActiveRoleId();

		collection = this.repository.findNotInvestmentRoundsByBookkeeperId(bookkeeperId);

		collection = collection.stream().distinct().collect(Collectors.toList());

		collection = collection.stream().filter(i -> i.getRecord().stream().allMatch(a -> a.getBookkeeper().getId() != bookkeeperId)).collect(Collectors.toList());

		return collection;
	}
}
