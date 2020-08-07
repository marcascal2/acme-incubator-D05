
package acme.features.administrator.activitySector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activitySectors.ActivitySector;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorActivitySectorShowService implements AbstractShowService<Administrator, ActivitySector> {

	//Internal state
	@Autowired
	private AdministratorActivitySectorRepository repository;


	@Override
	public boolean authorise(final Request<ActivitySector> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<ActivitySector> request, final ActivitySector entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "sector");

	}

	@Override
	public ActivitySector findOne(final Request<ActivitySector> request) {
		assert request != null;
		ActivitySector result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}
}
