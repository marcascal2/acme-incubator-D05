
package acme.features.investor.investmentRound;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class InvestorInvestmentRoundListService implements AbstractListService<Investor, InvestmentRound> {

	@Autowired
	InvestorInvestmentRoundRepository repository;


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

		request.unbind(entity, model, "ticker", "creationDate", "kindOfRound");
	}

	@Override
	public Collection<InvestmentRound> findMany(final Request<InvestmentRound> request) {
		assert request != null;
		Collection<InvestmentRound> result;

		int id = request.getPrincipal().getActiveRoleId();
		Collection<InvestmentRound> list = this.repository.findMany();
		result = list.stream().filter(ir -> ir.getApplication().stream().allMatch(y -> y.getInvestor().getId() != id)).collect(Collectors.toList());

		return result;
	}

}
