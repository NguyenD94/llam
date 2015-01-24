package de.uulm.llam.web.rest;

import de.uulm.llam.Application;
import de.uulm.llam.domain.User;
import de.uulm.llam.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UserResourceTest {

    private static final String DEFAULT_LOGIN = "SAMPLE_TEXT";
    private static final String UPDATED_LOGIN = "UPDATED_TEXT";
    private static final String DEFAULT_PASSWORD = "SAMPLE_TEXT";
    private static final String UPDATED_PASSWORD = "UPDATED_TEXT";
    private static final String DEFAULT_FIRST_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_FIRST_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_LAST_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_LAST_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";

    private static final Boolean DEFAULT_ACTIVATED = false;
    private static final Boolean UPDATED_ACTIVATED = true;
    private static final String DEFAULT_LANG_KEY = "SAMPLE_TEXT";
    private static final String UPDATED_LANG_KEY = "UPDATED_TEXT";
    private static final String DEFAULT_ACTIVATION_KEY = "SAMPLE_TEXT";
    private static final String UPDATED_ACTIVATION_KEY = "UPDATED_TEXT";

    private static final Integer DEFAULT_STUDENT_NUMBER = 0;
    private static final Integer UPDATED_STUDENT_NUMBER = 1;

    @Inject
    private UserRepository userRepository;

    private MockMvc restUserMockMvc;

    private User user;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserResource userResource = new UserResource();
        ReflectionTestUtils.setField(userResource, "userRepository", userRepository);
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(userResource).build();
    }

    @Before
    public void initTest() {
        user = new User();
        user.setLogin(DEFAULT_LOGIN);
        user.setPassword(DEFAULT_PASSWORD);
        user.setFirstName(DEFAULT_FIRST_NAME);
        user.setLastName(DEFAULT_LAST_NAME);
        user.setEmail(DEFAULT_EMAIL);
        user.setActivated(DEFAULT_ACTIVATED);
        user.setLangKey(DEFAULT_LANG_KEY);
        user.setActivationKey(DEFAULT_ACTIVATION_KEY);
        user.setStudentNumber(DEFAULT_STUDENT_NUMBER);
    }


    @Test
    @Transactional
    public void getAllUsers() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        // Get all the users
        restUserMockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(user.getId().intValue()))
                .andExpect(jsonPath("$.[0].login").value(DEFAULT_LOGIN.toString()))
                .andExpect(jsonPath("$.[0].password").value(DEFAULT_PASSWORD.toString()))
                .andExpect(jsonPath("$.[0].firstName").value(DEFAULT_FIRST_NAME.toString()))
                .andExpect(jsonPath("$.[0].lastName").value(DEFAULT_LAST_NAME.toString()))
                .andExpect(jsonPath("$.[0].email").value(DEFAULT_EMAIL.toString()))
                .andExpect(jsonPath("$.[0].activated").value(DEFAULT_ACTIVATED.booleanValue()))
                .andExpect(jsonPath("$.[0].langKey").value(DEFAULT_LANG_KEY.toString()))
                .andExpect(jsonPath("$.[0].activationKey").value(DEFAULT_ACTIVATION_KEY.toString()))
                .andExpect(jsonPath("$.[0].studentNumber").value(DEFAULT_STUDENT_NUMBER));
    }

    @Test
    @Transactional
    public void getUser() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        // Get the user
        restUserMockMvc.perform(get("/api/users/{id}", user.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(user.getId().intValue()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.activated").value(DEFAULT_ACTIVATED.booleanValue()))
            .andExpect(jsonPath("$.langKey").value(DEFAULT_LANG_KEY.toString()))
            .andExpect(jsonPath("$.activationKey").value(DEFAULT_ACTIVATION_KEY.toString()))
            .andExpect(jsonPath("$.studentNumber").value(DEFAULT_STUDENT_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingUser() throws Exception {
        // Get the user
        restUserMockMvc.perform(get("/api/users/{id}", 1L))
                .andExpect(status().isNotFound());
    }


  @Test
  public void testGetExistingUser() throws Exception {
    restUserMockMvc.perform(get("/api/users/admin")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.lastName").value("Administrator"));
  }

  @Test
  public void testGetUnknownUser() throws Exception {
    restUserMockMvc.perform(get("/api/users/unknown")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
