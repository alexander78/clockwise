package de.clockwise;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.clockwise.model.User;
import de.clockwise.model.UserRole;
import de.clockwise.persistence.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository repository;

	@Autowired
	public DatabaseLoader(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.save(createUserData("alexander.thoms@onlinehome.de", "Alexander", "Thoms"));
		this.repository.save(createUserData("belia.thoms@onlinehome.de", "Belia", "Thoms"));
		this.repository.save(createUserData("elias.thoms@onlinehome.de", "Elias", "Thoms"));
		this.repository.save(createUserData("irmgard.thoms@onlinehome.de", "Irmgard", "Thoms"));
		this.repository.save(createUserData("sebastian.thoms@onlinehome.de", "Sebasitan", "Thoms"));
		this.repository.save(createUserData("claudia.thoms@onlinehome.de", "Claudia", "Thoms"));
	}

	private User createUserData(String email, String firstname, String lastname) {
		User user = new User();
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		UserRole e = new UserRole();
		HashSet<UserRole> set = new HashSet<>();
		set.add(e);
		e.setRoleName("User");
		user.setUserRoles(set);
		return user;
	}
}