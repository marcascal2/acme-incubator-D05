
package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.acountingRecords.AccountingRecord;
import acme.entities.acountingRecords.StatusAR;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BookkeeperAccountingRecordListService implements AbstractListService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "status", "title");
	}

	@Override
	public Collection<AccountingRecord> findMany(final Request<AccountingRecord> request) {
		assert request != null;
		Collection<AccountingRecord> result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findManyByInvestmentId(id);

		result = result.stream().filter(a -> a.getStatus() == StatusAR.PUBLISHED).collect(Collectors.toList()); //Solo se muestran los que están published

		return result;
	}

}
