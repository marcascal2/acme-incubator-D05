
package acme.features.administrator.technologyRecords;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activitySectors.ActivitySector;
import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTechnologyRecordsRepository extends AbstractRepository {

	@Query("select a from TechnologyRecord a")
	Collection<TechnologyRecord> findManyOrder();

	@Query("select t from TechnologyRecord t where t.id = ?1")
	TechnologyRecord findTechRecordById(Integer integer);

	@Query("select a.sector from ActivitySector a")
	List<String> findActivitiesSectors();

	@Query("select a from ActivitySector a where a.sector = ?1")
	ActivitySector findSector(String sector);
}
