package com.qa.Spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.spring.data.model.User;
import com.qa.spring.dto.UserDTO;
import com.qa.spring.dto.UserUpdateDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntergrationTest {
	
	@Autowired
	private MockMvc mvc;

	private ObjectMapper mapper = new ObjectMapper();
	
	private User user;
	
	private UserUpdateDTO updateDTO;
	
	private UserDTO dto;
	
	private List<UserDTO> dtoList;
	
	private Long targetId = 1L;
	
	@Before
	public void init() {
		this.user = new User("Henry", "Green");
		this.dto = new UserDTO(targetId, this.user.getForename(), this.user.getSurname());
		this.updateDTO = new UserUpdateDTO(this.user.getForename(), this.user.getSurname());
		this.dtoList = new ArrayList<UserDTO>();
		this.dtoList.add(dto);
	}
	
	@Test
	public void addIntergrationTest() throws JsonProcessingException, Exception {
		RequestBuilder request = request(HttpMethod.POST, "/user/add/6")
				.content(mapper.writeValueAsString(updateDTO))
				.contentType(MediaType.APPLICATION_JSON);
		
		String responseBody = this.mvc
				.perform(request)
				.andExpect(status().is(200))
				.andReturn()
				.getResponse()
				.getContentAsString();
		
		UserDTO createdUser = this.mapper.readValue(responseBody, UserDTO.class);
		
		assertEquals(dto.getForename(), createdUser.getForename());
		assertEquals(dto.getSurname(), createdUser.getSurname());
		assertNotNull(createdUser.getId());
		assertNotNull(createdUser.getAccountNumber());
		assertEquals(6, createdUser.getAccountNumber().length());
		assertTrue(createdUser.getAccountNumber().substring(0, 1).equals("a") || createdUser.getAccountNumber().substring(0, 1).equals("b") || createdUser.getAccountNumber().substring(0, 1).equals("c"));
	}
	
//	@Test
//	public void getAllIntergrationTest() throws JsonProcessingException, Exception {
//		RequestBuilder request = request(HttpMethod.POST, "/user/add/6")
//				.content(mapper.writeValueAsString(updateDTO))
//				.contentType(MediaType.APPLICATION_JSON);
//		
//		this.mvc.perform(request);
//		
//		request = request(HttpMethod.GET, "/user/");
//		
//		String responseBody = this.mvc.perform(request)
//				.andExpect(status().is(200)).andReturn().getResponse()
//				.getContentAsString();
//		
//		UserDTO[] foundArray = this.mapper.readValue(responseBody, UserDTO[].class);
//		
//	}
	
	@Test
	public void getByIdIntergrationTest() throws JsonProcessingException, Exception {
		RequestBuilder request = request(HttpMethod.POST, "/user/add/6")
				.content(mapper.writeValueAsString(updateDTO))
				.contentType(MediaType.APPLICATION_JSON);
		
		this.mvc.perform(request);
		
		request = request(HttpMethod.GET, "/user/1");
		
		String responseBody = this.mvc.perform(request)
				.andExpect(status().is(200)).andReturn().getResponse()
				.getContentAsString();
		
		UserDTO foundUser = this.mapper.readValue(responseBody, UserDTO.class);
		
		assertEquals(dto.getForename(), foundUser.getForename());
		assertEquals(dto.getSurname(), foundUser.getSurname());
		assertEquals(targetId, foundUser.getId());
		assertNotNull(foundUser.getAccountNumber());
		assertEquals(6, foundUser.getAccountNumber().length());
		assertTrue(foundUser.getAccountNumber().substring(0, 1).equals("a") || foundUser.getAccountNumber().substring(0, 1).equals("b") || foundUser.getAccountNumber().substring(0, 1).equals("c"));
	}
	
	@Test
	public void updateIntergrationTest() throws JsonProcessingException, Exception {
		RequestBuilder request = request(HttpMethod.POST, "/user/add/6")
				.content(mapper.writeValueAsString(updateDTO))
				.contentType(MediaType.APPLICATION_JSON);
		
		this.mvc.perform(request);
		
		updateDTO.setForename("New");
		updateDTO.setSurname("Name");
		dto.setForename("New");
		dto.setSurname("Name");
		request = request(HttpMethod.PUT, "/user/put/1")
				.content(mapper.writeValueAsString(updateDTO))
				.contentType(MediaType.APPLICATION_JSON);
		
		String responseBody = this.mvc.perform(request)
				.andExpect(status().is(200)).andReturn().getResponse()
				.getContentAsString();
		
		UserDTO foundUser = this.mapper.readValue(responseBody, UserDTO.class);
		assertEquals(dto.getForename(), foundUser.getForename());
		assertEquals(dto.getSurname(), foundUser.getSurname());
		assertEquals(targetId, foundUser.getId());
	}
}
