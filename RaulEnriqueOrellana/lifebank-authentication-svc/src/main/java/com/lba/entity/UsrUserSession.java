package com.lba.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uus_session_id")
	private Integer uusSessionId;

	@Column(name="uus_session_date")
	private Timestamp uusSessionDate;

	@Column(name="uus_session_status")
	private Integer uusSessionStatus;

	@Column(name="uus_token")
	private String uusToken;

	//bi-directional many-to-one association to UsrUser
	@ManyToOne
	@JoinColumn(name="usr_user_name_id")
	private UsrUser usrUser;

	public UsrUserSession() {
	}

	public Integer getUusSessionId() {
		return this.uusSessionId;
	}

	public void setUusSessionId(Integer uusSessionId) {
		this.uusSessionId = uusSessionId;
	}

	public Timestamp getUusSessionDate() {
		return this.uusSessionDate;
	}

	public void setUusSessionDate(Timestamp uusSessionDate) {
		this.uusSessionDate = uusSessionDate;
	}

	public Integer getUusSessionStatus() {
		return this.uusSessionStatus;
	}

	public void setUusSessionStatus(Integer uusSessionStatus) {
		this.uusSessionStatus = uusSessionStatus;
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