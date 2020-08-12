
package acme.features.patron.banner;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.roles.Patron;
import acme.entities.spamWords.SpamList;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronBannerCreateService implements AbstractCreateService<Patron, Banner> {

	@Autowired
	PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		boolean result;
		Patron patron;
		Principal principal;

		patron = this.repository.findPatronById(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = patron.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url");
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		assert request != null;

		Banner banner = new Banner();
		Principal principal = request.getPrincipal();
		int idPrincipal = principal.getActiveRoleId();

		Patron patron = this.repository.findPatronById(idPrincipal);
		banner.setPatron(patron);

		return banner;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Collection<String> englishWords = this.repository.findAllSpamWordsEnglish();
		Collection<String> spanishWords = this.repository.findAllSpamWordsSpanish();
		if (!errors.hasErrors()) {
			String totalText = entity.getPicture() + " " + entity.getSlogan() + " " + entity.getUrl();

			Boolean isSpamEN = this.isSpam(totalText, englishWords);
			Boolean isSpamES = this.isSpam(totalText, spanishWords);

			errors.state(request, !isSpamEN, "spam", "patron.banner.form.error.spam");
			errors.state(request, !isSpamES, "spam", "patron.banner.form.error.spam");
		}
	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
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
