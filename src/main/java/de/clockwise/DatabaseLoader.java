package de.clockwise;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.clockwise.model.User;
import de.clockwise.model.Abruf;
import de.clockwise.model.Project;
import de.clockwise.model.Role;
import de.clockwise.model.WorkingModelType;
import de.clockwise.model.WorkingtimeModel;
import de.clockwise.persistence.AbrufRepository;
import de.clockwise.persistence.ProjectRepository;
import de.clockwise.persistence.UserRepository;
import de.clockwise.persistence.WorkingtimeModelRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository userRepos;
	private final WorkingtimeModelRepository workingRepos;
	private final ProjectRepository projectRepos;
	private final AbrufRepository abrufRepos;

	@Autowired
	public DatabaseLoader(UserRepository repository, WorkingtimeModelRepository workingRepos, ProjectRepository projectRepos, AbrufRepository abrufRepos) {
		this.userRepos = repository;
		this.workingRepos = workingRepos;
		this.projectRepos = projectRepos;
		this.abrufRepos = abrufRepos;
	}

	@Override
	public void run(String... strings) throws Exception {
		createUserProfile(createUserData("alexander.thoms@onlinehome.de", "Alexander", "Thoms"));
		createUserProfile(createUserData("belia.thoms@onlinehome.de", "Belia", "Thoms"));
		createUserProfile(createUserData("elias.thoms@onlinehome.de", "Elias", "Thoms"));
		createUserProfile(createUserData("irmgard.thoms@onlinehome.de", "Irmgard", "Thoms"));
		createUserProfile(createUserData("sebastian.thoms@onlinehome.de", "Sebasitan", "Thoms"));
		createUserProfile(createUserData("claudia.thoms@onlinehome.de", "Claudia", "Thoms"));
		
		createProject("I0001-987", "Urlaub");
		createProject("I0002-987", "Krank");
		createProject("I0003-987", "Gleitzeit");
		createProject("E0001-3323", "ClockwiseUi");
		createProject("E0001-2344", "Clockwise");
	}

	private void createUserProfile(User user) {
		User savedUser = this.userRepos.save(user);
		this.workingRepos.save(createWorkingtimeModel(savedUser));
		if(user.getFirstname().equals("Alexander")) {
			this.workingRepos.save(createWorkingtimeModel(savedUser));
		}
	}

	private User createUserData(String email, String firstname, String lastname) {
		User user = new User();
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		Role e = new Role();
		HashSet<Role> set = new HashSet<>();
		set.add(e);
		e.setRoleName("User");
		user.setRoles(set);
		return user;
	}

	private WorkingtimeModel createWorkingtimeModel(User user) {
		WorkingtimeModel workingtimeModel = new WorkingtimeModel();
		workingtimeModel.setUser(user);
		workingtimeModel.setVacationDays(30);
		workingtimeModel.setWorkingModelTyp(WorkingModelType.VOLLZEIT);
		workingtimeModel.setValidFrom(Date.from(Instant.parse("2012-08-01T00:00:00.00Z")));
		workingtimeModel.setValidTo(Date.from(Instant.parse("2099-08-01T00:00:00.00Z")));
		workingtimeModel.setHoursOnMonday(8.0);
		workingtimeModel.setHoursOnTuesday(8.0);
		workingtimeModel.setHoursOnWednesday(8.0);
		workingtimeModel.setHoursOnThursday(8.0);
		workingtimeModel.setHoursOnFriday(8.0);
		workingtimeModel.setMonday(true);
		workingtimeModel.setTuesday(true);
		workingtimeModel.setWednesday(true);
		workingtimeModel.setThursday(true);
		workingtimeModel.setFriday(true);
		return workingtimeModel;
	}
	
	private void createProject(String fachid, String bezeichnung) {
		Project p = new Project();
		p.setFachId(fachid);
		p.setBezeichnung(bezeichnung);
		HashSet<Abruf> abrufe = new HashSet<Abruf>();
		Project save = projectRepos.save(p);
		abrufe.add(createAbruf(save));
		abrufe.add(createAbruf(save));
		
	}

	private Abruf createAbruf(Project project) {
		Abruf abruf = new Abruf();
		abruf.setAbrufNummer(String.valueOf("A-"+ (int)(Math.random()*1000000)));
		abruf.setRahmenBmNummer("123456798");
		abruf.setValidFrom(Date.from(Instant.parse("2021-01-01T00:00:00.00Z")));
		abruf.setValidTo(Date.from(Instant.parse("2021-08-30T00:00:00.00Z")));
		abruf.setProject(project);
		return abrufRepos.save(abruf);
	}
}