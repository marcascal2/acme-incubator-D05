
package acme.entities.investmentRounds;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.URL;

import acme.entities.acountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvestmentRound extends DomainEntity {

	private static final long			serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{3}([0-9]{2})?-[0-9]{2}-[0-9]{6}")
	private String						ticker;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	private Date						creationDate;

	@NotNull
	private KindOfRound					kindOfRound;

	@NotBlank
	private String						title;

	@NotBlank
	private String						description;

	@NotNull
	@Valid
	private Money						amount;

	@URL
	private String						link;

	@NotNull
	@ManyToOne(optional = false)
	@Valid
	private Entrepreneur				entrepreneur;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "round")
	private Collection<Activity>		workProgramme;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "investmentApplied")
	private List<InvestmentApplication>	application;

	@OneToMany(mappedBy = "round")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AccountingRecord>		record;

	@Valid
	@OneToOne(mappedBy = "investmentRound")
	private DiscussionForum				forum;


	@Transient
	public boolean sumUp() {
		Double sum = 0.;
		Boolean result = false;
		for (Activity a : this.workProgramme) {
			sum += a.getAmount().getAmount();
		}
		if (this.amount.getAmount().equals(sum)) {
			result = true;
		}
		return result;
	}

}
