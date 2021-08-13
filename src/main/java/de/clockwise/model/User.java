package de.clockwise.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String userNumber;

	private Title title;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private byte[] salt;

	private String personalNr;

	@OneToMany (cascade=CascadeType.ALL)
	private Set<Role> roles;

	@OneToMany (cascade=CascadeType.ALL)
	private Set<Project> projects;

	private String externalUserID;

	private double flexTime;

	private String firmenName;

	private String firmenStrasse;

	private String firmenOrt;

	private String firmenPlz;

	private String firmenLieferantenNr;

	private boolean active;

	private String costCentre;

	@OneToMany (cascade=CascadeType.ALL, mappedBy = "user")
	private List<WorkingtimeModel> userWorkingTimeModels;

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(final String accountnumber) {
		this.userNumber = accountnumber;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(final Title title) {
		this.title = title;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(final byte[] salt) {
		this.salt = salt;
	}

	public double getHoursPerWeek() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return 0;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1).getHoursPerWeek();

	}

	public int getDaysPerWeek() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return 5;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1).getDaysPerWeek();

	}

	public String getPersonalNr() {
		return personalNr;
	}

	public void setPersonalNr(final String personalNr) {
		this.personalNr = personalNr;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(final Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(final Set<Project> userProjects) {
		this.projects = userProjects;
	}

	public void setExternalUserID(final String externalUserID) {
		this.externalUserID = externalUserID;
	}

	public String getExternalUserID() {
		return externalUserID;
	}

	public double getFlexTime() {
		return flexTime;
	}

	public void setFlexTime(final double flexTime) {
		this.flexTime = flexTime;
	}

	public String getFirmenName() {
		return firmenName;
	}

	public void setFirmenName(final String firmenName) {
		this.firmenName = firmenName;
	}

	public String getFirmenStrasse() {
		return firmenStrasse;
	}

	public void setFirmenStrasse(final String firmenStrasse) {
		this.firmenStrasse = firmenStrasse;
	}

	public String getFirmenOrt() {
		return firmenOrt;
	}

	public void setFirmenOrt(final String firmenOrt) {
		this.firmenOrt = firmenOrt;
	}

	public String getFirmenPlz() {
		return firmenPlz;
	}

	public void setFirmenPlz(final String firmenPlz) {
		this.firmenPlz = firmenPlz;
	}

	public String getFirmenLieferantenNr() {
		return firmenLieferantenNr;
	}

	public void setFirmenLieferantenNr(final String lieferantenNr) {
		this.firmenLieferantenNr = lieferantenNr;
	}

	public int getVacationDays() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return 0;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1).getVacationDays();
	}

	public String getCostCentre() {
		return costCentre;
	}

	public void setCostCentre(final String costcentre) {
		this.costCentre = costcentre;
	}

	public boolean isVollzeit() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return true;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1)
				.getWorkingModelTyp() == WorkingModelType.VOLLZEIT;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean isMonday() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return true;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1).isMonday();
	}

	public boolean isTuesday() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return true;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1).isMonday();
	}

	public boolean isWednesday() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return true;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1).isWednesday();
	}

	public boolean isThursday() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return true;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1).isThursday();
	}

	public boolean isFriday() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return true;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1).isFriday();
	}

	public boolean isFreelancer() {
		if (getUserWorkingTimeModels().isEmpty()) {
			return false;
		}
		return getUserWorkingTimeModels().get(getUserWorkingTimeModels().size() - 1)
				.getWorkingModelTyp() == WorkingModelType.FREELANCER;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<WorkingtimeModel> getUserWorkingTimeModels() {
		return userWorkingTimeModels;
	}

	public void setUserWorkingTimeModels(List<WorkingtimeModel> userWorkingTimeModels) {
		this.userWorkingTimeModels = userWorkingTimeModels;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return hashCode() == obj.hashCode();
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.valueOf(id));
		builder.append("|");
		builder.append(String.valueOf(email));
		builder.append("|");
		builder.append(String.valueOf(personalNr));
		return builder.toString();
	}

//	public double getVacationDaysForYear(int year) {
//		VacationPerYearCalculator vacationPerYearCalculator = new VacationPerYearCalculator(this);
//		return vacationPerYearCalculator.getVacationDaysForYear(year);
//	}

}
