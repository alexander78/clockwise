package de.clockwise.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.clockwise.model.Project;
import de.clockwise.persistence.ProjectRepository;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepos;

	@GetMapping("/getAll")
	public List<Project> getAll() {
		ArrayList<Project> projects = new ArrayList<>();
		Iterable<Project> found = projectRepos.findAll();
		found.forEach(C -> projects.add(C));
		return projects;
	}
	
	@GetMapping("/get/{id}")
	public Project get(@PathVariable long id) {
        return projectRepos.findById(id).orElseThrow(RuntimeException::new);
    }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable long id) {
		projectRepos.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
