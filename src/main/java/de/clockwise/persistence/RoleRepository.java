package de.clockwise.persistence;

import org.springframework.data.repository.CrudRepository;

import de.clockwise.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
