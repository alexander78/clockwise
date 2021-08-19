package de.clockwise.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.clockwise.model.Abruf;
import de.clockwise.model.Project;
import de.clockwise.model.Role;
import de.clockwise.model.User;
import de.clockwise.model.WorkingtimeModel;
import de.clockwise.persistence.RoleRepository;
import de.clockwise.persistence.UserRepository;
import de.clockwise.persistence.WorkingtimeModelRepository;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepos;
	
	@Autowired
	private UserRepository userRepos;

	@GetMapping("/getAll")
	public List<Role> getAll() {
		ArrayList<Role> roles = new ArrayList<>();
		Iterable<Role> found = roleRepos.findAll();
		found.forEach(C -> roles.add(C));
		return roles;
	}

	@GetMapping("/getAllByUser/{userid}")
	public List<Role> getAllByUser(@PathVariable long userid) {
		return roleRepos.findAllByUser(userid);
	}

	@GetMapping("/get/{id}")
	public Role get(@PathVariable long id) {
		return roleRepos.findById(id).orElseThrow(RuntimeException::new);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable long id) {
		roleRepos.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/removeFromUser/{userid}/{roleid}")
	public ResponseEntity removeFromUser(@PathVariable long userid, @PathVariable long roleid) {
		Optional<Role> findById = roleRepos.findById(roleid);
		Optional<User> findById2 = userRepos.findById(userid);
		if(findById.isPresent() && findById2.isPresent()) {
			findById2.get().removeRole(findById.get());
		}
		userRepos.save(findById2.get());
		return ResponseEntity.ok().build();
	}

	@PutMapping("/save/{id}")
	public ResponseEntity updateRole(@PathVariable Long id, @RequestBody Role role) {
		Role currentRole = roleRepos.findById(id).orElseThrow(RuntimeException::new);
		currentRole.setRoleName(role.getRoleName());
		currentRole.setRoleDescription(role.getRoleDescription());
		currentRole = roleRepos.save(currentRole);
		return ResponseEntity.ok(currentRole);
	}

	@PostMapping("/create")
	public ResponseEntity createWorkingtimeModel(@RequestBody Role role) throws URISyntaxException {
		Role savedModel = roleRepos.save(role);
		return ResponseEntity.created(new URI("/roles/" + savedModel.getId())).body(savedModel);
	}
}
