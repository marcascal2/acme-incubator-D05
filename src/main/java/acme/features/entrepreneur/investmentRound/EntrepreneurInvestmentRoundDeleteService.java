
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentApplications.ApplicationStatus;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		int roundId = request.getModel().getInteger("id");
		int entrepreneurId = request.getPrincipal().getActiveRoleId();

		InvestmentRound investmentRound = this.repository.findOneById(roundId);
		Entrepreneur entrepreneur = this.repository.findEntrepreneurById(entrepreneurId);

		boolean isAuthorised = investmentRound.getEntrepreneur().equals(entrepreneur);

		return isAuthorised;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		//Para el boton de mostrar
		List<InvestmentApplication> list = entity.getApplication().stream().filter(a -> a.getStatus() == ApplicationStatus.ACCEPTED).collect(Collectors.toList());
		model.setAttribute("createForum", entity.getForum() == null && !list.isEmpty());

		Collection<InvestmentApplication> applications = entity.getApplication();
		boolean canDelete = applications.isEmpty() ? true : applications.stream().allMatch(x -> x.getStatus().equals(ApplicationStatus.REJECTED));

		model.setAttribute("canDelete", canDelete);

		Double sum = 0.;
		for (Activity a : entity.getWorkProgramme()) {
			sum += a.getAmount().getAmount();
		}

		model.setAttribute("sumUp", entity.sumUp());

		request.unbind(entity, model, "ticker", "creationDate", "kindOfRound", "title", "description", "amount", "link");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;
		InvestmentRound result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		if (!entity.getWorkProgramme().isEmpty()) {
			entity.setWorkProgramme(new ArrayList<Activity>());
			this.repository.deleteAll(entity.getWorkProgramme());
		}
		if (!entity.getRecord().isEmpty()) {
			entity.setRecord(new ArrayList<>());
			this.repository.deleteAll(entity.getRecord());
		}
		if (!entity.getApplication().isEmpty()) {
			entity.setApplication(new ArrayList<>());
			this.repository.deleteAll(entity.getApplication());
		}

		if (entity.getForum() != null) {
			entity.setForum(null);
			entity.getForum().setInvestmentRound(null);
		}
		if (entity.getEntrepreneur() != null) {
			entity.setEntrepreneur(null);
		}

		this.repository.delete(entity);
	}
}
