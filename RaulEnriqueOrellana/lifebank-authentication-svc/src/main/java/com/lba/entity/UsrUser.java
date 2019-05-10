package com.lba.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usr_users database table.
 * 
 */
@Entity
@Table(name="usr_users")
@NamedQuery(name="UsrUser.findAll", query="SELECT u FROM UsrUser u")
public class UsrUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usr_user_id")
	private String usrUserId;

	@Column(name="usr_password")
	private String usrPassword;

	@Column(name="usr_second_name")
	private String usrSecondName;

	@Column(name="usr_user_name")
	private String usrUserName;

	//bi-directional many-to-one association to UsrUserSession
	@OneToMany(mappedBy="usrUser")
	private List<UsrUserSession> usrUserSessions;

	public UsrUser() {
	}

	public String getUsrUserId() {
		return this.usrUserId;
	}

	public void setUsrUserId(String usrUserId) {
		this.usrUserId = usrUserId;
	}

	public String getUsrPassword() {
		return this.usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

	public String getUsrSecondName() {
		return this.usrSecondName;
	}

	public void setUsrSecondName(String usrSecondName) {
		this.usrSecondName = usrSecondName;
	}

	public String getUsrUserName() {
		return this.usrUserName;
	}

	public void setUsrUserName(String usrUserName) {
		this.usrUserName = usrUserName;
	}

	public List<UsrUserSession> getUsrUserSessions() {
		return this.usrUserSessions;
	}

	public void setUsrUserSessions(List<UsrUserSession> usrUserSessions) {
		this.usrUserSessions = usrUserSessions;
	}

	public UsrUserSession addUsrUserSession(UsrUserSession usrUserSession) {
		getUsrUserSessions().add(usrUserSession);
		usrUserSession.setUsrUser(this);

		return usrUserSession;
	}

	public UsrUserSession removeUsrUserSession(UsrUserSession usrUserSession) {
		getUsrUserSessions().remove(usrUserSession);
		usrUserSession.setUsrUser(null);

		return usrUserSession;
	}

}