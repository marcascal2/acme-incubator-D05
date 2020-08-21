/*
 * AuthenticatedEntrepreneurRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.entrepreneur;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activitySectors.ActivitySector;
import acme.entities.roles.Entrepreneur;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedEntrepreneurRepository extends AbstractRepository {

	@Query("select d from Entrepreneur d where d.id = ?1")
	Entrepreneur findOneEntrepreneurByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select a.sector from ActivitySector a")
	List<String> findActivitiesSectors();

	@Query("select a from ActivitySector a where a.sector = ?1")
	ActivitySector findSector(String sector);
}
