package com.banditdev.wishlist.repository;

import com.banditdev.wishlist.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2database.sql", executionPhase = BEFORE_TEST_METHOD)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() {
        User user = new User(4, "test@mail.com", "Bjørn", "pass4321");
        User savedUser = userRepository.addUser(user);

        assertEquals("Bjørn", savedUser.getUserName());
    }

    @Test
    void deleteUser() {
        User user = new User(0, "test@mail.com", "Bjørn", "pass4321");
        User savedUser = userRepository.addUser(user);

        int userId = savedUser.getUserId();

        userRepository.deleteUser(userId);

        User foundUser = userRepository.findUserById(userId);

        assertNull(foundUser);
    }

    @Test
    void ReturnWhenUserEmailExists() {
        User user = new User(0, "test@mail.com", "Bjørn", "pass4321");
        userRepository.addUser(user);

        User found = userRepository.findUserByEmail("test@mail.com");

        assertNotNull(found);
        assertEquals("Bjørn", found.getUserName());
        assertEquals("test@mail.com", found.getUserEmail());
    }

    @Test
    void FindUserIgnoringCaseEmail() {
        User user = new User(0, "Test@Mail.com", "Bjørn", "pass4321");
        userRepository.addUser(user);

        User found = userRepository.findUserByEmail("test@mail.com");

        assertNotNull(found);
        assertEquals("Bjørn", found.getUserName());
    }

    @Test
    void ReturnNullWhenUserNotFoundByEmail() {
        User found = userRepository.findUserByEmail("missing@mail.com");

        assertNull(found);
    }

    @Test
    void ReturnWhenUserExistById() {
        User user = new User(0, "test@mail.com", "Bjørn", "pass4321");
        User savedUser = userRepository.addUser(user);

        User found = userRepository.findUserById(savedUser.getUserId());

        assertNotNull(found);
        assertEquals(savedUser.getUserId(), found.getUserId());
        assertEquals("Bjørn", found.getUserName());
        assertEquals("test@mail.com", found.getUserEmail());
    }

    @Test
    void ReturnNullWhenUserNotFoundById() {
        User found = userRepository.findUserById(999);

        assertNull(found);
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        User user = new User(0, "Test@mail.com", "Bjørn", "pass4321");
        User savedUser = userRepository.addUser(user);

        User updatedUser = new User(
                savedUser.getUserId(),
                "new@mail.com",
                "Jan",
                "1234pass"
        );

        userRepository.updateUser(updatedUser);

        User result = userRepository.findUserById(savedUser.getUserId());

        assertNotNull(result);
        assertEquals("new@mail.com", result.getUserEmail());
        assertEquals("Jan", result.getUserName());
        assertEquals("1234pass", result.getUserPassword());
    }
}