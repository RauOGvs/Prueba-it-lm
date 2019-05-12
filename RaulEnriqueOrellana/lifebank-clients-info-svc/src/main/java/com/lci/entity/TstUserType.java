package com.lci.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tst_user_types database table.
 * 
 */
@Entity
@Table(name="tst_user_types")
@NamedQuery(name="TstUserType.findAll", query="SELECT t FROM TstUserType t")
public class TstUserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TST_USER_TYPES_IDUSRTYPES_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TST_USER_TYPES_IDUSRTYPES_GENERATOR")
	@Column(name="id_usr_types")
	private Integer idUsrTypes;

	@Column(name="type_description")
	private String typeDescription;

	@Column(name="type_name")
	private String typeName;

	public TstUserType() {
	}

	public Integer getIdUsrTypes() {
		return this.idUsrTypes;
	}

	public void setIdUsrTypes(Integer idUsrTypes) {
		this.idUsrTypes = idUsrTypes;
	}

	public String getTypeDescription() {
		return this.typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}