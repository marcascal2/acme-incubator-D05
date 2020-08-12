
package acme.features.authenticated.card;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedCardRepository extends AbstractRepository {

	@Query("select d from Patron d where d.id = ?1")
	Patron findOnePatronByUserAccountId(int id);

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findOneById(int id);
}
