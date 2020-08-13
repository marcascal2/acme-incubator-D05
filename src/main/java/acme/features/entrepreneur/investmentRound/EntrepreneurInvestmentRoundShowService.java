
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentApplications.ApplicationStatus;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.spamWords.SpamList;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentRoundShowService implements AbstractShowService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		int roundId = request.getModel().getInteger("id");
		int entrepreneurId = request.getPrincipal().getActiveRoleId();

		InvestmentRound round = this.repository.findOneById(roundId);
		Entrepreneur entrepreneur = this.repository.findEntrepreneurById(entrepreneurId);

		boolean isAuthorised = round.getEntrepreneur().equals(entrepreneur);

		return isAuthorised;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "kindOfRound", "title", "description", "amount", "link");

		int id = model.getInteger("id");
		model.setAttribute("invId", id);

		//Para el boton de mostrar
		List<InvestmentApplication> list = entity.getApplication().stream().filter(a -> a.getStatus() == ApplicationStatus.ACCEPTED).collect(Collectors.toList());
		model.setAttribute("createForum", entity.getForum() == null && !list.isEmpty());

		Collection<String> englishWords = this.repository.findAllSpamWordsEnglish();
		Collection<String> spanishWords = this.repository.findAllSpamWordsSpanish();
		String totalText = entity.getTicker() + " " + entity.getTitle() + " " + entity.getDescription() + entity.getLink();

		Boolean isSpamEN = this.isSpam(totalText, englishWords);
		Boolean isSpamES = this.isSpam(totalText, spanishWords);

		model.setAttribute("finalMode", !isSpamEN && !isSpamES && entity.sumUp());

		List<InvestmentApplication> applications = entity.getApplication().stream().collect(Collectors.toList());
		boolean canDelete = applications.isEmpty() ? true : applications.stream().allMatch(x -> x.getStatus() == ApplicationStatus.REJECTED);

		model.setAttribute("canDelete", canDelete);

		Double sum = 0.;
		for (Activity a : entity.getWorkProgramme()) {
			sum += a.getAmount().getAmount();
		}

		model.setAttribute("sumUp", entity.sumUp());
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		InvestmentRound result;
		Integer id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
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

}
