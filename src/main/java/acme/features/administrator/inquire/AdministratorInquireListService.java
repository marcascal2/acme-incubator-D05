
package acme.features.administrator.inquire;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquires.Inquire;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorInquireListService implements AbstractListService<Administrator, Inquire> {

	@Autowired
	AdministratorInquireRepository repository;


	@Override
	public boolean authorise(final Request<Inquire> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Inquire> request, final Inquire entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "deadline", "title", "maxMoney", "minMoney");

	}

	@Override
	public Collection<Inquire> findMany(final Request<Inquire> request) {
		assert request != null;

		Collection<Inquire> result;

		result = this.repository.findManyAll();

		return result;
	}

}
