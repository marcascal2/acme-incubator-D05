
package acme.features.administrator.activitySector;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.activitySectors.ActivitySector;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/activity-sector/")
public class AdministratorActivitySectorController extends AbstractController<Administrator, ActivitySector> {

	//Internal state
	@Autowired
	private AdministratorActivitySectorListService		listService;

	@Autowired
	private AdministratorActivitySectorShowService		showService;

	@Autowired
	private AdministratorActivitySectorUpdateService	updateService;


	//Constructors
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
