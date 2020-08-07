
package acme.features.administrator.toolRecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activitySectors.ActivitySector;
import acme.entities.toolRecords.ToolRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorToolRecordCreateService implements AbstractCreateService<Administrator, ToolRecord> {

	@Autowired
	AdministratorToolRecordRepository repository;


	@Override
	public boolean authorise(final Request<ToolRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<ToolRecord> request, final ToolRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description", "title", "inventorName", "webSite", "email", "openSource", "stars", "activitySector.sector");
	}

	@Override
	public ToolRecord instantiate(final Request<ToolRecord> request) {
		ToolRecord result;

		result = new ToolRecord();

		return result;
	}

	@Override
	public void validate(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<String> sectors = this.repository.findActivitiesSectors();
		String sector = request.getModel().getString("activitySector.sector").toUpperCase();
		if (!errors.hasErrors("activitySector.sector")) {
			errors.state(request, sectors.contains(sector), "activitySector.sector", "administrator.toolrecords.error.sector");
		}
	}

	@Override
	public void create(final Request<ToolRecord> request, final ToolRecord entity) {
		assert request != null;
		assert entity != null;

		String sector = entity.getActivitySector().getSector().toUpperCase();
		ActivitySector a = this.repository.findSector(sector);
		a.setSector(sector);
		entity.setActivitySector(a);

		this.repository.save(entity);
	}

}
