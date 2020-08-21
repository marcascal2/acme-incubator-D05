
package acme.features.authenticated.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedCardCreateService implements AbstractCreateService<Authenticated, CreditCard> {

	@Autowired
	AuthenticatedCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;
		int id = request.getModel().getInteger("patron");
		Patron p = this.repository.findPatronById(id);

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
		Integer patron = request.getModel().getInteger("patron");
		model.setAttribute("patron", patron);
	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		CreditCard result;

		result = new CreditCard();

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;
		Integer id = request.getModel().getInteger("patron");
		Patron patron = this.repository.findPatronById(id);
		entity.setPatron(patron);
		entity.getPatron().setCard(entity);
		this.repository.save(entity);

	}

}
