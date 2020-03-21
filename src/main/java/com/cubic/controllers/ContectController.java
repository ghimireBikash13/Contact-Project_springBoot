package com.cubic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.dtos.MainContactDto;
import com.cubic.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContectController {

	@Autowired
	private ContactService contactService;

	@PostMapping
	public void createContect(@RequestBody MainContactDto mainContactDto) {
		contactService.createContactInfo(mainContactDto);
	}

	@GetMapping(value = "/{id}")
	public MainContactDto getContacts(@PathVariable("id") int id) {
		return contactService.getContact(id);
	}

	@GetMapping
	public List<MainContactDto> getAll() {
		return contactService.getAllContacts();
	}

	@PutMapping(value = "/{id}")
	public void updateById(@PathVariable("id") int id, @RequestBody MainContactDto mainContactDto) {
		contactService.updateContacts(id, mainContactDto);
	}

}
