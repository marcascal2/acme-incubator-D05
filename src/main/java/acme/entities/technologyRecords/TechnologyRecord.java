
package acme.entities.technologyRecords;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.entities.activitySectors.ActivitySector;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TechnologyRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@Valid
	@OneToOne()
	private ActivitySector		activitySector;

	@NotBlank
	private String				inventor;

	@NotBlank
	private String				description;

	@URL
	@NotBlank
	private String				website;

	@NotBlank
	@Email
	private String				email;

	@NotNull
	private Boolean				openSource;

	@Range(min = -5, max = 5)
	private Integer				stars;
}
