
package acme.features.administrator.inquire;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquires.Inquire;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInquireCreateService implements AbstractCreateService<Administrator, Inquire> {

	@Autowired
	AdministratorInquireRepository repository;


	@Override
	public boolean authorise(final Request<Inquire> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Inquire> request, final Inquire entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate");
	}

	@Override
	public void unbind(final Request<Inquire> request, final Inquire entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "deadline", "description", "title", "maxMoney", "minMoney", "email");
	}

	@Override
	public Inquire instantiate(final Request<Inquire> request) {
		Inquire result;

		result = new Inquire();

		return result;
	}

	@Override
	public void validate(final Request<Inquire> request, final Inquire entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;
		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.inquire.form.error.deadline");
		}
		if (!errors.hasErrors("maxMoney") && !errors.hasErrors("minMoney")) {
			errors.state(request, entity.getMinMoney().getAmount() <= entity.getMaxMoney().getAmount(), "minMoney", "administrator.inquire.form.error.minmaxMoney");
		}
		if (!errors.hasErrors("maxMoney")) {
			errors.state(request, entity.getMaxMoney().getAmount() >= 0, "maxMoney", "administrator.inquire.form.error.negativeMoney");
		}
		if (!errors.hasErrors("minMoney")) {
			errors.state(request, entity.getMinMoney().getAmount() >= 0, "minMoney", "administrator.inquire.form.error.negativeMoney");
		}
	}

	@Override
	public void create(final Request<Inquire> request, final Inquire entity) {
		assert request != null;
		assert entity != null;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		this.repository.save(entity);
	}

}
