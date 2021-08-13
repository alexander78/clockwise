package de.clockwise.persistence;

import org.springframework.data.repository.CrudRepository;

import de.clockwise.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

}
