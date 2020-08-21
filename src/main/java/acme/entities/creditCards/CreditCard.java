
package acme.entities.creditCards;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.entities.roles.Patron;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				holder;

	@NotBlank
	@CreditCardNumber
	private String				number;

	@NotBlank
	private String				brand;

	@NotBlank
	@Pattern(regexp = "[0-9]{2}/[0-9]{4}")
	private String				expirationDate;

	@NotBlank
	@Pattern(regexp = "[0-9]{3}")
	private String				cvv;

	@Valid
	@OneToOne(optional = true)
	private Patron				patron;

}
