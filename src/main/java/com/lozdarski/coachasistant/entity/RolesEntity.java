package com.lozdarski.coachasistant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="roles", schema="myschema")
public class RolesEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="roles_id_seq")
	@SequenceGenerator(name="roles_id_seq", sequenceName="roles_id_seq", schema="myschema")
	private int id;
	private String roleName;
	
	@OneToOne(targetEntity = UserEntity.class)
	private UserEntity userIdFk;
	
	private String userNameFk;
	
	public RolesEntity() {}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserEntity getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(UserEntity userIdFk) {
		this.userIdFk = userIdFk;
	}

	public String getUserNameFk() {
		return userNameFk;
	}

	public void setUserNameFk(String userNameFk) {
		this.userNameFk = userNameFk;
	}

	public int getId() {
		return id;
	}

}
