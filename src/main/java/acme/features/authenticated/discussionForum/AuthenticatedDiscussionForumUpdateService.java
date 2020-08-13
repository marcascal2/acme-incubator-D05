
package acme.features.authenticated.discussionForum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentApplications.ApplicationStatus;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedDiscussionForumUpdateService implements AbstractUpdateService<Authenticated, DiscussionForum> {

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

		DiscussionForum f = this.repository.findOneById(request.getModel().getInteger("id"));

		result = f.getInvestmentRound().getEntrepreneur().getUserAccount().getId() == idUA;

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

		Integer invId = entity.getInvestmentRound().getId();

		InvestmentRound round = this.repository.findInvestmentRoundById(invId);

		List<UserAccount> acceptedUserAccounts = round.getApplication().stream().filter(a -> a.getStatus() == ApplicationStatus.ACCEPTED).map(x -> x.getInvestor().getUserAccount()).collect(Collectors.toList());

		acceptedUserAccounts = acceptedUserAccounts.stream().filter(u -> !entity.getInvestor().stream().map(i -> i.getUserAccount().getId()).collect(Collectors.toList()).contains(u.getId())).collect(Collectors.toList());

		List<String> ids = acceptedUserAccounts.stream().map(x -> String.valueOf(x.getId())).collect(Collectors.toList());
		List<String> usernames = acceptedUserAccounts.stream().map(x -> x.getUsername()).collect(Collectors.toList());

		String[] ids_arrays = ids.stream().toArray(n -> new String[n]);
		String[] usernames_arrays = usernames.stream().toArray(n -> new String[n]);

		model.setAttribute("user_usernames", usernames_arrays);
		model.setAttribute("user_ids", ids_arrays);
		model.setAttribute("invId", invId);

		model.setAttribute("userToAdd", null);

		request.unbind(entity, model);
	}

	@Override
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");

		DiscussionForum forum = this.repository.findOneById(id);

		return forum;
	}

	@Override
	public void validate(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Integer invId = entity.getInvestmentRound().getId();

		InvestmentRound round = this.repository.findInvestmentRoundById(invId);

		List<UserAccount> acceptedUserAccounts = round.getApplication().stream().filter(a -> a.getStatus() == ApplicationStatus.ACCEPTED).map(x -> x.getInvestor().getUserAccount()).collect(Collectors.toList());

		acceptedUserAccounts = acceptedUserAccounts.stream().filter(u -> !entity.getInvestor().stream().map(i -> i.getUserAccount().getId()).collect(Collectors.toList()).contains(u.getId())).collect(Collectors.toList());

		List<String> usernames = acceptedUserAccounts.stream().map(x -> x.getUsername()).collect(Collectors.toList());

		errors.state(request, !usernames.isEmpty(), "userToAdd", "authenticated.discussion-forum.form.error.userToAdd");
	}

	@Override
	public void update(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;

		Integer invId = entity.getInvestmentRound().getId();
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

	@Override
	public void onSuccess(final Request<DiscussionForum> request, final Response<DiscussionForum> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
