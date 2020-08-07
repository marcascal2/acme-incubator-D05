
package acme.entities.spamWords;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SpamList extends DomainEntity {

	/**
	 *
	 */
	private static final long			serialVersionUID	= 1L;

	@NotNull
	private Double						spamThreshold;

	@NotNull
	@Valid
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "spamList")
	private Collection<@Valid SpamWord>	spamWords;
}
