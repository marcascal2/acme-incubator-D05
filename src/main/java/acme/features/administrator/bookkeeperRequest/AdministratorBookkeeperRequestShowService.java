
package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorBookkeeperRequestShowService implements AbstractShowService<Administrator, BookkeeperRequest> {

	@Autowired
	private AdministratorBookkeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "responsibility", "status");
		model.setAttribute("oldstatus", entity.getStatus());

	}

	@Override
	public BookkeeperRequest findOne(final Request<BookkeeperRequest> request) {

		BookkeeperRequest result = null;
		result = this.repository.findBookkeeperRequestById(request.getModel().getInteger("id"));

		return result;
	}

}
