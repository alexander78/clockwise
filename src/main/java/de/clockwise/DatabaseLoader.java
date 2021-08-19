package de.clockwise;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.clockwise.model.Abruf;
import de.clockwise.model.Project;
import de.clockwise.model.Role;
import de.clockwise.model.User;
import de.clockwise.model.WorkingModelType;
import de.clockwise.model.WorkingtimeModel;
import de.clockwise.persistence.AbrufRepository;
import de.clockwise.persistence.ProjectRepository;
import de.clockwise.persistence.RoleRepository;
import de.clockwise.persistence.UserRepository;
import de.clockwise.persistence.WorkingtimeModelRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository userRepos;
	private final WorkingtimeModelRepository workingRepos;
	private final ProjectRepository projectRepos;
	private final AbrufRepository abrufRepos;
	private final RoleRepository roleRepos;

	@Autowired
	public DatabaseLoader(UserRepository repository, WorkingtimeModelRepository workingRepos, ProjectRepository projectRepos, AbrufRepository abrufRepos, RoleRepository roleRepos) {
		this.userRepos = repository;
		this.workingRepos = workingRepos;
		this.projectRepos = projectRepos;
		this.abrufRepos = abrufRepos;
		this.roleRepos = roleRepos;
	}

	@Override
	public void run(String... strings) throws Exception {
		
		HashSet set = new HashSet<Role>();
		set.add(createRole("ADMIN"));
		set.add(createRole("USER"));
		createRole("PROJECTMANAGER");
		
		createUserProfile(createUserData("alexander.t@online.de", "Alexander", "Müller"), set);
		createUserProfile(createUserData("belia.t@online.de", "Belia", "Müller"), set);
		createUserProfile(createUserData("elias.t@online.de", "Elias", "Müller"), set);
		createUserProfile(createUserData("irmgard.t@online.de", "Irmgard", "Müller"), set);
		createUserProfile(createUserData("sebastian.t@online.de", "Sebasitan", "Müller"), set);
		createUserProfile(createUserData("claudia.t@online.de", "Claudia", "Müller"), set);
		
		createProject("I0001-987", "Urlaub");
		createProject("I0002-987", "Krank");
		createProject("I0003-987", "Gleitzeit");
		createProject("E0001-3323", "ClockwiseUi");
		createProject("E0001-2344", "Clockwise");
	}

	private void createUserProfile(User user, Set<Role> roles) {
		User savedUser = this.userRepos.save(user);
		savedUser.setRoles(roles);
		this.userRepos.save(savedUser);
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
		return user;
	}
	
	private Role createRole(String name) {
		Role role = new Role();
		role.setRoleName(name);
		return roleRepos.save(role);
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