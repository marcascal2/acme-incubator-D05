
package acme.features.administrator.activitySector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activitySectors.ActivitySector;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorActivitySectorUpdateService implements AbstractUpdateService<Administrator, ActivitySector> {

	@Autowired
	AdministratorActivitySectorRepository repository;


	@Override
	public boolean authorise(final Request<ActivitySector> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<ActivitySector> request, final ActivitySector entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
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

	@Override
	public void validate(final Request<ActivitySector> request, final ActivitySector entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<ActivitySector> request, final ActivitySector entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
