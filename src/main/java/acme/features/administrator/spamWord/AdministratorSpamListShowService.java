
package acme.features.administrator.spamWord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamWords.SpamList;
import acme.entities.spamWords.SpamWord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamListShowService implements AbstractShowService<Administrator, SpamList> {

	//Internal state
	@Autowired
	private AdministratorSpamListRepository repository;


	@Override
	public boolean authorise(final Request<SpamList> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<SpamList> request, final SpamList entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		StringBuilder buffer;
		Collection<SpamWord> spamwords;

		request.unbind(entity, model, "spamThreshold");

		spamwords = this.repository.findManySpamwordsById(entity.getId());
		buffer = new StringBuilder();
		Integer x = spamwords.size();
		Integer i = 0;
		for (SpamWord word : spamwords) {
			buffer.append(word.getEnglishTranslation());
			if (i < x - 1) {
				buffer.append(", ");
			}
			i++;
		}

		model.setAttribute("spamwordslist", buffer.toString());

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
}
