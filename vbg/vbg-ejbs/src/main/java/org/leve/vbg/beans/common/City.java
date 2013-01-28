package org.leve.vbg.beans.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * The persistent class for the cmo_city database table.
 * 
 */
@Entity
@Table(name="cmo_city")
public class City {

	@Id
	@SequenceGenerator(name="CMO_CITY_CITYID_GENERATOR", sequenceName="SEQ_CMO_CITY", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CMO_CITY_CITYID_GENERATOR")
	private Long cityid;

	@NotNull
	@Size(min = 1, max = 50)
	private String name;

	//bi-directional many-to-one association to State
	@NotNull
	@ManyToOne
	@JoinColumn(name="stateid")
	private State state;

	public City() {
	}

	public Long getCityid() {
		return this.cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

}