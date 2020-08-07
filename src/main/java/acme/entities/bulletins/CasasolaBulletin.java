
package acme.entities.bulletins;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CasasolaBulletin extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Atributes ---------------------------------

	@NotBlank
	private String				surname;

	@NotBlank
	private String				name;

	@NotBlank
	private String				favouriteColor;

	@NotNull
	private Integer				favouriteNumber;
}
