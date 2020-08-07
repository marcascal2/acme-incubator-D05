
package acme.features.authenticated.toolRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolRecords.ToolRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedToolRecordRepository extends AbstractRepository {

	@Query("select b from ToolRecord b where b.id = ?1")
	ToolRecord findOneById(int id);

	@Query("select a from ToolRecord a")
	Collection<ToolRecord> findMany();

}
