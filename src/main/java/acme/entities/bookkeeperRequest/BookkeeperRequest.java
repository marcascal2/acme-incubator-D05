
package acme.entities.bookkeeperRequest;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookkeeperRequest extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "ACCEPTED|REJECTED|PENDING")
	private String				status;

	@NotBlank
	private String				firmName;

	@NotBlank
	private String				responsibility;

	@OneToOne(optional = false)
	private Authenticated		authenticated;

}
