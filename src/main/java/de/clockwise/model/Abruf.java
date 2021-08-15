package de.clockwise.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "abruf")
public class Abruf {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String abrufNummer;

	private Date validFrom;

	private Date validTo;

	private double abrufSumme;

	private boolean active;

	private String azNummer;

	private String rahmenBmNummer;

	private String einzelBmNummer;

	private String waNummer;

	@ManyToOne
    @JoinColumn(name="project_id")
	private Project project;

	public Abruf() {
	}

	public String getAbrufNummer() {
		return abrufNummer;
	}

	public void setAbrufNummer(final String abrufNummer) {
		this.abrufNummer = abrufNummer;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(final Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(final Date validTo) {
		this.validTo = validTo;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public double getAbrufSumme() {
		return abrufSumme;
	}

	public void setAbrufSumme(final double abrufSumme) {
		this.abrufSumme = abrufSumme;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setAzNummer(final String azNummer) {
		this.azNummer = azNummer;
	}

	public String getAzNummer() {
		return azNummer;
	}

	public void setRahmenBmNummer(final String rahmenBmNummer) {
		this.rahmenBmNummer = rahmenBmNummer;
	}

	public String getRahmenBmNummer() {
		return rahmenBmNummer;
	}

	public String getEinzelBmNummer() {
		return einzelBmNummer;
	}

	public void setEinzelBmNummer(final String einzelBmNummer) {
		this.einzelBmNummer = einzelBmNummer;
	}

	public String getWaNummer() {
		return waNummer;
	}

	public void setWaNummer(final String waNummer) {
		this.waNummer = waNummer;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
