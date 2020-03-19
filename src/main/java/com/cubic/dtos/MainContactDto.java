package com.cubic.dtos;

import java.util.List;

public class MainContactDto {

	private NameDto name;
	private AddressDto address;
	private List<PhoneDto> phone;
	private String email;

	public NameDto getName() {
		return name;
	}

	public void setName(NameDto name) {
		this.name = name;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public List<PhoneDto> getPhone() {
		return phone;
	}

	public void setPhone(List<PhoneDto> phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
