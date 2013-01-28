package org.leve.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * The persistent class for the sys_domain_value database table.
 * 
 */
@Entity
@Table(name="SYS_DOMAIN_VALUE")
public class DomainValue {

	@Id
	@SequenceGenerator(name="SYS_DOMAIN_VALUE_GENERATOR", sequenceName="SEQ_SYS_DOMAIN_VALUE", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_DOMAIN_VALUE_GENERATOR")
	@Column(name="id_domain_value")
	private Long idDomainValue;

	@NotNull
	@Size(min = 1, max = 40)
	private String description;

	@NotNull
	@Size(min = 1, max = 5)
	private String value;
	
	@NotNull
	private Integer index;

	//bi-directional one-to-one association to Domain
	@OneToOne
	@JoinColumn(name="id_domain")
	private Domain domain;

	public Long getIdDomainValue() {
		return idDomainValue;
	}

	public void setIdDomainValue(Long idDomainValue) {
		this.idDomainValue = idDomainValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}


	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}