
package acme.features.authenticated.discussionForum;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDiscussionForumListService implements AbstractListService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investmentRound.ticker", "investmentRound.kindOfRound", "investmentRound.title");
	}

	@Override
	public Collection<DiscussionForum> findMany(final Request<DiscussionForum> request) {
		assert request != null;
		Collection<DiscussionForum> result = null;
		Collection<DiscussionForum> allForums;
		Principal principal;

		principal = request.getPrincipal();

		if (principal.hasRole(Entrepreneur.class)) {
			result = this.repository.findManyByInvestment(principal.getAccountId());
		} else if (principal.hasRole(Investor.class)) {

			result = new ArrayList<DiscussionForum>();
			allForums = this.repository.findAllDiscussionforum();
			int id = principal.getAccountId();
			Collection<InvestmentApplication> applicationsByInvestor = this.repository.findApplicationsByInvestor(id);

			for (DiscussionForum d : allForums) {
				for (InvestmentApplication a : applicationsByInvestor) {
					if (d.canPost(a)) {
						if (!result.contains(d)) {
							result.add(d);
						}
					}
				}
			}

		}
		return result;
	}

}
