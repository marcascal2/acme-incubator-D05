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

package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.entities.spamWords.SpamList;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select o from Message o where o.id = ?1")
	Message findOneById(int id);

	@Query("select i from Message i where i.forum.id = ?1")
	Collection<Message> findManyByForumId(int forumId);

	@Query("select o from DiscussionForum o where o.id = ?1")
	DiscussionForum findDiscussionForum(int forumId);

	@Query("select a from Entrepreneur a where a.userAccount.id = ?1")
	Entrepreneur findEntrepreneurById(int entrepreneurId);

	@Query("select a from Investor a where a.userAccount.id = ?1")
	Investor findInvestorById(int investorId);

	@Query("select w.englishTranslation from SpamWord w")
	Collection<String> findAllSpamWordsEnglish();

	@Query("select w.spanishTranslation from SpamWord w")
	Collection<String> findAllSpamWordsSpanish();

	@Query("select w from SpamList w")
	SpamList findSpamList();

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int accountId);

}
