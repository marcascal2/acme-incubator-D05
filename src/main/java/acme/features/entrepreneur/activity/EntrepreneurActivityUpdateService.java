
package acme.features.entrepreneur.activity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.roles.Entrepreneur;
import acme.entities.spamWords.SpamList;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurActivityUpdateService implements AbstractUpdateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;
		boolean result = false;

		int entrepreneurId = request.getPrincipal().getActiveRoleId();
		int activityId = request.getModel().getInteger("id");

		Activity activity = this.repository.findOneById(activityId);
		Entrepreneur entrepreneur = this.repository.findEntrepreneurById(entrepreneurId);

		result = activity.getRound().getEntrepreneur().equals(entrepreneur);

		return result;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "startDate", "endDate", "amount");

		String roundId = request.getServletRequest().getParameter("id");
		model.setAttribute("id", Integer.parseInt(roundId));
	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;
		Activity result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Double sum = 0.;

		if (entity.getRound().getWorkProgramme() != null) {
			for (Activity a : entity.getRound().getWorkProgramme()) {
				sum += a.getAmount().getAmount();
			}
		}

		if (!errors.hasErrors("amount")) {
			errors.state(request, !(sum > entity.getRound().getAmount().getAmount()), "amount", "entrepreneur.activity.form.error.sum");
		}

		if (!errors.hasErrors("startDate") && !errors.hasErrors("endDate")) {
			errors.state(request, entity.getStartDate().after(new Date(System.currentTimeMillis() - 1)), "startDate", "entrepreneur.activity.form.error.startDate");
			errors.state(request, entity.getEndDate().after(new Date(System.currentTimeMillis() - 1)), "endDate", "entrepreneur.activity.form.error.endDate");
			errors.state(request, entity.getEndDate().after(entity.getStartDate()), "endDate", "entrepreneur.activity.form.error.endDate.after");

		}

		Collection<String> englishWords = this.repository.findAllSpamWordsEnglish();
		Collection<String> spanishWords = this.repository.findAllSpamWordsSpanish();
		if (!errors.hasErrors()) {
			String totalText = entity.getTitle();

			Boolean isSpamEN = this.isSpam(totalText, englishWords);
			Boolean isSpamES = this.isSpam(totalText, spanishWords);

			errors.state(request, !isSpamEN, "spam", "entrepreneur.activity.form.error.spam");
			errors.state(request, !isSpamES, "spam", "entrepreneur.activity.form.error.spam");
		}
	}

	@Override
	public void update(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

	private Boolean isSpam(final String reallyBigString, final Collection<String> sl) {
		SpamList spamList = this.repository.findSpamList();
		Double numSpamWords = 0.;

		for (String spamword : sl) {
			numSpamWords = numSpamWords + this.numDeSpamwords(reallyBigString.toLowerCase(), spamword, 0.);
			double frequency = (double) Collections.frequency(sl, spamword) / sl.size() * 100;
			if (frequency > spamList.getSpamThreshold()) {
				return true;
			}
		}
		return false;

	}

	private Double numDeSpamwords(final String fullText, final String spamword, final Double u) {
		if (!fullText.contains(spamword)) {
			return u;
		} else {
			Integer a = fullText.indexOf(spamword);
			return this.numDeSpamwords(fullText.substring(a + 1), spamword, u + 1);
		}
	}
}
