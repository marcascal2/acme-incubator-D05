
package acme.features.administrator.spamWord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spamWords.SpamList;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spam-list/")
public class AdministratorSpamListController extends AbstractController<Administrator, SpamList> {

	//Internal state
	@Autowired
	private AdministratorSpamListListService	listService;

	@Autowired
	private AdministratorSpamListShowService	showService;

	@Autowired
	private AdministratorSpamListUpdateService	updateService;


	//Constructors
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
