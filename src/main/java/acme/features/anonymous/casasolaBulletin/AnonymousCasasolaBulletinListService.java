
package acme.features.anonymous.casasolaBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.CasasolaBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCasasolaBulletinListService implements AbstractListService<Anonymous, CasasolaBulletin> {

	@Autowired
	AnonymousCasasolaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CasasolaBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<CasasolaBulletin> findMany(final Request<CasasolaBulletin> request) {
		assert request != null;
		Collection<CasasolaBulletin> result;

		result = this.repository.findManyAll();
		return result;
	}

	@Override
	public void unbind(final Request<CasasolaBulletin> request, final CasasolaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "surname", "name", "favouriteColor", "favouriteNumber");
	}
}
