package org.leve.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.leve.annotation.LeveDesc;
import org.leve.annotation.LeveKey;


/**
 * The persistent class for the sys_user database table.
 * 
 */
@Entity
@Table(name="SYS_USER")
public class User  {

	@Id
	@SequenceGenerator(name="SYS_USER_USERID_GENERATOR", sequenceName="SEQ_SYS_USER",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_USER_USERID_GENERATOR")
	@Column(name="id_user")
	private Long idUser;


	@LeveKey
	@Column(name="key")
	private String key;
	
	@LeveDesc
	@NotNull
	@Size(min = 1, max = 40)
	private String name;

	@NotNull
	@Size(min = 1, max = 40)
	private String password;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<UserGroup> groups;


	public List<UserGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<UserGroup> groups) {
		this.groups = groups;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	

}