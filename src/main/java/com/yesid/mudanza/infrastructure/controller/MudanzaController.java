package com.yesid.mudanza.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yesid.mudanza.application.handler.MakeMoveHandler;

@RestController
@RequestMapping(value = "/mudanza")
public class MudanzaController {

	@Autowired
	MakeMoveHandler makeMoveHandler;
	
	@PostMapping(value = "viajes/{dni}", produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody byte[] makeMove(@RequestParam("file") MultipartFile input, @PathVariable("dni") String dni) {
		return makeMoveHandler.makeElementTrips(input, dni);
	}
}
