
package acme.features.authenticated.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedCardUpdateService implements AbstractUpdateService<Authenticated, CreditCard> {

	@Autowired
	AuthenticatedCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		int id = request.getModel().getInteger("card");
		Patron p = this.repository.findPatronByCardId(id);

		int idUA = request.getPrincipal().getAccountId();
		Patron e = this.repository.findOnePatronByUserAccountId(idUA);

		return p.equals(e);
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "patron");
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holder", "number", "brand", "expirationDate", "cvv");

		int id = request.getModel().getInteger("card");

		model.setAttribute("card", id);

	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		int id = request.getModel().getInteger("card");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		entity.getPatron().setCard(entity);

		this.repository.save(entity);

	}
}
