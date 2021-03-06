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

package acme.features.authenticated.inquire;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquires.Inquire;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInquireRepository extends AbstractRepository {

	@Query("select ua from Inquire ua where ua.id = ?1")
	Inquire findOneInquireById(int id);

	@Query("select o from Inquire o where o.deadline >= now()")
	Collection<Inquire> findActives();

}
