package de.clockwise.model;


public enum Title
{

	HERR("Herr"),
	FRAU("Frau");

	private final String titleText;

	private Title(final String titleText) {
		this.titleText = titleText;

	}

	public String getTitleText() {
		return titleText;
	}

	public static Title getForAnredeText(final String anredeText) {
		Title[] values = values();
		for (Title title : values) {
			if (title.getTitleText().equals(anredeText)) {
				return title;
			}
		}
		return null;
	}
}
