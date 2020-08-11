
package acme.features.authenticated.bookkeeper;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBookkeeperRequestRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedById(int id);

	@Query("select a from BookkeeperRequest a where a.authenticated.userAccount.id = ?1")
	BookkeeperRequest findBookkeeperRequestByUserId(int id);

}
