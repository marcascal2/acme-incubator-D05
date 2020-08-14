
package acme.features.authenticated.card;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedCardRepository extends AbstractRepository {

	@Query("select d from Patron d where d.userAccount.id = ?1")
	Patron findOnePatronByUserAccountId(int id);

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findOneById(int id);

	@Query("select c.patron from CreditCard c where c.id = ?1")
	Patron findPatronByCardId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select p from Patron p where p.id = ?1")
	Patron findPatronById(int id);
}
