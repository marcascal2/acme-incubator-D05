
package acme.features.administrator.spamWord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spamWords.SpamList;
import acme.entities.spamWords.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamListRepository extends AbstractRepository {

	@Query("select s from SpamList s where s.id = ?1")
	SpamList findOneById(int id);

	@Query("select s from SpamList s")
	Collection<SpamList> findManyAll();

	@Query("select a from SpamWord a where a.spamList.id = ?1")
	Collection<SpamWord> findManySpamwordsById(int id);

	@Query("select a from SpamWord a where a.englishTranslation = ?1 AND a.spamList.id = ?2")
	SpamWord findOneSpamword(String spamword, int id);

	@Modifying
	@Query("delete from SpamWord where id = ?1")
	void deleteSpamWord(int id);

}
