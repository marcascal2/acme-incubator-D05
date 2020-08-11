
package acme.features.authenticated.discussionForum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedDiscussionForumShowService implements AbstractShowService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;
		Boolean result = false;
		Principal principal;
		principal = request.getPrincipal();
		if (principal.hasRole(Entrepreneur.class)) {
			int entrepreneurId = request.getPrincipal().getAccountId();
			int forumId = request.getModel().getInteger("id");

			DiscussionForum a = this.repository.findOneById(forumId);
			Entrepreneur e = this.repository.findEntrepreneurById(entrepreneurId);

			result = a.getInvestmentRound().getEntrepreneur().equals(e);
		} else if (principal.hasRole(Investor.class)) {
			int investorId = request.getPrincipal().getAccountId();
			int forumId = request.getModel().getInteger("id");

			DiscussionForum a = this.repository.findOneById(forumId);
			Investor e = this.repository.findInvestorById(investorId);

			result = a.getInvestor().contains(e);
		}
		return result;
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investmentRound.ticker", "messages");

		int id = model.getInteger("id");

		model.setAttribute("forumId", id);

		model.setAttribute("noMessages", entity.getMessages().isEmpty());

		model.setAttribute("isInvestor", request.getPrincipal().hasRole(Investor.class));

	}

	@Override
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		DiscussionForum result;
		Integer id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
