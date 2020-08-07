
package acme.features.investor.investmentApplication;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class InvestorInvestmentApplicationListService implements AbstractListService<Investor, InvestmentApplication> {

	@Autowired
	InvestorInvestmentApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentApplication> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<InvestmentApplication> request, final InvestmentApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "statement");
	}

	@Override
	public Collection<InvestmentApplication> findMany(final Request<InvestmentApplication> request) {
		assert request != null;

		Collection<InvestmentApplication> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findManyByInvestorId(principal.getActiveRoleId());

		return result;
	}

}
