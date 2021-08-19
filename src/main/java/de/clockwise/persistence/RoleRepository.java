package de.clockwise.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.clockwise.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	@Query(value = "SELECT * FROM ROLE R LEFT JOIN USER_ROLES UR ON R.ID = UR.ROLE_ID WHERE UR.USER_ID=?1", nativeQuery = true)
	List<Role> findAllByUser(long userId);
	
	@Query(value = "DELETE FROM USER_ROLES WHERE USER_ID=?1 and role_id=?2", nativeQuery = true)
	List<Role> deleteFromUser(long userId, long roleId);
	
	@Query(value = "INSERT INTO USER_ROLES (USER_ID, ROLE_ID) VALUES (:userId, :roleId)", nativeQuery = true)
	List<Role> addToUser(long userId, long roleId);

}
