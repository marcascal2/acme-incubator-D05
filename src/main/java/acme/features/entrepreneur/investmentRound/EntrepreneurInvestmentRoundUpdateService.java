
package acme.features.entrepreneur.investmentRound;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.spamWords.SpamList;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		int investmentRoundId = request.getModel().getInteger("id");
		int entrepreneurId = request.getPrincipal().getActiveRoleId();

		InvestmentRound investmentRound = this.repository.findOneById(investmentRoundId);
		Entrepreneur entrepreneur = this.repository.findEntrepreneurById(entrepreneurId);

		boolean isAuthorised = investmentRound.getEntrepreneur().equals(entrepreneur);

		return isAuthorised;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate");

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "kindOfRound", "title", "description", "amount", "link");

	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;
		InvestmentRound result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String newTicker = entity.getTicker();
		if (!errors.hasErrors("ticker")) {
			InvestmentRound repeatedRound = this.repository.findOneByTicker(newTicker);
			boolean isRepeated = repeatedRound != null;
			errors.state(request, isRepeated, "ticker", "entrepreneur.investment-round.form.error.ticker");
		}

		Collection<String> englishWords = this.repository.findAllSpamWordsEnglish();
		Collection<String> spanishWords = this.repository.findAllSpamWordsSpanish();
		String totalText = entity.getTicker() + " " + entity.getTitle() + " " + entity.getDescription() + entity.getLink();

		Boolean isSpamEN = this.isSpam(totalText, englishWords);
		Boolean isSpamES = this.isSpam(totalText, spanishWords);

		if (!errors.hasErrors()) {
			errors.state(request, !isSpamEN, "finalMode", "entrepreneur.investment-round.form.error.spam");
			errors.state(request, !isSpamES, "finalMode", "entrepreneur.investment-round.form.error.spam");
		}

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Date date = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(date);

		this.repository.save(entity);
	}

	private boolean isSpam(final String totalText, final Collection<String> words) {
		List<String> list = Arrays.asList(totalText.split(" "));
		SpamList spamList = this.repository.findSpamList();
		for (String spamWord : words) {
			double frequency = (double) Collections.frequency(list, spamWord) / list.size() * 100;
			if (frequency > spamList.getSpamThreshold()) {
				return true;
			}

		}
		return false;
	}
}
