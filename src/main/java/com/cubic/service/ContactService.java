package com.cubic.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

	public MainContactDto getContact(int id) {

		Optional<MainContactEntity> mainContactEntity = contactRepository.findById(id);

		MainContactDto mainContactDto = null;

		if (mainContactEntity.isPresent()) {
			mainContactDto = new MainContactDto();
			mainContactDto.setEmail(mainContactEntity.get().getEmail());

			NameEntity nameEntity = mainContactEntity.get().getName();
			NameDto nameDto = new NameDto();
			nameDto.setFirstName(nameEntity.getFirstName());
			nameDto.setMiddleName(nameEntity.getMiddleName());
			nameDto.setLastName(nameEntity.getLastName());
			mainContactDto.setName(nameDto);

			AddressEntity addressEntity = mainContactEntity.get().getAddress();
			AddressDto addressDto = new AddressDto();
			addressDto.setStreet(addressEntity.getStreet());
			addressDto.setCity(addressEntity.getCity());
			addressDto.setState(addressEntity.getState());
			addressDto.setZip(addressEntity.getZip());
			mainContactDto.setAddress(addressDto);

			List<PhoneEntity> phoneEntity = mainContactEntity.get().getPhone();

			List<PhoneDto> phoneDto = new ArrayList<PhoneDto>();

			for (PhoneEntity p : phoneEntity) {
				PhoneDto phoneDtos = new PhoneDto();
				phoneDtos.setNumber(p.getNumber());
				phoneDtos.setType(p.getType());
				phoneDto.add(phoneDtos);
				mainContactDto.setPhone(phoneDto);

			}
		}
		return mainContactDto;
	}

	public List<MainContactDto> getAllContacts() {
		Iterable<MainContactEntity> mainContactEntity = contactRepository.findAll();

		List<MainContactDto> mainContactDto = new ArrayList<MainContactDto>();
		for (MainContactEntity m : mainContactEntity) {
			MainContactDto mainDto = new MainContactDto();
			mainDto.setEmail(m.getEmail());

			NameEntity nameEntity = m.getName();
			NameDto nameDto = new NameDto();
			nameDto.setFirstName(nameEntity.getFirstName());
			nameDto.setLastName(nameEntity.getLastName());
			nameDto.setMiddleName(nameEntity.getMiddleName());
			mainDto.setName(nameDto);

			AddressEntity addressEntity = m.getAddress();
			AddressDto addressDto = new AddressDto();
			addressDto.setStreet(addressEntity.getStreet());
			addressDto.setCity(addressEntity.getCity());
			addressDto.setState(addressEntity.getState());
			addressDto.setZip(addressEntity.getZip());
			mainDto.setAddress(addressDto);

			List<PhoneEntity> phoneEntity = m.getPhone();
			List<PhoneDto> phoneDtos = new ArrayList<PhoneDto>();
			if (phoneEntity != null) {
				for (PhoneEntity p : phoneEntity) {
					PhoneDto phoneDto = new PhoneDto();
					phoneDto.setNumber(p.getNumber());
					phoneDto.setType(p.getType());
					phoneDtos.add(phoneDto);
					mainDto.setPhone(phoneDtos);
				}

				mainContactDto.add(mainDto);

			}

		}
		return mainContactDto;

	}

	public void updateContacts(int id, MainContactDto mainContactDto) {
		Optional<MainContactEntity> mainContactEntity = contactRepository.findById(id);
		if (mainContactEntity.isPresent()) {
			MainContactEntity mainContactEntity2 = mainContactEntity.get();
			mainContactEntity2.setEmail(mainContactDto.getEmail());
			mainContactEntity2.setUuid(UUID.randomUUID().toString());

			NameEntity nameEntity = mainContactEntity2.getName();
			NameDto nameDto = mainContactDto.getName();
			nameEntity.setFirstName(nameDto.getFirstName());
			nameEntity.setMiddleName(nameDto.getMiddleName());
			nameEntity.setLastName(nameDto.getLastName());
			nameEntity.setMainContactEntity(mainContactEntity2);
			mainContactEntity2.setName(nameEntity);

			AddressEntity addressEntity = mainContactEntity2.getAddress();
			AddressDto addressDto = mainContactDto.getAddress();
			addressEntity.setStreet(addressDto.getStreet());
			addressEntity.setState(addressDto.getState());
			addressEntity.setCity(addressDto.getCity());
			addressEntity.setZip(addressDto.getZip());
			addressEntity.setMainContactEntity(mainContactEntity2);
			mainContactEntity2.setAddress(addressEntity);

			List<PhoneEntity> phoneEntities = mainContactEntity2.getPhone();
			List<PhoneDto> phoneDtos = mainContactDto.getPhone();

			if (phoneDtos != null) {
				for (PhoneDto p : phoneDtos) {
					PhoneEntity phoneEntity = new PhoneEntity();
					phoneEntity.setNumber(p.getNumber());
					phoneEntity.setType(p.getType());
					phoneEntity.setMainContactEntity(mainContactEntity2);
					phoneEntities.add(phoneEntity);

					mainContactEntity2.setPhone(phoneEntities);
				}
			}
			contactRepository.save(mainContactEntity2);
		}

	}
}
