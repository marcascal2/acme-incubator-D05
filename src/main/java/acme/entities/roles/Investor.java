
package acme.entities.roles;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	private static final long				serialVersionUID	= 1L;

	//Atributes -------------------------------------

	@NotBlank
	private String							firmName;

	@NotNull
	@Valid
	@OneToOne()
	private ActivitySector					sector;

	@NotBlank
	private String							profile;


	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<@Valid DiscussionForum>	forum;

}
