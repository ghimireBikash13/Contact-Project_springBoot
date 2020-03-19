package com.cubic.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact_main")
public class MainContactEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "email")
	private String email;

	@OneToOne(mappedBy = "mainContactEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NameEntity name;

	@OneToOne(mappedBy = "mainContactEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private AddressEntity address;

	@OneToMany(mappedBy = "mainContactEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PhoneEntity> phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public NameEntity getName() {
		return name;
	}

	public void setName(NameEntity name) {
		this.name = name;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public List<PhoneEntity> getPhone() {
		return phone;
	}

	public void setPhone(List<PhoneEntity> phone) {
		this.phone = phone;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
