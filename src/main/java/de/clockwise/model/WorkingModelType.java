package de.clockwise.model;

import java.util.Arrays;

public enum WorkingModelType
{
	FREELANCER("F","Freelancer"),
	VOLLZEIT("V","Vollzeit"),
	TEILZEIT("T","Teilzeit");
	
	private String key;
	private String type;

	private WorkingModelType(String key, String type) {
		this.key = key;
		this.type = type;
	}
	
	public static WorkingModelType getByKey(String key){
		return Arrays.stream(values()).filter(P -> P.key.equals(key)).findFirst().orElse(null);
	} 
	
	@Override
	public String toString() {
		return type;
	}
}
