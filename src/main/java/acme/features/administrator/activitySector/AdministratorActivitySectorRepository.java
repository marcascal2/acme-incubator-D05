
package acme.features.administrator.activitySector;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activitySectors.ActivitySector;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorActivitySectorRepository extends AbstractRepository {

	@Query("select s from ActivitySector s where s.id = ?1")
	ActivitySector findOneById(int id);

	@Query("select s from ActivitySector s")
	Collection<ActivitySector> findManyAll();
}
