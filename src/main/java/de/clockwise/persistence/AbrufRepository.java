package de.clockwise.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.clockwise.model.Abruf;

public interface AbrufRepository extends CrudRepository<Abruf, Long> {

	@Query(value = "SELECT * FROM ABRUF WHERE PROJECT_ID = ?1", nativeQuery = true)
	List<Abruf> findAllByProject(long projectId);
}
