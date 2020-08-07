
package acme.features.administrator.overture;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorOvertureUpdateService implements AbstractUpdateService<Administrator, Overture> {

	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "maxMoney", "minMoney", "email");
	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		assert request != null;

		Overture result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (entity.getMaxMoney() != null && entity.getMinMoney() != null) {

			if (!errors.hasErrors("maxMoney") || !errors.hasErrors("minMoney")) {
				errors.state(request, !(entity.getMaxMoney().getAmount() <= entity.getMinMoney().getAmount()), "minMoney", "administrator.overture.form.error.minMoney");
			}
			if (!errors.hasErrors("maxMoney")) {
				errors.state(request, entity.getMaxMoney().getAmount() >= 0, "maxMoney", "administrator.overture.form.error.negativeMoney");
			}
			if (!errors.hasErrors("minMoney")) {
				errors.state(request, entity.getMinMoney().getAmount() >= 0, "minMoney", "administrator.overture.form.error.negativeMoney");
			}
		}
	}

	@Override
	public void update(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);
	}
}
