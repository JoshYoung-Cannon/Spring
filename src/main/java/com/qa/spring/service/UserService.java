package com.qa.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.spring.data.model.User;
import com.qa.spring.data.repo.UserRepo;
import com.qa.spring.dto.UserDTO;
import com.qa.spring.exceptions.UserNotFoundException;

@Service
public class UserService {

	private UserRepo repo;

	private AccountNumberService numberService;
	private PrizeCheckerService prizeService;
	
	private ModelMapper mapper;

	public UserService(UserRepo repo, AccountNumberService numberService, PrizeCheckerService prizeService, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.numberService = numberService;
		this.prizeService = prizeService;
		this.mapper = mapper;
	}

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	public UserDTO add(User user, Integer numLength) {
		if (numLength != null) {
			user.setAccountNumber(this.numberService.generateNumber(numLength));
		}
		this.repo.save(user);
		return mapToDTO(user);
	}

	public List<UserDTO> getAll() {
		return this.repo.findAll().stream().map((user) -> this.mapToDTO(user)).collect(Collectors.toList());
	}

	private User getById(Long id) {
		return this.repo.findById(id).orElseThrow(() -> new UserNotFoundException());
	}

	public UserDTO getDTOById(Long id) {
		return mapToDTO(getById(id));
	}

	public UserDTO update(String forename, String surname, Long id) {
		User foundUser = getById(id);
		foundUser.setForename(forename);
		foundUser.setSurname(surname);
		this.repo.save(foundUser);
		return mapToDTO(foundUser);
	}
	
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	public String prizeResult(Long id) {
		User foundUser = getById(id);
		return foundUser.getForename() + " " + foundUser.getSurname() + " You Have Won " + this.prizeService.getPrizeResult(foundUser.getAccountNumber());
	}
}
