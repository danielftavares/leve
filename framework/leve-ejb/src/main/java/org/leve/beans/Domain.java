package org.leve.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.leve.annotation.LeveDesc;


/**
 * The persistent class for the sys_domain database table.
 * 
 */
@Entity
@Table(name="SYS_DOMAIN")
public class Domain {

	@Id
	@SequenceGenerator(name="SYS_DOMAIN_GENERATOR", sequenceName="SEQ_SYS_DOMAIN", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_DOMAIN_GENERATOR")
	@Column(name="id_domain")
	private Long idDomain;

	@LeveDesc
	@Size(min = 1, max = 40)
	@NotNull
	private String name;
	
	public Long getIdDomain() {
		return idDomain;
	}

	public void setIdDomain(Long idDomain) {
		this.idDomain = idDomain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	

}