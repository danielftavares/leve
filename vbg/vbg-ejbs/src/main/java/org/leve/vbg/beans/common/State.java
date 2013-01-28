package org.leve.vbg.beans.common;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.leve.annotation.LeveDesc;
import org.leve.annotation.LeveKey;

import java.util.List;

/**
 * The persistent class for the cmo_state database table.
 * 
 */
@Entity
@Table(name = "cmo_state")
public class State {

	@Id
	@SequenceGenerator(name = "CMO_STATE_GENERATOR", sequenceName = "SEQ_CMO_STATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CMO_STATE_GENERATOR")
	private Long stateid;

	@LeveKey
	@NotNull
	@Size(max = 2, min = 2)
	private String abbreviation;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "countryid")
	private Country country;

	@LeveDesc
	@NotNull
	@Size(min = 1, max = 50)
	private String name;

	// bi-directional many-to-one association to City
	@OneToMany(mappedBy = "state")
	@JsonIgnore
	private List<City> cities;

	public State() {
	}

	public Long getStateid() {
		return this.stateid;
	}

	public void setStateid(Long stateid) {
		this.stateid = stateid;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}