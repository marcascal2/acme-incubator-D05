
package acme.features.entrepreneur.activity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		boolean result;
		Entrepreneur entrepreneur;
		Principal principal;

		entrepreneur = this.repository.findEntrepreneurById(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startDate", "endDate", "amount");

		String roundId = request.getServletRequest().getParameter("id");
		model.setAttribute("id", Integer.parseInt(roundId));
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {
		Activity result;
		String roundId = request.getServletRequest().getParameter("id");

		InvestmentRound round = this.repository.findInvestmentRound(Integer.parseInt(roundId));
		result = new Activity();
		result.setRound(round);
		return result;

	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Double sum = 0.;

		if (entity.getRound().getWorkProgramme() != null) {
			for (Activity a : entity.getRound().getWorkProgramme()) {
				sum += a.getAmount().getAmount();
			}
		}
		if (!errors.hasErrors("amount")) {
			errors.state(request, !(sum + entity.getAmount().getAmount() > entity.getRound().getAmount().getAmount()), "amount", "entrepreneur.activity.form.error.sum");
		}

		if (!errors.hasErrors("startDate") && !errors.hasErrors("endDate")) {
			errors.state(request, entity.getStartDate().after(new Date(System.currentTimeMillis() - 1)), "startDate", "entrepreneur.activity.form.error.startDate");
			errors.state(request, entity.getEndDate().after(new Date(System.currentTimeMillis() - 1)), "endDate", "entrepreneur.activity.form.error.endDate");
			errors.state(request, entity.getEndDate().after(entity.getStartDate()), "endDate", "entrepreneur.activity.form.error.endDate.after");

		}
	}

	@Override
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
