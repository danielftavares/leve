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
 * The persistent class for the sys_group database table.
 * 
 */
@Entity
@Table(name="sys_group")
public class Group  {

	@Id
	@SequenceGenerator(name="SYS_GROUP_GROUPID_GENERATOR", sequenceName="SEQ_SYS_GROUP", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_GROUP_GROUPID_GENERATOR")
	@Column(name="id_group")
	private Long idGroup;

	@LeveDesc
	@NotNull
	@Size(min = 1, max = 40)
	private String name;


	public Long getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}