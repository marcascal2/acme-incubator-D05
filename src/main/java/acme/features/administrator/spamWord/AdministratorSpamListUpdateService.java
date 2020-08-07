
package acme.features.administrator.spamWord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamWords.SpamList;
import acme.entities.spamWords.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamListUpdateService implements AbstractUpdateService<Administrator, SpamList> {

	@Autowired
	AdministratorSpamListRepository repository;


	@Override
	public boolean authorise(final Request<SpamList> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<SpamList> request, final SpamList entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "spamwordslist");
	}

	@Override
	public void unbind(final Request<SpamList> request, final SpamList entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		StringBuilder buffer;
		Collection<SpamWord> spamWords;

		request.unbind(entity, model, "spamThreshold");

		spamWords = this.repository.findManySpamwordsById(entity.getId());
		buffer = new StringBuilder();
		buffer.append("[");
		Integer x = spamWords.size();
		Integer i = 0;
		for (SpamWord word : spamWords) {
			buffer.append(word.getEnglishTranslation());
			if (i < x - 1) {
				buffer.append(", ");
			}
			i++;
		}

		model.setAttribute("spamwordslist", buffer.toString() + "]");
	}

	@Override
	public SpamList findOne(final Request<SpamList> request) {
		assert request != null;

		SpamList result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<SpamList> request, final SpamList entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		SpamWord deleteSpamword;
		String nws = request.getModel().getString("newSpamwordSpanish");
		String nwe = request.getModel().getString("newSpamwordEnglish");
		String delete = request.getModel().getString("deleteSpamword");

		if (!errors.hasErrors("deleteSpamword")) {
			deleteSpamword = this.repository.findOneSpamword(delete, entity.getId());
			errors.state(request, deleteSpamword != null || delete == "", "deleteSpamword", "administrator.spam_word.form.error.no-exist", delete);
		}
		if (!errors.hasErrors("newSpamwordEnglish")) {

			SpamWord actualSpamword = this.repository.findOneSpamword(nwe, entity.getId());

			errors.state(request, actualSpamword == null, "newSpamwordEnglish", "administrator.spam_word.form.error.duplicated", nwe);
		}

		errors.state(request, !(nwe == "" && nws != ""), "newSpamwordEnglish", "administrator.spam_word.form.error.englishTranslation");
		errors.state(request, !(nws == "" && nwe != ""), "newSpamwordSpanish", "administrator.spam_word.form.error.spanishTranslation");

	}

	@Override
	public void update(final Request<SpamList> request, final SpamList entity) {
		assert request != null;
		assert entity != null;

		String nws = request.getModel().getString("newSpamwordSpanish");
		String nwe = request.getModel().getString("newSpamwordEnglish");
		String delete = request.getModel().getString("deleteSpamword");

		Collection<SpamWord> spamwordslist = this.repository.findManySpamwordsById(entity.getId());

		if (nws != null && nwe != null) {
			SpamWord newSpamword = new SpamWord();
			newSpamword.setEnglishTranslation(nwe);
			newSpamword.setSpanishTranslation(nws);
			newSpamword.setSpamList(entity);

			if (nwe != "" || nws != "") {
				spamwordslist.add(newSpamword);
				this.repository.save(newSpamword);
			}
		}

		if (delete != "") {
			SpamWord deleteSpamword = this.repository.findOneSpamword(delete, entity.getId());
			spamwordslist.remove(deleteSpamword);
			this.repository.deleteSpamWord(deleteSpamword.getId());
		}

		entity.setSpamWords(spamwordslist);

		this.repository.save(entity);

	}

}
