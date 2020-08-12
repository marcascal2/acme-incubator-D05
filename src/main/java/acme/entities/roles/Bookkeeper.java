
package acme.entities.roles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import acme.entities.acountingRecords.AccountingRecord;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bookkeeper extends UserRole {

	/**
	 *
	 */
	private static final long				serialVersionUID	= 1L;

	@NotBlank
	private String							firmName;

	@NotBlank
	private String							responsibility;

	@OneToMany(mappedBy = "bookkeeper")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<AccountingRecord>	records;

}
