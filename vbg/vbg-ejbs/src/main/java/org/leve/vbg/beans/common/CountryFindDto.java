package org.leve.vbg.beans.common;

import javax.validation.constraints.Size;


public class CountryFindDto {

	@Size(max=2)
	private String abbreviation;

	@Size(max=50)
	private String name;


	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
