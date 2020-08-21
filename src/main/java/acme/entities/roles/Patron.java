
package acme.entities.roles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patron extends UserRole {

	/**
	 *
	 */
	private static final long			serialVersionUID	= 1L;

	@NotBlank
	private String						organisationName;

	@OneToOne(optional = true)
	@Valid
	private CreditCard					card;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "patron")
	private Collection<@Valid Banner>	banners;
}
