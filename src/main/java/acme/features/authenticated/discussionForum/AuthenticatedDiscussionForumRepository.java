/*
 * AuthenticatedUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.discussionForum;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentApplications.InvestmentApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDiscussionForumRepository extends AbstractRepository {

	@Query("select a from DiscussionForum a where a.investmentRound.entrepreneur.userAccount.id = ?1")
	Collection<DiscussionForum> findManyByInvestment(int activeRoleId);

	@Query("select a from DiscussionForum a")
	Collection<DiscussionForum> findAllDiscussionforum();

	@Query("select o from DiscussionForum o where o.id = ?1")
	DiscussionForum findOneById(int id);

	@Query("select a from Entrepreneur a where a.userAccount.id = ?1")
	Entrepreneur findEntrepreneurById(int entrepreneurId);

	@Query("select a from Investor a where a.userAccount.id = ?1")
	Investor findInvestorById(int investorId);

	@Query("select a from InvestmentApplication a where a.investor.userAccount.id = ?1")
	Collection<InvestmentApplication> findApplicationsByInvestor(int investorId);

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findInvestmentRoundById(Integer investorRecordId);

	@Query("select a from UserAccount a where a.id = ?1")
	UserAccount findOneUserAccountById(int idUA);

	@Query("select a.entrepreneur.userAccount from InvestmentRound a where a.id = ?1")
	UserAccount findEntrepreneurUserAccount(int invId);

	@Query("select a from Investor a where a.userAccount.id = ?1")
	Investor findInvestorByUserAccountId(int userId);

	@Transactional
	@Modifying
	@Query("delete from Message a where a.forum.id = ?1")
	void deleteMessages(int forumId);

	@Query("select a from Message a")
	Collection<Message> findAllMessages();

}
