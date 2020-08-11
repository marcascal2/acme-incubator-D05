
package acme.features.investor.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorActivityShowService implements AbstractShowService<Investor, Activity> {

	@Autowired
	InvestorActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		//		int entId = request.getPrincipal().getActiveRoleId();
		//		int actId = request.getModel().getInteger("id");
		//
		//		Activity a = this.repository.findOneById(actId);
		//		InvestmentRound round = a.getRound();
		//		Investor e = this.repository.findInvestorById(entId);
		//
		//		boolean isAuthorised = round.getInvestor().equals(e);

		return true;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "amount", "startDate", "endDate", "title");

	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		Activity result;
		Integer id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
