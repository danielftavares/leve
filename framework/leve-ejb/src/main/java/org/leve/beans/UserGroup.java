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
	@Id
	@SequenceGenerator(name="SYS_USER_GROUP_USERGROUPID_GENERATOR", sequenceName="SEQ_SYS_USER_GROUP", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_USER_GROUP_USERGROUPID_GENERATOR")
	@Column(name="user_group_id")
	private Integer userGroupId;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}