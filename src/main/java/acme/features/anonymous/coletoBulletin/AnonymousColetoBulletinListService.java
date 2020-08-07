
package acme.features.anonymous.coletoBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.ColetoBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousColetoBulletinListService implements AbstractListService<Anonymous, ColetoBulletin> {

	@Autowired
	AnonymousColetoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<ColetoBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<ColetoBulletin> findMany(final Request<ColetoBulletin> request) {
		assert request != null;

		Collection<ColetoBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<ColetoBulletin> request, final ColetoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "age", "degree", "favouriteSubject");
	}

}
