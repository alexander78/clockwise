package de.clockwise.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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

import de.clockwise.model.WorkingtimeModel;
import de.clockwise.persistence.WorkingtimeModelRepository;

@RestController
@RequestMapping("/api/workingtimemodel")
public class WorkingtimeModelController {

	@Autowired
	private WorkingtimeModelRepository workingtimeModelRepos;

	@GetMapping("/getAll")
	public List<WorkingtimeModel> getAll() {
		ArrayList<WorkingtimeModel> models = new ArrayList<>();
		Iterable<WorkingtimeModel> found = workingtimeModelRepos.findAll();
		found.forEach(C -> models.add(C));
		return models;
	}

	@GetMapping("/getAllByUser/{userid}")
	public List<WorkingtimeModel> getAllByUser(@PathVariable long userid) {
		return workingtimeModelRepos.findAllByUser(userid);
	}

	@GetMapping("/get/{id}")
	public WorkingtimeModel get(@PathVariable long id) {
		return workingtimeModelRepos.findById(id).orElseThrow(RuntimeException::new);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable long id) {
		workingtimeModelRepos.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/save/{id}")
	public ResponseEntity updateWorkingtimeModel(@PathVariable Long id, @RequestBody WorkingtimeModel model) {
		WorkingtimeModel currentModel = workingtimeModelRepos.findById(id).orElseThrow(RuntimeException::new);
		currentModel.setVacationDays(model.getVacationDays());
		currentModel.setValidFrom(model.getValidFrom());
		currentModel.setValidTo(model.getValidTo());
		currentModel.setWorkingModelTyp(model.getWorkingModelTyp());
		currentModel = workingtimeModelRepos.save(model);
		return ResponseEntity.ok(currentModel);
	}

	@PostMapping("/create")
	public ResponseEntity createWorkingtimeModel(@RequestBody WorkingtimeModel model) throws URISyntaxException {
		WorkingtimeModel savedModel = workingtimeModelRepos.save(model);
		return ResponseEntity.created(new URI("/workingtimemodels/" + savedModel.getId())).body(savedModel);
	}
}
