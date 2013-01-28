package org.leve.jaas;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LeveJaasGroupPrincipal implements Group {

	private Long idGroup;

	private String name;
	
	private Set<Principal> members;

	public LeveJaasGroupPrincipal() {
		super();
		members = new HashSet<Principal>();
	}
	
	public Long getIdGroup() {
		return this.idGroup;
	}

	public void setIdGroup(Long groupId) {
		this.idGroup = groupId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeveJaasGroupPrincipal other = (LeveJaasGroupPrincipal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	


	@Override
	public boolean addMember(Principal user) {
		boolean isMember = members.contains(user);
		if (isMember == false)
			members.add(user);
		return isMember == false;
	}

	@Override
	public boolean removeMember(Principal user) {
		return members.remove(user);
	}

	@Override
	public boolean isMember(Principal member) {
		boolean isMember = members.contains(member);
		if (isMember == false) { // Check any Groups for membership
			Iterator<Principal> iter = members.iterator();
			while (isMember == false && iter.hasNext()) {
				Object next = iter.next();
				if (next instanceof Group) {
					Group group = (Group) next;
					isMember = group.isMember(member);
				}
			}
		}

		return isMember;
	}

	@Override
	public Enumeration<? extends Principal> members() {
		return Collections.enumeration(members);
	}

}
