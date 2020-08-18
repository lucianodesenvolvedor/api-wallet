package com.api.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.api.wallet.entity.User;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	private static final String EMAIL = "email@test.com";

	@Autowired
	UserRepository repository;

	@Test
	public void testSave() {
		User user = new User();
		user.setName("Teste1");
		user.setPassword("12345");
		user.setEmail("test@test.com");
		User response = repository.save(user);
		assertNotNull(response);
	}

	@Test
	public void testFindByEmail() {
		insertUser();
		Optional<User> response = repository.findByEmailEquals(EMAIL);
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
		deleteAll();
	}

	private void insertUser() {
		User user = new User();
		user.setName("Set up User");
		user.setPassword("Senha123");
		user.setEmail(EMAIL);
		repository.save(user);
	}

	private void deleteAll() {
		repository.deleteAll();
	}
}
