
package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.activitySectors.ActivitySector;
import acme.entities.discussionForums.DiscussionForum;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Investor extends UserRole {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	//Atributes -------------------------------------

	@NotBlank
	private String				firmName;

	@NotNull
	@Valid
	@OneToOne()
	private ActivitySector		sector;

	@NotBlank
	private String				profile;

	@Valid
	@ManyToOne(optional = true)
	private DiscussionForum		forum;

}
