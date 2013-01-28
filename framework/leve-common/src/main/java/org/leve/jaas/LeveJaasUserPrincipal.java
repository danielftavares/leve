package org.leve.jaas;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class LeveJaasUserPrincipal implements Principal {

	private Long idUser;

	private String name;

	private String key;

	private List<LeveJaasGroupPrincipal> groups = new ArrayList<LeveJaasGroupPrincipal>();

	public LeveJaasUserPrincipal() {
		super();
	}

	public Long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Long userId) {
		this.idUser = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String userKey) {
		this.key = userKey;
	}

	public List<LeveJaasGroupPrincipal> getGroups() {
		return groups;
	}

	public void setGroups(List<LeveJaasGroupPrincipal> groups) {
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeveJaasUserPrincipal other = (LeveJaasUserPrincipal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
