
package acme.features.administrator.notice;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	@Autowired
	private AdministratorNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate");

	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "headerPicture", "title", "deadline", "body", "relatedNotices");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		assert request != null;

		Notice res;

		res = new Notice();

		Date moment = new Date(System.currentTimeMillis() - 1);
		res.setCreationDate(moment);

		return res;
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted;

		String requestNotices = (String) request.getModel().getAttribute("relatedNotices");

		List<String> relatedNotice = this.splitNotices(requestNotices);
		List<String> validateLinks = new ArrayList<>();

		if (request.getModel().getAttribute("relatedNotices").toString() != "") {
			for (String a : relatedNotice) {
				if (this.isValid(a)) {
					validateLinks.add(a);
				} else {
					//Eliminamos lo que hay en related notice para que no vuelva a salir en el campo con path=relatedNotices
					request.getModel().getAttribute("relatedNotices");
					Model model = request.getModel();
					model.setAttribute("relatedNotices", new ArrayList<>());
					request.setModel(model);

					entity.setRelatedNotices(new ArrayList<>());

					errors.state(request, this.isValid(a), "relatedNotices", "administrator.notice.errorLink");
				}

			}

		}
		entity.setRelatedNotices(validateLinks);

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "anonymous.user-account.error.must-accept");
	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	private List<String> splitNotices(final String requestNotices) {

		List<String> result = new ArrayList<>();

		String[] parts = requestNotices.split(",");

		for (String a : parts) {

			result.add(a);

		}

		return result;
	}

	private boolean isValid(final String url) {
		try {
			new URL(url).toURI();
			return true;
		}

		catch (Exception e) {
			return false;
		}
	}

}
