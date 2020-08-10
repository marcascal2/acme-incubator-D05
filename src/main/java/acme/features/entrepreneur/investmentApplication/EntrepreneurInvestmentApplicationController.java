
package acme.features.entrepreneur.investmentApplication;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/investment-application/")
public class EntrepreneurInvestmentApplicationController extends AbstractController<Entrepreneur, InvestmentApplication> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurInvestmentApplicationListService	listService;

	@Autowired
	private EntrepreneurInvestmentApplicationShowService	showService;

	@Autowired
	private EntrepreneurInvestmentApplicationUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
