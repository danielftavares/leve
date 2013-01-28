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
import org.leve.annotation.LeveKey;


@Entity
@Table(name="SYS_NUMBERING")
public class Numbering {

	@Id
	@Column(name="ID_NUMBERING")
	@SequenceGenerator(name = "SEQ_SYS_NUMBERING_GENERATOR", sequenceName = "SEQ_SYS_NUMBERING", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SYS_NUMBERING_GENERATOR")
	private Long idNumbering;
	
	@LeveKey
	@LeveDesc
	@Column(name="CLASS")
	@Size(min = 1, max = 40)
	@NotNull
	private String clazz;
	
	@NotNull
	private Integer year;
	
	@NotNull
	private Integer counter;
	
	@NotNull
	@Size(min = 1, max = 20)
	private String format;
	
	
	
	public Long getIdNumbering() {
		return idNumbering;
	}

	public void setIdNumbering(Long idNumbering) {
		this.idNumbering = idNumbering;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
//
//	public void prepareNextValue(Object entity) throws LeveException{
//		Field f = ReflectionUtil.getFieldWithAnnotation(Key.class, entity);
//		ReflectionUtil.setFieldValue(entity, f, getNextValue());
//	}
//	
//	private String getNextValue() {
//		StringBuilder m_sId = new StringBuilder();
//		
//		this.setCounter(this.getCounter()+1);
//		
//		if(this.getFormat().equals("")) {
//			DecimalFormat df = new DecimalFormat("0000");
//			m_sId = m_sId.append(getYear()).append(df.format(getCounter()));
//		} else {
//			StringTokenizer st = new StringTokenizer(this.getFormat(),"+",false);
//		    while(st.hasMoreTokens()) {
//		    	String m_sPart = "";
//		    	m_sPart = st.nextToken();
//		    	
//	    	
//				if(m_sPart.equalsIgnoreCase("yy")) {
//					m_sId.append(Integer.toString(this.getYear()).substring(2));
//				} else if(m_sPart.equalsIgnoreCase("yyyy")) {
//					m_sId.append(this.getYear());
//				} else if(m_sPart.startsWith("0")) {
//					DecimalFormat df = new DecimalFormat(m_sPart);
//					m_sId.append(df.format(this.getCounter()) );
//				} else {
//					m_sId.append(m_sPart);
//				}
//		    }
//		}
//		
//		return m_sId.toString();
//	}
//	
}
