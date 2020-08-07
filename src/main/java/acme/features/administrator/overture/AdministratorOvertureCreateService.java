
package acme.features.administrator.overture;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorOvertureCreateService implements AbstractCreateService<Administrator, Overture> {

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

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "maxMoney", "minMoney", "email");
	}

	@Override
	public Overture instantiate(final Request<Overture> request) {
		Overture result;

		result = new Overture();

		Date date = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(date);
		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;
		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.overture.form.error.deadline");
		}

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
	public void create(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
