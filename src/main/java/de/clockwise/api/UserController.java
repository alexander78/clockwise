package de.clockwise.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.clockwise.model.User;
import de.clockwise.persistence.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepos;

	@GetMapping("/getAll")
	public List<User> getAll() {
		ArrayList<User> users = new ArrayList<>();
		Iterable<User> findAll = userRepos.findAll();
		findAll.forEach(C -> users.add(C));
		return users;
	}
	
	@GetMapping("/get/{id}")
	public User get(@PathVariable long id) {
        return userRepos.findById(id).orElseThrow(RuntimeException::new);
    }

	@GetMapping("/getByMail/{email}")
	public User getByMail(@PathVariable String email) {
		return userRepos.findByEmail(email);
	}
	
	@PutMapping("/save/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        User currentUser = userRepos.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setEmail(user.getEmail());
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        currentUser.setPersonalNr(user.getPersonalNr());
        currentUser = userRepos.save(user);

        return ResponseEntity.ok(currentUser);
    }
	
	@PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
		User savedUser = userRepos.save(user);
        return ResponseEntity.created(new URI("/users/" + savedUser.getId())).body(savedUser);
    }

}
