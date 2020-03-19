package com.cubic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.dtos.MainContactDto;
import com.cubic.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContectController {

	@Autowired
	private ContactService contactService;

	@PostMapping
	public void createContect(@RequestBody MainContactDto mainContactDto) {
		contactService.createContactInfo(mainContactDto);
	}

}
