
package acme.features.anonymous.casasolaBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.CasasolaBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/casasola-bulletin/")
public class AnonymousCasasolaBulletinController extends AbstractController<Anonymous, CasasolaBulletin> {

	//Internal State ----------------------------------------------

	@Autowired
	private AnonymousCasasolaBulletinListService	listService;

	@Autowired
	private AnonymousCasasolaBulletinCreateService	createService;


	//Constructors-------------------------------------------------
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
