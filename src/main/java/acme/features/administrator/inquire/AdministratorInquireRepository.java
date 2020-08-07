
package acme.features.administrator.inquire;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquires.Inquire;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInquireRepository extends AbstractRepository {

	@Query("select a from Inquire a")
	Collection<Inquire> findManyAll();

	@Query("select a from Inquire a where a.id = ?1")
	Inquire findOneById(Integer id);

}
