package org.leve.vbg.beans.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.leve.vbg.beans.common.Product;


/**
 * The persistent class for the stk_invoice_product database table.
 * 
 */
@Entity
@Table(name="STK_INVOICE_PRODUCT")
public class InvoiceProduct {

	@Id
	@SequenceGenerator(name="STK_INVOICE_PRODUCT_GENERATOR", sequenceName="SEQ_STK_INVOICE_PRODUCT", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STK_INVOICE_PRODUCT_GENERATOR")
	@Column(name="id_invoice_product")
	private Integer idInvoiceProduct;

	private Integer quantity;
	
	private String size;

	@ManyToOne
	@JoinColumn(name="id_invoice")
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;

	public Integer getIdInvoiceProduct() {
		return idInvoiceProduct;
	}

	public void setIdInvoiceProduct(Integer idInvoiceProduct) {
		this.idInvoiceProduct = idInvoiceProduct;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}



}