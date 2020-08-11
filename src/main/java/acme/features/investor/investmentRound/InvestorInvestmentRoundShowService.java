
package acme.features.investor.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorInvestmentRoundShowService implements AbstractShowService<Investor, InvestmentRound> {

	@Autowired
	InvestorInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		Boolean canApply = true;

		int id = request.getModel().getInteger("id");
		int idUser = request.getPrincipal().getActiveRoleId();

		InvestmentRound inv = this.repository.findOneById(id);
		Investor w = this.repository.findInvestorById(idUser);

		for (InvestmentApplication a : inv.getApplication()) {
			if (a.getInvestor().equals(w)) {
				canApply = false;
				break;
			}
		}

		return canApply;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "kindOfRound", "title", "description", "amount", "link");

		int id = model.getInteger("id");

		model.setAttribute("invId", id);

	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		InvestmentRound result;
		Integer id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
