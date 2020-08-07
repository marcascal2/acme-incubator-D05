
package acme.features.anonymous.casasolaBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.CasasolaBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCasasolaBulletinRepository extends AbstractRepository {

	@Query("select a from CasasolaBulletin a")
	Collection<CasasolaBulletin> findManyAll();
}
