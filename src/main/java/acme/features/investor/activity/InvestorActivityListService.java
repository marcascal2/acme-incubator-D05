
package acme.features.investor.activity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class InvestorActivityListService implements AbstractListService<Investor, Activity> {

	@Autowired
	InvestorActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		//		int roundId = request.getModel().getInteger("id");
		//		int InvestorId = request.getPrincipal().getActiveRoleId();
		//
		//		InvestmentRound round = this.repository.findInvestmentRound(roundId);
		//		Investor Investor = this.repository.findInvestorById(InvestorId);
		//
		//		boolean isAuthorised = round.getInvestor().equals(Investor);

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
	public Collection<Activity> findMany(final Request<Activity> request) {
		assert request != null;
		Collection<Activity> result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findManyByInvestmentId(id);
		return result;
	}

}
