
package acme.features.anonymous.ruizbulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.ruizbulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousruizbulletinListService implements AbstractListService<Anonymous, ruizbulletin> {

	@Autowired
	AnonymousruizbulletinRepository repository;


	@Override
	public boolean authorise(final Request<ruizbulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<ruizbulletin> request, final ruizbulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "surname", "email", "favouriteFilms");
	}

	@Override
	public Collection<ruizbulletin> findMany(final Request<ruizbulletin> request) {
		assert request != null;
		Collection<ruizbulletin> result;

		result = this.repository.findMany();

		return result;
	}
}
