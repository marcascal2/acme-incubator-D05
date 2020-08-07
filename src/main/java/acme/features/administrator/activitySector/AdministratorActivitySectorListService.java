
package acme.features.administrator.activitySector;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activitySectors.ActivitySector;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorActivitySectorListService implements AbstractListService<Administrator, ActivitySector> {

	//Internal state
	@Autowired
	AdministratorActivitySectorRepository repository;


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
	public Collection<ActivitySector> findMany(final Request<ActivitySector> request) {
		assert request != null;
		Collection<ActivitySector> result;
		result = this.repository.findManyAll();
		return result;
	}

}
