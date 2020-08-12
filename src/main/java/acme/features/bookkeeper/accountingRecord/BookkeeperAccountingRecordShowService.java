
package acme.features.bookkeeper.accountingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.acountingRecords.AccountingRecord;
import acme.entities.acountingRecords.StatusAR;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookkeeperAccountingRecordShowService implements AbstractShowService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		boolean isAuthorised = request.getPrincipal().hasRole(Bookkeeper.class);

		return isAuthorised;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		AccountingRecord result = this.repository.findOneById(entity.getId());
		int id = request.getPrincipal().getActiveRoleId();
		Boolean canUpdate;

		canUpdate = result.getBookkeeper().getId() == id && result.getStatus().equals(StatusAR.DRAFT);

		model.setAttribute("canUpdate", canUpdate);

		request.unbind(entity, model, "body", "creationMoment", "status", "title");

	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		Integer id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
