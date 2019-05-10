package com.lba.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usr_user_session database table.
 * 
 */
@Entity
@Table(name="usr_user_session")
@NamedQuery(name="UsrUserSession.findAll", query="SELECT u FROM UsrUserSession u")
public class UsrUserSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="uus_session_id")
	private String uusSessionId;

	@Column(name="usr_uss_status")
	private String usrUssStatus;

	@Column(name="uus_token")
	private String uusToken;

	//bi-directional many-to-one association to UsrUser
	@ManyToOne
	@JoinColumn(name="usr_user_id")
	private UsrUser usrUser;

	public UsrUserSession() {
	}

	public String getUusSessionId() {
		return this.uusSessionId;
	}

	public void setUusSessionId(String uusSessionId) {
		this.uusSessionId = uusSessionId;
	}

	public String getUsrUssStatus() {
		return this.usrUssStatus;
	}

	public void setUsrUssStatus(String usrUssStatus) {
		this.usrUssStatus = usrUssStatus;
	}

	public String getUusToken() {
		return this.uusToken;
	}

	public void setUusToken(String uusToken) {
		this.uusToken = uusToken;
	}

	public UsrUser getUsrUser() {
		return this.usrUser;
	}

	public void setUsrUser(UsrUser usrUser) {
		this.usrUser = usrUser;
	}

}