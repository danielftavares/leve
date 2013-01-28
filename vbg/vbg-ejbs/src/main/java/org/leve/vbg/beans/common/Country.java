package org.leve.vbg.beans.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.leve.annotation.LeveDesc;
import org.leve.annotation.LeveKey;

@Entity
@Table(name = "CMO_COUNTRY")
public class Country {
	@Id
	@SequenceGenerator(name = "SEQ_CMO_COUNTRY", sequenceName = "SEQ_CMO_COUNTRY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CMO_COUNTRY")
	private Long countryid;

	@LeveKey
	@NotNull
	@Size(max=2, min = 2)
	private String abbreviation;

	@LeveDesc
	@NotNull
	@Size(max=50, min = 1)
	private String name;

	public Long getCountryid() {
		return countryid;
	}

	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}

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
