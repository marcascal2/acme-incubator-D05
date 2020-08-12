/*
 * AuthenticatedUserAccountController.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.discussionForum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.discussionForums.DiscussionForum;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/discussion-forum/")
public class AuthenticatedDiscussionForumController extends AbstractController<Authenticated, DiscussionForum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedDiscussionForumListService		listService;

	@Autowired
	private AuthenticatedDiscussionForumShowService		showService;

	@Autowired
	private AuthenticatedDiscussionForumCreateService	createService;

	@Autowired
	private AuthenticatedDiscussionForumDeleteService	deleteService;

	@Autowired
	private AuthenticatedDiscussionForumUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
