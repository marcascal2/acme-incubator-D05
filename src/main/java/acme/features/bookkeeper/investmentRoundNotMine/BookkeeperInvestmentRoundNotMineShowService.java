
package acme.features.bookkeeper.investmentRoundNotMine;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookkeeperInvestmentRoundNotMineShowService implements AbstractShowService<Bookkeeper, InvestmentRound> {

	@Autowired
	BookkeeperInvestmentRoundNotMineRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		int roundId = request.getModel().getInteger("id");
		int keeperId = request.getPrincipal().getActiveRoleId();

		Bookkeeper b = this.repository.findBookkeeperById(keeperId);
		Collection<Bookkeeper> keeper = this.repository.findManyBookkeepers(roundId);

		boolean isAuthorised = keeper.contains(b);

		return isAuthorised;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "kindOfRound", "creationDate", "description", "amount", "link", "ticker");

		int id = model.getInteger("id");

		model.setAttribute("invId", id);
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		InvestmentRound result;
		Integer id = request.getModel().getInteger("id");
		result = this.repository.findOneInvestmentRoundById(id);
		return result;
	}

}
