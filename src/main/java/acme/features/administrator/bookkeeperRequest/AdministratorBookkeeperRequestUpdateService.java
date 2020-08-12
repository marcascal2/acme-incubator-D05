
package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBookkeeperRequestUpdateService implements AbstractUpdateService<Administrator, BookkeeperRequest> {

	@Autowired
	AdministratorBookkeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status");
		model.setAttribute("oldstatus", entity.getStatus());
	}

	@Override
	public BookkeeperRequest findOne(final Request<BookkeeperRequest> request) {
		assert request != null;

		BookkeeperRequest result = null;
		result = this.repository.findBookkeeperRequestById(request.getModel().getInteger("id"));

		return result;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;

		if (entity.getStatus().equals("ACCEPTED")) {
			Bookkeeper res = new Bookkeeper();
			res.setFirmName(entity.getFirmName());
			res.setResponsibility(entity.getResponsibility());
			res.setUserAccount(entity.getAuthenticated().getUserAccount());

			this.repository.save(res);
		}

		this.repository.save(entity);

	}

}
