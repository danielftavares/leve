package org.leve.vbg.beans.stock;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the stk_invoice database table.
 * 
 */
@Entity
@Table(name="STK_INVOICE")
public class Invoice {

	@Id
	@SequenceGenerator(name="STK_INVOICE_GENERATOR", sequenceName="SEQ_STK_INVOICE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STK_INVOICE_GENERATOR")
	@Column(name="ID_INVOICE")
	private Integer idInvoice;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_ARRIVE")
	private Calendar dtArrive;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_DEPARTURE")
	private Calendar dtDeparture;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_ISSUANCE")
	private Calendar dtIssuance;

	private String number;

	private String serial;

	private String type;

	@OneToMany(mappedBy="invoice")
	private List<InvoiceProduct> stkInvoiceProducts;

	public Integer getIdInvoice() {
		return this.idInvoice;
	}

	public void setIdInvoice(Integer idInvoice) {
		this.idInvoice = idInvoice;
	}

	public Calendar getDtArrive() {
		return this.dtArrive;
	}

	public void setDtArrive(Calendar dtArrive) {
		this.dtArrive = dtArrive;
	}

	public Calendar getDtDeparture() {
		return this.dtDeparture;
	}

	public void setDtDeparture(Calendar dtDeparture) {
		this.dtDeparture = dtDeparture;
	}

	public Calendar getDtIssuance() {
		return this.dtIssuance;
	}

	public void setDtIssuance(Calendar dtIssuance) {
		this.dtIssuance = dtIssuance;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<InvoiceProduct> getStkInvoiceProducts() {
		return this.stkInvoiceProducts;
	}

	public void setStkInvoiceProducts(List<InvoiceProduct> stkInvoiceProducts) {
		this.stkInvoiceProducts = stkInvoiceProducts;
	}

}