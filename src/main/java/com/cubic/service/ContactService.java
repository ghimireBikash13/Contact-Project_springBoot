package com.cubic.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubic.dtos.AddressDto;
import com.cubic.dtos.MainContactDto;
import com.cubic.dtos.NameDto;
import com.cubic.dtos.PhoneDto;
import com.cubic.entities.AddressEntity;
import com.cubic.entities.MainContactEntity;
import com.cubic.entities.NameEntity;
import com.cubic.entities.PhoneEntity;
import com.cubic.repositories.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public void createContactInfo(MainContactDto mainContactDto) {

		MainContactEntity mainContactEntity = new MainContactEntity();
		mainContactEntity.setEmail(mainContactDto.getEmail());
		mainContactEntity.setCreatedAt(new Date());
		mainContactEntity.setUpdatedAt(new Date());
		mainContactEntity.setUuid(UUID.randomUUID().toString());

		NameDto nameDto = mainContactDto.getName();
		NameEntity nameEntity = new NameEntity();
		nameEntity.setFirstName(nameDto.getFirstName());
		nameEntity.setMiddleName(nameDto.getMiddleName());
		nameEntity.setLastName(nameDto.getLastName());
		nameEntity.setMainContactEntity(mainContactEntity);
		mainContactEntity.setName(nameEntity);

		AddressDto addressDto = mainContactDto.getAddress();
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setStreet(addressDto.getStreet());
		addressEntity.setCity(addressDto.getCity());
		addressEntity.setState(addressDto.getState());
		addressEntity.setZip(addressDto.getZip());
		addressEntity.setMainContactEntity(mainContactEntity);
		mainContactEntity.setAddress(addressEntity);

		List<PhoneDto> phoneDto = mainContactDto.getPhone();
		// List<PhoneDto> phoneDto1 = new ArrayList<PhoneDto>();

		List<PhoneEntity> phoneEntities = new ArrayList<PhoneEntity>();
		for (PhoneDto p : phoneDto) {
			PhoneEntity phoneEntity = new PhoneEntity();
			phoneEntity.setNumber(p.getNumber());
			phoneEntity.setType(p.getType());
			phoneEntity.setMainContactEntity(mainContactEntity);
			mainContactEntity.setPhone(phoneEntities);
			phoneEntities.add(phoneEntity);

			contactRepository.save(mainContactEntity);
		}
	}

}
