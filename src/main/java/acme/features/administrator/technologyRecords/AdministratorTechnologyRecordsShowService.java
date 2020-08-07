
package acme.features.administrator.technologyRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorTechnologyRecordsShowService implements AbstractShowService<Administrator, TechnologyRecord> {

	@Autowired
	private AdministratorTechnologyRecordsRepository repository;


	@Override
	public boolean authorise(final Request<TechnologyRecord> request) {
		assert request != null;
		return true;
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
		TechnologyRecord result;

		result = this.repository.findTechRecordById(request.getModel().getInteger("id"));

		return result;
	}

}
