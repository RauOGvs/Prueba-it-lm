package com.lci.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tst_users database table.
 * 
 */
@Entity
@Table(name="tst_users")
@NamedQuery(name="TstUser.findAll", query="SELECT t FROM TstUser t")
public class TstUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TST_USERS_IDUSER_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TST_USERS_IDUSER_GENERATOR")
	@Column(name="id_user")
	private Integer idUser;

	@Temporal(TemporalType.DATE)
	@Column(name="usr_birth_date")
	private Date usrBirthDate;

	@Column(name="usr_name")
	private String usrName;

	//bi-directional many-to-one association to TstUser
	@ManyToOne
	@JoinColumn(name="tst_user_type")
	private TstUser tstUser;

	//bi-directional many-to-one association to TstUser
	@OneToMany(mappedBy="tstUser")
	private List<TstUser> tstUsers;

	public TstUser() {
	}

	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Date getUsrBirthDate() {
		return this.usrBirthDate;
	}

	public void setUsrBirthDate(Date usrBirthDate) {
		this.usrBirthDate = usrBirthDate;
	}

	public String getUsrName() {
		return this.usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public TstUser getTstUser() {
		return this.tstUser;
	}

	public void setTstUser(TstUser tstUser) {
		this.tstUser = tstUser;
	}

	public List<TstUser> getTstUsers() {
		return this.tstUsers;
	}

	public void setTstUsers(List<TstUser> tstUsers) {
		this.tstUsers = tstUsers;
	}

	public TstUser addTstUser(TstUser tstUser) {
		getTstUsers().add(tstUser);
		tstUser.setTstUser(this);

		return tstUser;
	}

	public TstUser removeTstUser(TstUser tstUser) {
		getTstUsers().remove(tstUser);
		tstUser.setTstUser(null);

		return tstUser;
	}

}