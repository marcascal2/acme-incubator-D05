/*
 * AuthenticatedInvestorUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.investor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activitySectors.ActivitySector;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedInvestorUpdateService implements AbstractUpdateService<Authenticated, Investor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedInvestorRepository repository;


	// AbstractUpdateService<Authenticated, Investor> interface ------------

	@Override
	public boolean authorise(final Request<Investor> request) {
		assert request != null;

		Boolean result = true;
		int idUA = request.getPrincipal().getAccountId();
		UserAccount ua = this.repository.findOneUserAccountById(idUA);
		Investor e = this.repository.findOneInvestorByUserAccountId(idUA);

		if (ua.getRoles().contains(e)) {
			result = false;
		}

		return !result;
	}

	@Override
	public void bind(final Request<Investor> request, final Investor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Investor> request, final Investor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "sector.sector", "profile");
	}

	@Override
	public Investor findOne(final Request<Investor> request) {
		assert request != null;

		Investor result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findOneInvestorByUserAccountId(principal.getAccountId());

		return result;
	}

	@Override
	public void validate(final Request<Investor> request, final Investor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<String> sectors = this.repository.findActivitiesSectors();
		String sector = request.getModel().getString("sector.sector").toUpperCase();
		if (!errors.hasErrors("sector.sector")) {
			errors.state(request, sectors.contains(sector), "sector.sector", "authenticated.investor.error.sector");
		}
	}

	@Override
	public void update(final Request<Investor> request, final Investor entity) {
		assert request != null;
		assert entity != null;

		String sector = entity.getSector().getSector().toUpperCase();
		ActivitySector a = this.repository.findSector(sector);
		entity.setSector(a);
		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Investor> request, final Response<Investor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
