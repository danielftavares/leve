package org.leve.ejb;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.beans.User;
import org.leve.beans.UserFindDto;
import org.leve.exception.LeveException;

@Stateless
public class UserBean extends BaseBusinessBean<User, UserFindDto> {

	@EJB
	private UserDAO userDAO;

	@Override
	protected UserDAO getDAO() {
		return userDAO;
	}

	@Override
	public User save(User bean) {
		try {
			byte[] bytesOfMessage = bean.getPassword().getBytes("UTF-8");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] mdbytes = md.digest(bytesOfMessage);

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100,
						16).substring(1));
			}

			bean.setPassword(sb.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new LeveException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new LeveException(e.getMessage());
		}

		return super.save(bean);
	}

}