
package acme.features.entrepreneur.investmentApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentApplications.ApplicationStatus;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentApplicationUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentApplication> {

	@Autowired
	private EntrepreneurInvestmentApplicationRepository repository;


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
	public void bind(final Request<InvestmentApplication> request, final InvestmentApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentApplication> request, final InvestmentApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "justification");
		model.setAttribute("oldstatus", entity.getStatus());

	}

	@Override
	public InvestmentApplication findOne(final Request<InvestmentApplication> request) {
		InvestmentApplication result;
		Integer id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		request.getModel().setAttribute("isAccepted", result.getStatus().equals(ApplicationStatus.ACCEPTED));
		return result;
	}

	@Override
	public void validate(final Request<InvestmentApplication> request, final InvestmentApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isRejected;

		isRejected = request.getModel().getAttribute("status").equals("REJECTED") && request.getModel().getAttribute("justification").toString().isEmpty();

		if (!errors.hasErrors("status")) {
			errors.state(request, !isRejected, "justification", "acme.validation.justification");
		}
	}

	@Override
	public void update(final Request<InvestmentApplication> request, final InvestmentApplication entity) {
		assert request != null;
		assert entity != null;

		if (request.getModel().getAttribute("status").equals("ACCEPTED")) {
			DiscussionForum foro = entity.getInvestmentApplied().getForum();
			if (foro != null) {
				List<Investor> investorsPerForum = foro.getInvestor();
				investorsPerForum.add(entity.getInvestor());

				entity.getInvestor().getForum().add(foro);

			}

		}

		this.repository.save(entity);

	}

}
