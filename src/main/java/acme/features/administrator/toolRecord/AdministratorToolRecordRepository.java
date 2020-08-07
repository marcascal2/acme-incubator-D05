
package acme.features.administrator.toolRecord;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activitySectors.ActivitySector;
import acme.entities.toolRecords.ToolRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorToolRecordRepository extends AbstractRepository {

	@Query("select a from ToolRecord a")
	Collection<ToolRecord> findManyAll();

	@Query("select a from ToolRecord a where a.id = ?1")
	ToolRecord findOneById(Integer id);

	@Query("select a.sector from ActivitySector a")
	List<String> findActivitiesSectors();

	@Query("select a from ActivitySector a where a.sector = ?1")
	ActivitySector findSector(String sector);

}
