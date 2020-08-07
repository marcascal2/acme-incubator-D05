
package acme.features.anonymous.technologyRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTechnologyRecordsRepository extends AbstractRepository {

	@Query("select a from TechnologyRecord a group by stars, activitySector")
	Collection<TechnologyRecord> findManyOrder();

	@Query("select t from TechnologyRecord t where t.id = ?1")
	TechnologyRecord findTechRecordById(Integer integer);

}
