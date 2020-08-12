
package acme.features.bookkeeper.investmentRound;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BookkeeperInvestmentRoundListMineService implements AbstractListService<Bookkeeper, InvestmentRound> {

	@Autowired
	BookkeeperInvestmentRoundRepository repository;


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
		Collection<InvestmentRound> collection;

		int bookkeeperId = request.getPrincipal().getActiveRoleId();

		collection = this.repository.findInvestmetnRoundByBookkeeperId(bookkeeperId);
		collection = collection.stream().distinct().collect(Collectors.toList());

		return collection;
	}

}
