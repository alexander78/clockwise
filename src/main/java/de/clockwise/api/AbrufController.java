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
import de.clockwise.persistence.AbrufRepository;
import de.clockwise.persistence.ProjectRepository;

@RestController
@RequestMapping("/api/abruf")
public class AbrufController {

	@Autowired
	private AbrufRepository abrufRepos;
	@Autowired
	private ProjectRepository projectRepos;

	@GetMapping("/getAll")
	public List<Abruf> getAll() {
		ArrayList<Abruf> models = new ArrayList<>();
		Iterable<Abruf> found = abrufRepos.findAll();
		found.forEach(C -> models.add(C));
		return models;
	}

	@GetMapping("/getAllByProject/{projectid}")
	public List<Abruf> getAllByUser(@PathVariable long projectid) {
		return abrufRepos.findAllByProject(projectid);
	}

	@GetMapping("/get/{id}")
	public Abruf get(@PathVariable long id) {
		return abrufRepos.findById(id).orElseThrow(RuntimeException::new);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable long id) {
		abrufRepos.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/save/{id}")
	public ResponseEntity updateAbruf(@PathVariable Long id, @RequestBody Abruf abruf) {
		Abruf currentAbruf = abrufRepos.findById(id).orElseThrow(RuntimeException::new);
		currentAbruf.setAbrufNummer(abruf.getAbrufNummer());
		currentAbruf.setValidFrom(abruf.getValidFrom());
		currentAbruf.setValidTo(abruf.getValidTo());
		currentAbruf.setRahmenBmNummer(abruf.getRahmenBmNummer());
		currentAbruf = abrufRepos.save(currentAbruf);
		return ResponseEntity.ok(currentAbruf);
	}

	@PostMapping("/create/{projectId}")
	public ResponseEntity createWorkingtimeModel(@PathVariable long projectId, @RequestBody Abruf abruf) throws URISyntaxException {
		Project project = projectRepos.findById(projectId).orElseThrow(RuntimeException::new);
		abruf.setProject(project);
		Abruf savedAbruf = abrufRepos.save(abruf);
		return ResponseEntity.created(new URI("/abruf/" + savedAbruf.getId()+"/"+projectId)).body(savedAbruf);
	}
}
