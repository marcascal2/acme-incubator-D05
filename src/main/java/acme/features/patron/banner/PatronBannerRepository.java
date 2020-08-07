
package acme.features.patron.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronBannerRepository extends AbstractRepository {

	@Query("select c from Banner c where c.id = ?1")
	Banner findOneById(int id);

	@Query("select c from Banner c where c.patron.id = ?1")
	Collection<Banner> findManyByPatronId(int patronId);

	@Query("select e from Patron e where e.id = ?1")
	Patron findPatronById(int patronId);
}
