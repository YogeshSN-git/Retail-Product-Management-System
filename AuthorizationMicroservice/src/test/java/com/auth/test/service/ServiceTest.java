package com.auth.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth.controller.UnauthorizedException;
import com.auth.dao.UserDAO;
import com.auth.model.UserData;
import com.auth.service.CustomerDetailsService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

	UserDetails userdetails;

	@InjectMocks
	CustomerDetailsService custdetailservice;

	@Mock
	UserDAO userservice;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void loadUserByUsernameTest() {

		UserData user1 = new UserData("admin", "admin", "admin", null);
		Optional<UserData> data = Optional.of(user1);
		when(userservice.findById("admin")).thenReturn(data);
		UserDetails loadUserByUsername2 = custdetailservice.loadUserByUsername("admin");
		assertEquals(user1.getUserid(), loadUserByUsername2.getUsername());
	}

	@Test(expected = UnauthorizedException.class)
	public void loadUserByUsernameTestFail() {
		Optional<UserData> data = null;
		when(userservice.findById("admin")).thenReturn(data);
		custdetailservice.loadUserByUsername("admin");
	}
}
