
package acme.features.anonymous.coletoBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.ColetoBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousColetoBulletinRepository extends AbstractRepository {

	@Query("select c from ColetoBulletin c")
	Collection<ColetoBulletin> findMany();
}
