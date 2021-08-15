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
@Table(name = "workingtimemodel")
public class WorkingtimeModel implements Comparable<WorkingtimeModel> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Date validFrom;

	private Date validTo;

	private Integer vacationDays;

	private boolean monday;

	private boolean tuesday;

	private boolean wednesday;

	private boolean thursday;

	private boolean friday;

	private Double hoursOnMonday;

	private Double hoursOnTuesday;

	private Double hoursOnWednesday;

	private Double hoursOnThursday;

	private Double hoursOnFriday;

	private Double hoursPerWeek;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private WorkingModelType workingModelTyp;

	public WorkingtimeModel() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Integer getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(Integer vacationDays) {
		this.vacationDays = vacationDays;
	}

	public boolean isMonday() {
		return monday;
	}

	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	public boolean isTuesday() {
		return tuesday;
	}

	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}

	public boolean isWednesday() {
		return wednesday;
	}

	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	public boolean isThursday() {
		return thursday;
	}

	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	public boolean isFriday() {
		return friday;
	}

	public void setFriday(boolean friday) {
		this.friday = friday;
	}

	public double getHoursPerWeek() {
		Double retVal = sumValuesNullSafe(getHoursOnMonday(), getHoursOnTuesday());
		retVal = sumValuesNullSafe(retVal, getHoursOnWednesday());
		retVal = sumValuesNullSafe(retVal, getHoursOnThursday());
		retVal = sumValuesNullSafe(retVal, getHoursOnFriday());
		return retVal.doubleValue();
	}

	private Double sumValuesNullSafe(Double val1, Double val2) {
		if (val1 == null) {
			val1 = new Double(0);
		}
		if (val2 == null) {
			val2 = new Double(0);
		}
		return val1 + val2;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getHoursOnMonday() {
		return hoursOnMonday;
	}

	public void setHoursOnMonday(Double hoursOnMonday) {
		this.hoursOnMonday = hoursOnMonday;
	}

	public Double getHoursOnTuesday() {
		return hoursOnTuesday;
	}

	public void setHoursOnTuesday(Double hoursOnTuesday) {
		this.hoursOnTuesday = hoursOnTuesday;
	}

	public Double getHoursOnWednesday() {
		return hoursOnWednesday;
	}

	public void setHoursOnWednesday(Double hoursOnWednesday) {
		this.hoursOnWednesday = hoursOnWednesday;
	}

	public Double getHoursOnThursday() {
		return hoursOnThursday;
	}

	public void setHoursOnThursday(Double hoursOnThursday) {
		this.hoursOnThursday = hoursOnThursday;
	}

	public Double getHoursOnFriday() {
		return hoursOnFriday;
	}

	public void setHoursOnFriday(Double hoursOnFriday) {
		this.hoursOnFriday = hoursOnFriday;
	}

	public WorkingModelType getWorkingModelTyp() {
		return workingModelTyp;
	}

	public void setWorkingModelTyp(WorkingModelType workingModelTyp) {
		this.workingModelTyp = workingModelTyp;
	}

	public int getDaysPerWeek() {
		int count = 0;
		if (monday) {
			count++;
		}
		if (tuesday) {
			count++;
		}
		if (wednesday) {
			count++;
		}
		if (thursday) {
			count++;
		}
		if (friday) {
			count++;
		}
		return count;
	}

	public void overrideAllPropertiesExceptId(final WorkingtimeModel model) {
		setId(model.getId());
		setMonday(model.isMonday());
		setHoursOnMonday(model.getHoursOnMonday());
		setTuesday(model.isTuesday());
		setHoursOnTuesday(model.getHoursOnTuesday());
		setWednesday(model.isWednesday());
		setHoursOnWednesday(model.getHoursOnWednesday());
		setThursday(model.isThursday());
		setHoursOnThursday(model.getHoursOnThursday());
		setFriday(model.isFriday());
		setHoursOnFriday(model.getHoursOnFriday());

		setVacationDays(model.getVacationDays());

		setValidFrom(model.getValidFrom());
		setValidTo(model.getValidTo());
		setWorkingModelTyp(model.getWorkingModelTyp());
	}

	@Override
	public int compareTo(WorkingtimeModel o) {
		if (this.getValidFrom().equals(o.getValidFrom()) && o.getValidTo().equals(this.getValidTo())) {
			return 0;
		}
		if (this.getValidFrom().before(o.getValidTo()) && this.getValidTo().before(o.getValidFrom())) {
			return -1;
		} else {
			return 1;
		}
	}
}
