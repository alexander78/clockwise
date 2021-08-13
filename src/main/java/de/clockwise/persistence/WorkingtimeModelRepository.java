package de.clockwise.persistence;

import org.springframework.data.repository.CrudRepository;

import de.clockwise.model.User;
import de.clockwise.model.WorkingtimeModel;

public interface WorkingtimeModelRepository extends CrudRepository<WorkingtimeModel, Long> {

	
}
