
package acme.entities.discussionForums;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import acme.entities.investmentApplications.ApplicationStatus;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.entities.roles.Investor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DiscussionForum extends DomainEntity {

	private static final long			serialVersionUID	= 1L;

	@OneToOne(optional = false)
	private InvestmentRound				investmentRound;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany
	private Collection<@Valid Message>	messages;

	@OneToOne(optional = false)
	private Investor					investor;


	@Transient
	public Boolean canPost(final InvestmentApplication app) {
		Boolean result = false;
		if (app.getStatus().equals(ApplicationStatus.ACCEPTED)) {
			result = true;
		}
		return result;
	}

}
