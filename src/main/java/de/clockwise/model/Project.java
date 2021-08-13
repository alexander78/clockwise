package de.clockwise.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project implements Comparable<Project> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String bezeichnung;

	private String fachId;

	private String beschreibung;

	private Boolean standard;

	@OneToMany (cascade=CascadeType.ALL, mappedBy = "project")
	private Set<Abruf> projectAbrufe;

	private String ansprechpartnerKunde;

	private String koordinator;

	private Boolean active;

	private Boolean rufbereitschaft;

	private String customer;
	
	public Project() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(final String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getFachId() {
		return fachId;
	}

	public void setFachId(final String fachId) {
		this.fachId = fachId;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(final String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Boolean isStandard() {
		return standard;
	}

	public void setStandard(final Boolean standard) {
		this.standard = standard;
	}

	public Boolean getStandard() {
		return standard;
	}

	public String getAnsprechpartnerKunde() {
		return ansprechpartnerKunde;
	}

	public void setAnsprechpartnerKunde(final String ansprechpartnerKunde) {
		this.ansprechpartnerKunde = ansprechpartnerKunde;
	}

	public String getKoordinator() {
		return koordinator;
	}

	public void setKoordinator(final String koordinator) {
		this.koordinator = koordinator;
	}

	public Set<Abruf> getProjectAbrufe() {
		return projectAbrufe;
	}

	public void setProjectAbrufe(final Set<Abruf> projectAbrufe) {
		this.projectAbrufe = projectAbrufe;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getRufbereitschaft() {
		return rufbereitschaft;
	}

	public void setRufbereitschaft(final Boolean rufbereitschaft) {
		this.rufbereitschaft = rufbereitschaft;
	}

	@Override
	public int compareTo(final Project project) {
		return bezeichnung.toLowerCase().compareTo(project.getBezeichnung().toLowerCase());
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}
