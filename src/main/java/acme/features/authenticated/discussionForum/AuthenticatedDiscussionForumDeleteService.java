
package acme.features.authenticated.discussionForum;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedDiscussionForumDeleteService implements AbstractDeleteService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert request != null;
		assert model != null;

		request.unbind(entity, model, "messages", "investor", "investmentRound");

	}

	@Override
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		DiscussionForum result;
		Integer id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;

		InvestmentRound a = entity.getInvestmentRound();
		a.setForum(null);
		entity.setInvestmentRound(null);

		List<Investor> inv = entity.getInvestor();
		for (Investor i : inv) {
			i.getForum().remove(entity);
			this.repository.save(i);
		}

		entity.setInvestor(null);

		if (entity.getMessages() != null) {
			Collection<Message> forumMessages = entity.getMessages();
			Collection<Message> messages = this.repository.findAllMessages();

			for (Message d : forumMessages) {
				for (Message m : messages) {
					if (d.equals(m)) {
						this.repository.delete(m);
					}
				}
			}
		}
		this.repository.save(a);

		this.repository.save(entity);
		this.repository.delete(entity);
	}

}
