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
	
	private User updateToUser(UserUpdateDTO updateDTO) {
		return this.mapper.map(updateDTO, User.class);
	}
	
	@PostMapping("/add/{numLength}")
	public UserDTO add(@RequestBody UserUpdateDTO user, @PathVariable int numLength) {
		return this.service.add(updateToUser(user), numLength);
	}
	
	@GetMapping("/")
	public List<UserDTO> getAll(){
		return this.service.getAll();
	}
	
	@GetMapping("/{id}")
	public UserDTO getById(@PathVariable Long id){
		return this.service.getDTOById(id);
	}
	
	@PutMapping("/put/{id}")
	public UserDTO update(@RequestBody UserUpdateDTO user, @PathVariable Long id) {
		return this.service.update(user.getForename(), user.getSurname(), id);
	}
	
	@PutMapping("/{id}/new-account-number/{numLength}")
	public UserDTO generateNewAccountNumber(@PathVariable Long id, @PathVariable int numLength) {
		return this.service.newAccountNumber(id, numLength);
	}
	
	@DeleteMapping("/del/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.delete(id);
	}
}
