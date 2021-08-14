package de.clockwise.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.clockwise.model.WorkingtimeModel;

public interface WorkingtimeModelRepository extends CrudRepository<WorkingtimeModel, Long> {

	@Query(value = "SELECT * FROM WORKINGTIMEMODEL WHERE USER_ID = ?1", nativeQuery = true)
	List<WorkingtimeModel> findAllByUser(long userId);

}
