
package acme.features.entrepreneur.investmentApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentApplications.ApplicationStatus;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentApplicationShowService implements AbstractShowService<Entrepreneur, InvestmentApplication> {

	@Autowired
	EntrepreneurInvestmentApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentApplication> request) {
		assert request != null;
		int appId = request.getModel().getInteger("id");
		int entrepreneurId = request.getPrincipal().getActiveRoleId();

		InvestmentApplication app = this.repository.findOneById(appId);
		Entrepreneur entrepreneur = this.repository.findEntrepreneurBy(entrepreneurId);

		boolean isAuthorised = app.getInvestmentApplied().getEntrepreneur().equals(entrepreneur);

		return isAuthorised;
	}

	@Override
	public void unbind(final Request<InvestmentApplication> request, final InvestmentApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "statement", "moneyOffer", "status", "justification");
		model.setAttribute("oldstatus", entity.getStatus());
		model.setAttribute("isAccepted", entity.getStatus().equals(ApplicationStatus.ACCEPTED));

	}

	@Override
	public InvestmentApplication findOne(final Request<InvestmentApplication> request) {
		InvestmentApplication result;
		Integer id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
