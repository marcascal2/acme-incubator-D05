
package acme.features.authenticated.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.messages.Message;
import acme.entities.spamWords.SpamList;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {

		boolean result;
		UserAccount user;
		DiscussionForum forum;
		Collection<UserAccount> authorisedUsers;
		int forumId;

		user = this.repository.findOneUserAccountById(request.getPrincipal().getAccountId());
		forumId = request.getModel().getInteger("forumId");
		forum = this.repository.findDiscussionForum(forumId);

		authorisedUsers = forum.getInvestor().stream().map(x -> x.getUserAccount()).collect(Collectors.toList());
		authorisedUsers.add(forum.getInvestmentRound().getEntrepreneur().getUserAccount());

		result = authorisedUsers.contains(user);

		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "tags");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body");

		int forumId = request.getModel().getInteger("forumId");
		model.setAttribute("forumId", forumId);
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result = new Message();

		int forumId = request.getModel().getInteger("forumId");
		DiscussionForum forum = this.repository.findDiscussionForum(forumId);

		result.setForum(forum);

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String confirmCreation = request.getModel().getString("confirmation");
		boolean confirmation = confirmCreation.equals("true");
		errors.state(request, confirmation, "confirmation", "authenticated.message.form.error.confirmation");

		Collection<String> englishWords = this.repository.findAllSpamWordsEnglish();
		Collection<String> spanishWords = this.repository.findAllSpamWordsSpanish();

		String tag = request.getModel().getString("tags");
		String[] array = Arrays.stream(tag.split(",")).map(String::trim).toArray(String[]::new);

		String totalText = "";
		if (!errors.hasErrors()) {
			totalText = entity.getTitle() + " " + entity.getBody() + " " + String.join(" ", array);
		}

		Boolean isSpamEN = this.isSpam(totalText, englishWords);
		Boolean isSpamES = this.isSpam(totalText, spanishWords);

		if (!errors.hasErrors()) {
			errors.state(request, !isSpamEN, "spam", "authenticated.message.form.error.spam");
			errors.state(request, !isSpamES, "spam", "authenticated.message.form.error.spam");
		}
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {

		String tag = request.getModel().getString("tags");
		String[] array = Arrays.stream(tag.split(",")).map(String::trim).toArray(String[]::new);
		Collection<String> tags = new ArrayList<String>();

		tags = Arrays.asList(array);
		entity.setTags(tags);

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
}
