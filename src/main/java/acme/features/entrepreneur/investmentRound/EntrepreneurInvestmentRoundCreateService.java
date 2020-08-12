
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.spamWords.SpamList;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		Entrepreneur entrepreneur;
		Principal principal;

		entrepreneur = this.repository.findEntrepreneurById(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "ticker", "creationDate");

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "kindOfRound", "title", "description", "amount", "link");
	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		InvestmentRound result;
		Entrepreneur entrepreneur;

		result = new InvestmentRound();
		entrepreneur = this.repository.findEntrepreneurById(request.getPrincipal().getActiveRoleId());

		result.setEntrepreneur(entrepreneur);
		result.setApplication(null);

		String ticker = this.generateTicker(result, result.getEntrepreneur().getActivitySector().getSector());
		result.setTicker(ticker);

		Date date = new Date(System.currentTimeMillis() - 1);
		result.setCreationDate(date);

		return result;

	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Collection<String> englishWords = this.repository.findAllSpamWordsEnglish();
		Collection<String> spanishWords = this.repository.findAllSpamWordsSpanish();
		if (!errors.hasErrors()) {
			String totalText = entity.getTicker() + " " + entity.getTitle() + " " + entity.getDescription() + entity.getLink();

			Boolean isSpamEN = this.isSpam(totalText, englishWords);
			Boolean isSpamES = this.isSpam(totalText, spanishWords);

			errors.state(request, !isSpamEN, "finalMode", "entrepreneur.investment-round.form.error.spam");
			errors.state(request, !isSpamES, "finalMode", "entrepreneur.investment-round.form.error.spam");
		}
	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	private Boolean isSpam(final String reallyBigString, final Collection<String> sl) {
		SpamList spamList = this.repository.findSpamList();
		Double numSpamWords = 0.;

		for (String spamword : sl) {
			numSpamWords = numSpamWords + this.numDeSpamwords(reallyBigString.toLowerCase(), spamword, 0.);
			double frequency = numSpamWords / sl.size() * 100;
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

	private String generateTicker(final InvestmentRound i, final String s) {
		String res;

		res = s.substring(0, 3).toUpperCase();

		res += "-20-";

		Random r = new Random();
		for (int j = 0; j < 6; j++) {
			Integer in = r.nextInt(10);
			res += in.toString();
		}

		return res;
	}
}
