package com.example.usermanagement.service;

import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.exception.ExternalApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private EntityManager entityManager;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    private User testUser;
//    private Map<String, Object> externalApiResponse;
//
//    @BeforeEach
//    void setUp() {
//        testUser = new User();
//        testUser.setId(1L);
//        testUser.setFirstName("John");
//        testUser.setLastName("Doe");
//        testUser.setEmail("john.doe@example.com");
//        testUser.setSsn("123-45-6789");
//
//        List<Map<String, Object>> users = new ArrayList<>();
//        Map<String, Object> user = new HashMap<>();
//        user.put("id", 1);
//        user.put("firstName", "John");
//        user.put("lastName", "Doe");
//        user.put("email", "john.doe@example.com");
//        user.put("ssn", "123-45-6789");
//        users.add(user);
//
//        externalApiResponse = new HashMap<>();
//        externalApiResponse.put("users", users);
//    }
//
//    @Test
//    void loadUsersFromExternalApi_Success() {
//        when(restTemplate.getForObject(any(String.class), eq(Map.class)))
//                .thenReturn(externalApiResponse);
//        when(userRepository.save(any(User.class))).thenReturn(testUser);
//
//        assertDoesNotThrow(() -> userService.loadUsersFromExternalApi());
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    @Test
//    void loadUsersFromExternalApi_ThrowsException() {
//        when(restTemplate.getForObject(any(String.class), eq(Map.class)))
//                .thenThrow(new RuntimeException("API Error"));
//
//        assertThrows(ExternalApiException.class, () -> userService.loadUsersFromExternalApi());
//    }
//
//    @Test
//    void findById_Success() {
//        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
//
//        Optional<User> result = userService.findById(1L);
//        assertTrue(result.isPresent());
//        assertEquals(testUser.getEmail(), result.get().getEmail());
//    }
//
//    @Test
//    void findByEmail_Success() {
//        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(testUser));
//
//        Optional<User> result = userService.findByEmail("john.doe@example.com");
//        assertTrue(result.isPresent());
//        assertEquals(testUser.getId(), result.get().getId());
//    }
//
//    @Test
//    void findAll_Success() {
//        List<User> users = Collections.singletonList(testUser);
//        when(userRepository.findAll()).thenReturn(users);
//
//        List<User> result = userService.findAll();
//        assertFalse(result.isEmpty());
//        assertEquals(1, result.size());
//    }


    import org.junit.jupiter.api.Test;
    import org.springframework.boot.test.context.SpringBootTest;

    @SpringBootTest
    class SpringBootJpaH2ApplicationTests {

        @Test
        void contextLoads() {
        }

    }
} 