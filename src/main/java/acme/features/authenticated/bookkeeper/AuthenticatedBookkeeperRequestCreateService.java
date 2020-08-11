
package acme.features.authenticated.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBookkeeperRequestCreateService implements AbstractCreateService<Authenticated, BookkeeperRequest> {

	@Autowired
	AuthenticatedBookkeeperRequestRepository repository;


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

		request.unbind(entity, model, "firmName", "responsibility");

	}

	@Override
	public void bind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public BookkeeperRequest instantiate(final Request<BookkeeperRequest> request) {
		assert request != null;

		BookkeeperRequest result = new BookkeeperRequest();

		result.setStatus("PENDING");

		Principal principal = request.getPrincipal();
		int principalId = principal.getAccountId();
		Authenticated authenticated = this.repository.findAuthenticatedById(principalId);
		result.setAuthenticated(authenticated);

		return result;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean sameRequest;

		Principal principal = request.getPrincipal();
		int id = principal.getAccountId();

		BookkeeperRequest res = this.repository.findBookkeeperRequestByUserId(id);

		if (res != null) {
			sameRequest = id == res.getAuthenticated().getUserAccount().getId();

			if (sameRequest) {
				errors.state(request, !sameRequest, "responsibility", "acme.validation.sameRequest");
			}
		}

	}

	@Override
	public void create(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
