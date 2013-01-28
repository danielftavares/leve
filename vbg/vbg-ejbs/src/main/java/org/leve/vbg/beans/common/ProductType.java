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
@Table(name="CMO_PRODUCT_TYPE")
public class ProductType {

	@Id
	@Column(name="ID_PRODUCT_TYPE")
	@SequenceGenerator(name="CMO_PRODUCT_TYPE_GENERATOR", sequenceName="SEQ_CMO_PRODUCT_TYPE", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CMO_PRODUCT_TYPE_GENERATOR")
	private Long idProductType;
	
	@LeveDesc
	@NotNull
	@Size(min = 1, max = 40)
	private String name;
	
	public Long getIdProductType() {
		return idProductType;
	}

	public void setIdProductType(Long idProductType) {
		this.idProductType = idProductType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}