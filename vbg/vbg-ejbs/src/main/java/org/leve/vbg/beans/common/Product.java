package org.leve.vbg.beans.common;

import java.math.BigDecimal;

import javax.persistence.Column;
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

import org.leve.annotation.LeveDesc;
import org.leve.annotation.LeveKey;


/**
 * The persistent class for the cmo_city database table.
 * 
 */
@Entity
@Table(name="CMO_PRODUCT")
public class Product {

	@Id
	@Column(name="ID_PRODUCT")
	@SequenceGenerator(name="CMO_PRODUCT_GENERATOR", sequenceName="SEQ_CMO_PRODUCT", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CMO_PRODUCT_GENERATOR")
	private Long idProduct;
	
	@LeveKey
	@LeveDesc
	@NotNull
	@Size(min = 1, max = 10)
	private String key;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_BRAND")
	private Brand brand;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_PRODUCT_TYPE")
	private ProductType productType;
	
	@NotNull
	@Size(min = 1, max = 20)
	private String code;
	
	@Column(name="PRICE_PURCHASE", scale = 2, precision = 10)
	private BigDecimal purchasePrice;
	
	@Column(name="PRICE_SALE", scale = 2, precision = 10)
	private BigDecimal salePrice;

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	
}