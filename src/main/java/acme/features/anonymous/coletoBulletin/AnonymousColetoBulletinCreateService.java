
package acme.features.anonymous.coletoBulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.ColetoBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousColetoBulletinCreateService implements AbstractCreateService<Anonymous, ColetoBulletin> {

	@Autowired
	AnonymousColetoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<ColetoBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public ColetoBulletin instantiate(final Request<ColetoBulletin> request) {
		assert request != null;

		ColetoBulletin result;

		result = new ColetoBulletin();

		return result;
	}

	@Override
	public void unbind(final Request<ColetoBulletin> request, final ColetoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "age", "degree", "favouriteSubject");
	}

	@Override
	public void bind(final Request<ColetoBulletin> request, final ColetoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<ColetoBulletin> request, final ColetoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<ColetoBulletin> request, final ColetoBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
