
package acme.features.bookkeeper.investmentRoundNotMine;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.acountingRecords.AccountingRecord;
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

		request.unbind(entity, model, "title", "kindOfRound", "amount");

	}

	@Override
	public Collection<InvestmentRound> findMany(final Request<InvestmentRound> request) {
		assert request != null;
		Collection<InvestmentRound> result = new ArrayList<>();
		Collection<InvestmentRound> collection;

		int bookkeeperId = request.getPrincipal().getActiveRoleId();

		Bookkeeper b = this.repository.findBookkeeperById(bookkeeperId);

		collection = this.repository.findMany();

		for (InvestmentRound i : collection) {
			for (AccountingRecord a : i.getRecord()) {
				if (!a.getBookkeeper().equals(b) || a.getBookkeeper() == null) {
					result.add(i);
				}
			}
		}

		return result;
	}
}
