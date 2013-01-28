package org.leve.beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_user_group database table.
 * 
 */
@Entity
@Table(name="sys_user_group")
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_USER_GROUP_USERGROUPID_GENERATOR", sequenceName="SEQ_SYS_USER_GROUP")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_USER_GROUP_USERGROUPID_GENERATOR")
	@Column(name="user_group_id")
	private Integer userGroupId;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group sysGroup;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User sysUser;

	public UserGroup() {
	}

	public Integer getUserGroupId() {
		return this.userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public Group getSysGroup() {
		return this.sysGroup;
	}

	public void setSysGroup(Group sysGroup) {
		this.sysGroup = sysGroup;
	}

	public User getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(User sysUser) {
		this.sysUser = sysUser;
	}

}