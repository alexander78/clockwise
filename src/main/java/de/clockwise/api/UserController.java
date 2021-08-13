package de.clockwise.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.clockwise.model.User;
import de.clockwise.persistence.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepos;

	@GetMapping("/user/getAll")
	public List<User> getAll() {
		ArrayList<User> users = new ArrayList<>();
		Iterable<User> findAll = userRepos.findAll();
		findAll.forEach(C -> users.add(C));
		return users;
	}

	@GetMapping("/user/getByMail")
	public User getByMail(@RequestParam String email) {
		User user = userRepos.findByEmail(email);
		return user;
	}

}
