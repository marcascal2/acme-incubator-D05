
package acme.features.anonymous.ruizbulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.ruizbulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/ruizbulletin/")
public class AnonymousruizbulletinController extends AbstractController<Anonymous, ruizbulletin> {

	//Internal State ----------------------------------------------

	@Autowired
	private AnonymousruizbulletinListService	listService;

	@Autowired
	private AnonymousruizbulletinCreateService	createService;


	//Constructors-------------------------------------------------
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);

	}

}
