
package acme.features.anonymous.casasolaBulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.CasasolaBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousCasasolaBulletinCreateService implements AbstractCreateService<Anonymous, CasasolaBulletin> {

	@Autowired
	AnonymousCasasolaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CasasolaBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public CasasolaBulletin instantiate(final Request<CasasolaBulletin> request) {
		assert request != null;

		CasasolaBulletin result;

		result = new CasasolaBulletin();

		return result;
	}

	@Override
	public void bind(final Request<CasasolaBulletin> request, final CasasolaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CasasolaBulletin> request, final CasasolaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "surname", "name", "favouriteColor", "favouriteNumber");
	}

	@Override
	public void validate(final Request<CasasolaBulletin> request, final CasasolaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<CasasolaBulletin> request, final CasasolaBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
