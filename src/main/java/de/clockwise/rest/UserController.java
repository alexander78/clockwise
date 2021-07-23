package de.clockwise.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.clockwise.model.User;
import de.clockwise.persistence.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepos;

	@GetMapping("api/user/getAll")
	public List<User> getAll() {
		ArrayList<User> users = new ArrayList<>();
		Iterable<User> findAll = userRepos.findAll();
		findAll.forEach(C -> users.add(C));
		return users;
	}

}
