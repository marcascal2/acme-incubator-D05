
package acme.features.authenticated.discussionForum;

import java.util.ArrayList;

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
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedDiscussionForumCreateService implements AbstractCreateService<Authenticated, DiscussionForum> {

	@Autowired
	private AuthenticatedDiscussionForumRepository repository;


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

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);

		String investorRecordId = request.getServletRequest().getParameter("id");

		model.setAttribute("invId", Integer.parseInt(investorRecordId));

	}

	@Override
	public DiscussionForum instantiate(final Request<DiscussionForum> request) {
		assert request != null;

		DiscussionForum result = new DiscussionForum();
		return result;
	}

	@Override
	public void validate(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		System.out.println("Hola2");

	}

	@Override
	public void create(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;
		System.out.println("Hola3");

		entity.setInvestor(new ArrayList<Investor>());
		entity.setMessages(new ArrayList<Message>());

		String investorRecordId = request.getServletRequest().getParameter("id");
		InvestmentRound investmentRound = this.repository.findInvestorRecordById(Integer.parseInt(investorRecordId));
		entity.setInvestmentRound(investmentRound);

		this.repository.save(entity);
	}

}
