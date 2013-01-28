package org.leve.vbg.ejb.common;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.ejb.BaseBusinessBean;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.State;
import org.leve.vbg.beans.common.StateFindDto;

@Stateless
public class StateBean extends BaseBusinessBean<State, StateFindDto> {

	@EJB
	private StateDAO stateDAO;

	@Override
	protected BaseDAO<State, StateFindDto> getDAO() {
		return stateDAO;
	}

}