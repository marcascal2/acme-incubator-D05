
package acme.features.bookkeeper.accountingRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.acountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		boolean isAuthorised = request.getPrincipal().hasRole(Bookkeeper.class);

		return isAuthorised;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;

		request.bind(entity, errors, "creationMoment", "round", "bookkeeper");
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int invId = request.getModel().getInteger("invId");
		model.setAttribute("invId", invId);

		request.unbind(entity, model, "body", "status", "title");
	}

	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {
		assert request != null;

		int invId = request.getModel().getInteger("invId");
		InvestmentRound inv = this.repository.findInvestmentRound(invId);

		int bookId = request.getPrincipal().getActiveRoleId();
		Bookkeeper b = this.repository.findBookkeeperById(bookId);

		AccountingRecord res = new AccountingRecord();
		res.setRound(inv);
		res.setBookkeeper(b);

		return res;
	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);

		entity.setCreationMoment(moment);

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<AccountingRecord> request, final Response<AccountingRecord> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
