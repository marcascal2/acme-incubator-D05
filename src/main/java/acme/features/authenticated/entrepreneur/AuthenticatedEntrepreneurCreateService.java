
package acme.features.authenticated.entrepreneur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activitySectors.ActivitySector;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedEntrepreneurCreateService implements AbstractCreateService<Authenticated, Entrepreneur> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedEntrepreneurRepository repository;


	@Override
	public boolean authorise(final Request<Entrepreneur> request) {
		assert request != null;

		Boolean result = true;
		int idUA = request.getPrincipal().getAccountId();
		UserAccount ua = this.repository.findOneUserAccountById(idUA);
		Entrepreneur e = this.repository.findOneEntrepreneurByUserAccountId(idUA);

		if (ua.getRoles().contains(e)) {
			result = false;
		}

		return result;
	}

	@Override
	public void bind(final Request<Entrepreneur> request, final Entrepreneur entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Entrepreneur> request, final Entrepreneur entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "startUpName", "activitySector.sector", "qualificationRecord", "skillsRecord");
	}

	@Override
	public Entrepreneur instantiate(final Request<Entrepreneur> request) {
		Entrepreneur result;
		Principal principal;

		result = new Entrepreneur();
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<Entrepreneur> request, final Entrepreneur entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<String> sectors = this.repository.findActivitiesSectors();
		String sector = request.getModel().getString("activitySector.sector").toUpperCase();
		if (!errors.hasErrors("activitySector.sector")) {
			errors.state(request, sectors.contains(sector), "activitySector.sector", "authenticated.entrepreneur.error.sector");
		}
	}

	@Override
	public void create(final Request<Entrepreneur> request, final Entrepreneur entity) {
		assert request != null;
		assert entity != null;

		String sector = entity.getActivitySector().getSector().toUpperCase();
		ActivitySector a = this.repository.findSector(sector);
		entity.setActivitySector(a);

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Entrepreneur> request, final Response<Entrepreneur> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
