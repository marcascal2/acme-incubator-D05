
package acme.features.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurActivityShowService implements AbstractShowService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		int entId = request.getPrincipal().getActiveRoleId();
		int actId = request.getModel().getInteger("id");

		Activity a = this.repository.findOneById(actId);
		Entrepreneur e = this.repository.findEntrepreneurById(entId);

		boolean isAuthorised = a.getRound().getEntrepreneur().equals(e);

		return isAuthorised;
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
