package com.qa.Spring;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.qa.spring.data.model.User;
import com.qa.spring.dto.UserDTO;
import com.qa.spring.dto.UserUpdateDTO;
import com.qa.spring.rest.UserController;
import com.qa.spring.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {
	
	private User user;
	private UserUpdateDTO updateDTO;
	private UserDTO dto;
	private List<UserDTO> dtoList;
	
	private Long id = 1L;
	private int numLength = 6;
	private String accountNumber = "a61824";
	
	@Mock
	private UserService service;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private UserController controller;
	
	@Before
	public void init() {
		this.user = new User("Henry", "Green");
		this.updateDTO = new UserUpdateDTO(this.user.getForename(), this.user.getSurname());
		this.dto = new UserDTO(this.user.getForename(), this.user.getSurname());
		this.dto.setId(id);
		this.dto.setAccountNumber(accountNumber);
		this.dtoList = new ArrayList<UserDTO>();
		this.dtoList.add(dto);
	}
	
	@Test
	public void addTest() {
		Mockito.when(mapper.map(updateDTO, User.class)).thenReturn(user);
		Mockito.when(service.add(user, numLength)).thenReturn(dto);
		
		assertEquals( dto, this.controller.add(updateDTO, numLength));
		
		Mockito.verify(this.mapper, times(1)).map(updateDTO, User.class);
		Mockito.verify(this.service, times(1)).add(user, numLength);
	}
	
	@Test
	public void getAllTest() {
		Mockito.when(service.getAll()).thenReturn(dtoList);
		
		assertEquals( dtoList, this.controller.getAll());
		
		Mockito.verify(this.service, times(1)).getAll();
	}
	
	@Test
	public void getByIdTest() {
		Mockito.when(service.getDTOById(id)).thenReturn(dto);
		
		assertEquals( dto, this.controller.getById(id));
		
		Mockito.verify(this.service, times(1)).getDTOById(id);
	}
}
