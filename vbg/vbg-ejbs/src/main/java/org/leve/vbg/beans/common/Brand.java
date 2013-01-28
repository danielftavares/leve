package org.leve.vbg.beans.common;

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
 * The persistent class for the cmo_city database table.
 * 
 */
@Entity
@Table(name="CMO_BRAND")
public class Brand {

	@Id
	@Column(name="id_brand")
	@SequenceGenerator(name="CMO_BRAND_BRANDID_GENERATOR", sequenceName="SEQ_CMO_BRAND", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CMO_BRAND_BRANDID_GENERATOR")
	private Long idBrand;
	
	@LeveDesc
	@NotNull
	@Size(min = 1, max = 40)
	private String name;
	
	public Long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}