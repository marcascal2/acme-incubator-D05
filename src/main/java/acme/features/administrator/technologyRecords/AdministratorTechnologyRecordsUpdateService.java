
package acme.features.administrator.technologyRecords;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activitySectors.ActivitySector;
import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorTechnologyRecordsUpdateService implements AbstractUpdateService<Administrator, TechnologyRecord> {

	//Internal state ------------------------------------------------------------
	@Autowired
	AdministratorTechnologyRecordsRepository repository;


	@Override
	public boolean authorise(final Request<TechnologyRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "activitySector.sector", "inventor", "description", "website", "email", "openSource", "stars");
	}

	@Override
	public TechnologyRecord findOne(final Request<TechnologyRecord> request) {
		assert request != null;

		TechnologyRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findTechRecordById(id);

		return result;
	}

	@Override
	public void validate(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<String> sectors = this.repository.findActivitiesSectors();
		String sector = request.getModel().getString("activitySector.sector").toUpperCase();
		if (!errors.hasErrors("activitySector.sector")) {
			errors.state(request, sectors.contains(sector), "activitySector.sector", "administrator.technologyrecords.error.sector");
		}

	}

	@Override
	public void update(final Request<TechnologyRecord> request, final TechnologyRecord entity) {
		assert request != null;
		assert entity != null;

		String sector = entity.getActivitySector().getSector().toUpperCase();
		ActivitySector a = this.repository.findSector(sector);
		a.setSector(sector);
		entity.setActivitySector(a);
		this.repository.save(entity);
	}
}
