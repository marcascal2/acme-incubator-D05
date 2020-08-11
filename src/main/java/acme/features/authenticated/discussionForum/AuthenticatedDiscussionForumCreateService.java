
package acme.features.authenticated.discussionForum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentApplications.ApplicationStatus;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedDiscussionForumCreateService implements AbstractCreateService<Authenticated, DiscussionForum> {

	@Autowired
	private AuthenticatedDiscussionForumRepository repository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		Boolean result = false;
		int idUA = request.getPrincipal().getAccountId();
		UserAccount ua = this.repository.findOneUserAccountById(idUA);
		Entrepreneur e = this.repository.findEntrepreneurById(idUA);

		if (ua.getRoles().contains(e)) {
			result = true;
		}

		return result;
	}

	@Override
	public void bind(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "messages", "investor", "investmentRound");

	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int invId = request.getModel().getInteger("invId");

		InvestmentRound round = this.repository.findInvestmentRoundById(invId);

		List<UserAccount> acceptedUserAccounts = round.getApplication().stream().filter(a -> a.getStatus() == ApplicationStatus.ACCEPTED).map(x -> x.getInvestor().getUserAccount()).collect(Collectors.toList());

		List<String> ids = acceptedUserAccounts.stream().map(x -> String.valueOf(x.getId())).collect(Collectors.toList());
		List<String> usernames = acceptedUserAccounts.stream().map(x -> x.getUsername()).collect(Collectors.toList());

		String[] ids_arrays = ids.stream().toArray(n -> new String[n]);
		String[] usernames_arrays = usernames.stream().toArray(n -> new String[n]);

		model.setAttribute("user_usernames", usernames_arrays);
		model.setAttribute("user_ids", ids_arrays);
		model.setAttribute("invId", invId);

		model.setAttribute("userToAdd", null);
		model.setAttribute("checkCreate", false);

		request.unbind(entity, model);

	}

	@Override
	public DiscussionForum instantiate(final Request<DiscussionForum> request) {
		assert request != null;

		List<Investor> invs = new ArrayList<>();
		InvestmentRound investmentRound = new InvestmentRound();
		List<Message> messages = new ArrayList<Message>();
		DiscussionForum result = new DiscussionForum();

		result.setInvestor(invs);
		result.setInvestmentRound(investmentRound);
		result.setMessages(messages);

		return result;
	}

	@Override
	public void validate(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean c = request.getModel().getBoolean("checkCreate");

		errors.state(request, c, "checkCreate", "authenticated.discussion-forum.check.error");

	}

	@Override
	public void create(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;

		int invId = request.getModel().getInteger("invId");
		int userId = request.getModel().getInteger("userToAdd");

		InvestmentRound investmentRound = this.repository.findInvestmentRoundById(invId);

		Investor investor = this.repository.findInvestorByUserAccountId(userId);

		List<DiscussionForum> forum = new ArrayList<>();
		forum.add(entity);

		investor.setForum(forum);

		investmentRound.setForum(entity);

		entity.getInvestor().add(investor);
		entity.setInvestmentRound(investmentRound);

		this.repository.save(entity);
	}

}
