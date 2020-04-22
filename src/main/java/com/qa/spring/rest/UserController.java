package com.qa.spring.rest;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.spring.data.model.User;
import com.qa.spring.dto.UserDTO;
import com.qa.spring.dto.UserUpdateDTO;
import com.qa.spring.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService service;

	private ModelMapper mapper;
	
	public UserController(UserService service, ModelMapper mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}
	
	private User mapToUser(UserDTO userDTO) {
		return this.mapper.map(userDTO, User.class);
	}
	
	@PostMapping("/add")
	public UserDTO add(@RequestBody UserDTO user) {
		return this.service.add(mapToUser(user));
	}
	
	@GetMapping("/get")
	public List<UserDTO> getAll(){
		return this.service.getAll();
	}
	
	@PutMapping("/put/{id}")
	public UserDTO update(@RequestBody UserUpdateDTO user, @PathVariable Long id) {
		return this.service.update(user.getForename(), user.getSurname(), id);
	}
	
	@DeleteMapping("/del/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.delete(id);
	}
}
