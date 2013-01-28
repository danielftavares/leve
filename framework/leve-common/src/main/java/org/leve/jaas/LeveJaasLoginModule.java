package org.leve.jaas;

import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.sql.DataSource;

/**
 * CONF: <security-domains> ... <security-domain name="LeveAuthDomain"
 * cache-type="default"> <authentication> <login-module
 * code="org.leve.jaas.LeveJaasLoginModule" flag="required"> <module-option
 * name="dbJNDIName" value="java:/leveDS"/> </login-module> </authentication>
 * </security-domain>
 * 
 * 
 * @author daniel
 * 
 */
public class LeveJaasLoginModule implements LoginModule {

	private static final String SELECT_USER = "SELECT ID_USER, "
			+ "	KEY, NAME FROM SYS_USER  WHERE "
			+ "	(KEY = ? OR NAME = ?) AND  PASSWORD = MD5(?)";

	private static final String SELECT_GROUP = "SELECT ID_GROUP, "
			+ "	NAME FROM SYS_GROUP G WHERE  EXISTS "
			+ "		(SELECT 1 FROM SYS_USER_GROUP UG "
			+ "		WHERE UG.GROUP_ID = G.ID_GROUP AND UG.USER_ID= ?)";

	private static Logger LOGGER = Logger.getLogger(LeveJaasLoginModule.class
			.getName());

	private boolean authState = false;
	private boolean commitState = false;

	private Subject subject;
	private CallbackHandler callbackHandler;
	private Map<String, ?> sharedState;
	private Map<String, ?> options;

	private String dbJNDIName;

	private LeveJaasUserPrincipal currentUser = null;

	private DataSource dataSource;
	private LeveJaasGroupPrincipal mainGroup = new LeveJaasGroupPrincipal();

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;

		dbJNDIName = (String) options.get("dbJNDIName");

		try {
			InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup(dbJNDIName);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "ERRO - leve Login module", e);
		}
		mainGroup.setName("Roles");
	}

	@Override
	public boolean login() throws LoginException {
		try {
			if (callbackHandler == null)
				throw new LoginException("No callback handler");

			Callback[] callbacks = configureCallbacks();
			callbackHandler.handle(callbacks);

			String webUserName = ((NameCallback) callbacks[0]).getName();
			char[] webCredential = ((PasswordCallback) callbacks[1])
					.getPassword();

			if ((webUserName == null) || (webCredential == null)) {
				setAuthenticated(false);
				return isAuthenticated();
			}

			LeveJaasUserPrincipal userInfo = getUserInfo(webUserName,
					webCredential);

			if (userInfo == null) {
				setAuthenticated(false);
				return isAuthenticated();
			}

			currentUser = userInfo;
			setAuthenticated(true);
			return isAuthenticated();
		} catch (IOException e) {
			throw new LoginException(e.toString());
		} catch (UnsupportedCallbackException e) {
			throw new LoginException(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoginException(e.toString());
		}
	}

	public Callback[] configureCallbacks() {

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Enter user name");
		callbacks[1] = new PasswordCallback("password: ", false);
		return callbacks;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#commit()
	 * @return
	 * @throws LoginException
	 */
	public boolean commit() throws LoginException {

		if (!isAuthenticated()) {
			currentUser = null;
			setCommitted(false);
			return false;
		}

		setCommitted(true);
		setJAASInfo(subject);
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#abort()
	 * @throws LoginException
	 */
	public boolean abort() throws LoginException {
		this.currentUser = null;
		return (isAuthenticated() && isCommitted());
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#logout()
	 * @return
	 * @throws LoginException
	 */
	public boolean logout() throws LoginException {
		unsetJAASInfo(this.subject);
		return true;
	}

	public void setJAASInfo(Subject subject) {
		subject.getPrincipals().add(currentUser);
		subject.getPrincipals().add(mainGroup);
	}

	public void unsetJAASInfo(Subject subject) {
		subject.getPrincipals().remove(currentUser);
		subject.getPrincipals().remove(mainGroup);
	}

	/**
	 * Load info from database
	 * 
	 * @param userName
	 *            user info to load
	 * @exception SQLException
	 */
	public LeveJaasUserPrincipal getUserInfo(String userName,
			char[] webCredential) throws Exception {
		Connection connection = null;

		try {
			connection = getConnection();
			LeveJaasUserPrincipal user = null;

			// query for credential
			PreparedStatement statement = connection
					.prepareStatement(SELECT_USER);
			statement.setString(1, userName);
			statement.setString(2, userName);
			statement.setString(3, new String(webCredential));
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				user = new LeveJaasUserPrincipal();
				user.setIdUser(results.getLong("ID_USER"));
				user.setKey(results.getString("KEY"));
				user.setName(results.getString("NAME"));
			}
			results.close();
			statement.close();

			if (user != null) {
				// query for role names
				statement = connection.prepareStatement(SELECT_GROUP);
				statement.setLong(1, user.getIdUser());
				results = statement.executeQuery();
				List<LeveJaasGroupPrincipal> groups = new ArrayList<LeveJaasGroupPrincipal>();

				while (results.next()) {
					LeveJaasGroupPrincipal group = new LeveJaasGroupPrincipal();
					group.setIdGroup(results.getLong("ID_GROUP"));
					group.setName(results.getString("NAME"));
					groups.add(group);
					mainGroup.addMember(group);
				}
				user.setGroups(groups);
				results.close();
				statement.close();
			}

			return user;
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/**
	 * Get a connection from the DataSource
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}

	public boolean isAuthState() {
		return authState;
	}

	public void setAuthState(boolean authState) {
		this.authState = authState;
	}

	public boolean isCommitState() {
		return commitState;
	}

	public void setCommitState(boolean commitState) {
		this.commitState = commitState;
	}

	public boolean isAuthenticated() {
		return this.authState;
	}

	public void setAuthenticated(boolean authState) {
		this.authState = authState;
	}

	public boolean isCommitted() {
		return this.commitState;
	}

	public void setCommitted(boolean commitState) {
		this.commitState = commitState;
	}

	/**
	 * Find or create a Group with the given name. Subclasses should use this
	 * method to locate the 'Roles' group or create additional types of groups.
	 * 
	 * @return A named Group from the principals set.
	 */
	protected Group createGroup(String name, Set<Principal> principals) {
		Group roles = null;
		Iterator<Principal> iter = principals.iterator();
		while (iter.hasNext()) {
			Object next = iter.next();
			if ((next instanceof Group) == false)
				continue;
			Group grp = (Group) next;
			if (grp.getName().equals(name)) {
				roles = grp;
				break;
			}
		}
		return roles;
	}

}
