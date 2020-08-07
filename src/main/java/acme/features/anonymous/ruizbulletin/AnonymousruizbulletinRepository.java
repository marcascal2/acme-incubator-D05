
package acme.features.anonymous.ruizbulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.bulletins.ruizbulletin;
import acme.framework.repositories.AbstractRepository;

public interface AnonymousruizbulletinRepository extends AbstractRepository {

	@Query("select b from ruizbulletin b")
	Collection<ruizbulletin> findMany();
}
