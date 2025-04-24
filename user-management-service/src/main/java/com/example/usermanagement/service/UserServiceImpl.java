package com.example.usermanagement.service;

import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.exception.ExternalApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final RestTemplate restTemplate;

    @Value("${external.api.users-url}")
    private String usersApiUrl;

    @Override
    @Retryable(
        value = ExternalApiException.class,
        maxAttemptsExpression = "${external.api.retry.max-attempts}",
        backoff = @Backoff(
            delayExpression = "${external.api.retry.initial-interval}",
            multiplierExpression = "${external.api.retry.multiplier}",
            maxDelayExpression = "${external.api.retry.max-interval}"
        )
    )
    @Transactional
    public void loadUsersFromExternalApi() {
        log.info("Loading users from external API: {}", usersApiUrl);
        try {
            Map<String, Object> response = restTemplate.getForObject(usersApiUrl, Map.class);
            if (response != null && response.containsKey("users")) {
                List<Map<String, Object>> users = (List<Map<String, Object>>) response.get("users");
                users.forEach(this::saveUser);
                log.info("Successfully loaded {} users from external API", users.size());
            }
        } catch (Exception e) {
            log.error("Error loading users from external API", e);
            throw new ExternalApiException("Failed to load users from external API", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> searchUsers(String searchTerm) {
        log.debug("Searching users with term: {}", searchTerm);
        SearchSession searchSession = Search.session(entityManager);
        
        return searchSession.search(User.class)
                .where(f -> f.match()
                    .fields("firstName", "lastName", "ssn")
                    .matching(searchTerm))
                .fetchHits(20);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void saveUser(Map<String, Object> userData) {
        User user = new User();
        user.setId(Long.valueOf(userData.get("id").toString()));
        user.setFirstName((String) userData.get("firstName"));
        user.setLastName((String) userData.get("lastName"));
        user.setEmail((String) userData.get("email"));
        user.setSsn((String) userData.get("ssn"));
        user.setPhone((String) userData.get("phone"));
        user.setAddress((String) userData.get("address"));
        user.setGender((String) userData.get("gender"));
        user.setImage((String) userData.get("image"));
        user.setDescription((String) userData.get("description"));
        
        userRepository.save(user);
    }
} 