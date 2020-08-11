
package acme.features.investor.investmentApplication;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentApplications.ApplicationStatus;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorInvestmentApplicationCreateService implements AbstractCreateService<Investor, InvestmentApplication> {

	@Autowired
	InvestorInvestmentApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentApplication> request) {
		assert request != null;

		boolean result = true;

		int idInvestor = request.getPrincipal().getActiveRoleId();
		int invId = request.getModel().getInteger("invId");
		InvestmentRound inv = this.repository.findInvestmentRoundById(invId);
		Investor w = this.repository.findInvestorById(idInvestor);

		for (InvestmentApplication a : inv.getApplication()) {
			if (a.getInvestor().equals(w)) {
				result = false;
				break;
			}
		}

		return result;
	}

	@Override
	public void bind(final Request<InvestmentApplication> request, final InvestmentApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "ticker", "creationMoment", "status", "investmentApplied", "investor");
	}

	@Override
	public void unbind(final Request<InvestmentApplication> request, final InvestmentApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int invId = request.getModel().getInteger("invId");
		model.setAttribute("invId", invId);

		request.unbind(entity, model, "statement", "moneyOffer");
	}

	@Override
	public InvestmentApplication instantiate(final Request<InvestmentApplication> request) {
		assert request != null;
		InvestmentApplication ia = new InvestmentApplication();

		int invId = request.getModel().getInteger("invId");
		InvestmentRound round = this.repository.findInvestmentRoundById(invId);

		String ticker = this.generateTicker(ia, round.getEntrepreneur().getActivitySector().getSector());
		int id = request.getPrincipal().getActiveRoleId();
		Investor inv = this.repository.findInvestorById(id);

		Date moment = new Date(System.currentTimeMillis() - 1);

		ia.setCreationMoment(moment);

		ia.setStatus(ApplicationStatus.PENDING);
		ia.setInvestmentApplied(round);
		ia.setTicker(ticker);
		ia.setInvestor(inv);

		return ia;
	}

	@Override
	public void validate(final Request<InvestmentApplication> request, final InvestmentApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<InvestmentApplication> request, final InvestmentApplication entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);

		entity.setCreationMoment(moment);

		int invId = request.getModel().getInteger("invId");
		InvestmentRound round = this.repository.findInvestmentRoundById(invId);

		round.getApplication().add(entity);

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<InvestmentApplication> request, final Response<InvestmentApplication> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

	private String generateTicker(final InvestmentApplication i, final String s) {
		String res;

		res = s.substring(0, 3).toUpperCase();

		res += "-20-";

		Random r = new Random();
		for (int j = 0; j < 6; j++) {
			Integer in = r.nextInt(10);
			res += in.toString();
		}

		return res;
	}

}
