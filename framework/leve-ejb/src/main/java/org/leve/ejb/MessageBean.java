package org.leve.ejb;

import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.beans.Message;
import org.leve.beans.MessageFindDto;
import org.leve.ejb.dao.BaseDAO;

@Stateless
public class MessageBean extends BaseBusinessBean<Message, MessageFindDto> {

	@EJB
	private MessageDAO messageDAO;
	
	public List<Message> list(Locale locale){
		return messageDAO.list(locale);
	}

	@Override
	protected BaseDAO<Message, MessageFindDto> getDAO() {
		// TODO Auto-generated method stub
		return messageDAO;
	}

}