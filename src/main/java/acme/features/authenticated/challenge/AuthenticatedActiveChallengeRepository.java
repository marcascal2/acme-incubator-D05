
package acme.features.authenticated.challenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedActiveChallengeRepository extends AbstractRepository {

	@Query("select c from Challenge c where c.deadline >= now()")
	Collection<Challenge> findActives();

	@Query("select ua from Challenge ua where ua.id = ?1")
	Challenge findOneById(Integer id);

}
