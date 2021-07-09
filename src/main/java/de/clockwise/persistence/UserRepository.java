package de.clockwise.persistence;

import org.springframework.data.repository.CrudRepository;

import de.clockwise.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
