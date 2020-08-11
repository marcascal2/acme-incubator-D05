
package acme.features.administrator.bookkeeperRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBookkeeperRequestRepository extends AbstractRepository {

	@Query("select a from BookkeeperRequest a")
	Collection<BookkeeperRequest> findAllRequest();

	@Query("select a from BookkeeperRequest a where a.id = ?1")
	BookkeeperRequest findBookkeeperRequestById(Integer integer);

}
