package de.uulm.llam.web.rest;

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
import java.util.List;

import de.uulm.llam.Application;
import de.uulm.llam.domain.Role;
import de.uulm.llam.repository.RoleRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RoleResource REST controller.
 *
 * @see RoleResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class RoleResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";

    private static final Boolean DEFAULT_CAN_CREATE = false;
    private static final Boolean UPDATED_CAN_CREATE = true;

    private static final Boolean DEFAULT_CAN_DISCUSS = false;
    private static final Boolean UPDATED_CAN_DISCUSS = true;

    private static final Boolean DEFAULT_CAN_RATE = false;
    private static final Boolean UPDATED_CAN_RATE = true;

    private static final Boolean DEFAULT_CAN_DELETE = false;
    private static final Boolean UPDATED_CAN_DELETE = true;

    private static final Boolean DEFAULT_CAN_GRANT_ACCESS = false;
    private static final Boolean UPDATED_CAN_GRANT_ACCESS = true;

    private static final Boolean DEFAULT_CAN_EDIT = false;
    private static final Boolean UPDATED_CAN_EDIT = true;

    private static final Boolean DEFAULT_CAN_ADMINISTRATE = false;
    private static final Boolean UPDATED_CAN_ADMINISTRATE = true;

    @Inject
    private RoleRepository roleRepository;

    private MockMvc restRoleMockMvc;

    private Role role;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RoleResource roleResource = new RoleResource();
        ReflectionTestUtils.setField(roleResource, "roleRepository", roleRepository);
        this.restRoleMockMvc = MockMvcBuilders.standaloneSetup(roleResource).build();
    }

    @Before
    public void initTest() {
        role = new Role();
        role.setName(DEFAULT_NAME);
        role.setCanCreate(DEFAULT_CAN_CREATE);
        role.setCanDiscuss(DEFAULT_CAN_DISCUSS);
        role.setCanRate(DEFAULT_CAN_RATE);
        role.setCanDelete(DEFAULT_CAN_DELETE);
        role.setCanGrantAccess(DEFAULT_CAN_GRANT_ACCESS);
        role.setCanEdit(DEFAULT_CAN_EDIT);
        role.setCanAdministrate(DEFAULT_CAN_ADMINISTRATE);
    }

    @Test
    @Transactional
    public void createRole() throws Exception {
        // Validate the database is empty
        assertThat(roleRepository.findAll()).hasSize(0);

        // Create the Role
        restRoleMockMvc.perform(post("/api/roles")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(role)))
                .andExpect(status().isOk());

        // Validate the Role in the database
        List<Role> roles = roleRepository.findAll();
        assertThat(roles).hasSize(1);
        Role testRole = roles.iterator().next();
        assertThat(testRole.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRole.getCanCreate()).isEqualTo(DEFAULT_CAN_CREATE);
        assertThat(testRole.getCanDiscuss()).isEqualTo(DEFAULT_CAN_DISCUSS);
        assertThat(testRole.getCanRate()).isEqualTo(DEFAULT_CAN_RATE);
        assertThat(testRole.getCanDelete()).isEqualTo(DEFAULT_CAN_DELETE);
        assertThat(testRole.getCanGrantAccess()).isEqualTo(DEFAULT_CAN_GRANT_ACCESS);
        assertThat(testRole.getCanEdit()).isEqualTo(DEFAULT_CAN_EDIT);
        assertThat(testRole.getCanAdministrate()).isEqualTo(DEFAULT_CAN_ADMINISTRATE);
    }

    @Test
    @Transactional
    public void getAllRoles() throws Exception {
        // Initialize the database
        roleRepository.saveAndFlush(role);

        // Get all the roles
        restRoleMockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(role.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].canCreate").value(DEFAULT_CAN_CREATE.booleanValue()))
                .andExpect(jsonPath("$.[0].canDiscuss").value(DEFAULT_CAN_DISCUSS.booleanValue()))
                .andExpect(jsonPath("$.[0].canRate").value(DEFAULT_CAN_RATE.booleanValue()))
                .andExpect(jsonPath("$.[0].canDelete").value(DEFAULT_CAN_DELETE.booleanValue()))
                .andExpect(jsonPath("$.[0].canGrantAccess").value(DEFAULT_CAN_GRANT_ACCESS.booleanValue()))
                .andExpect(jsonPath("$.[0].canEdit").value(DEFAULT_CAN_EDIT.booleanValue()))
                .andExpect(jsonPath("$.[0].canAdministrate").value(DEFAULT_CAN_ADMINISTRATE.booleanValue()));
    }

    @Test
    @Transactional
    public void getRole() throws Exception {
        // Initialize the database
        roleRepository.saveAndFlush(role);

        // Get the role
        restRoleMockMvc.perform(get("/api/roles/{id}", role.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(role.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.canCreate").value(DEFAULT_CAN_CREATE.booleanValue()))
            .andExpect(jsonPath("$.canDiscuss").value(DEFAULT_CAN_DISCUSS.booleanValue()))
            .andExpect(jsonPath("$.canRate").value(DEFAULT_CAN_RATE.booleanValue()))
            .andExpect(jsonPath("$.canDelete").value(DEFAULT_CAN_DELETE.booleanValue()))
            .andExpect(jsonPath("$.canGrantAccess").value(DEFAULT_CAN_GRANT_ACCESS.booleanValue()))
            .andExpect(jsonPath("$.canEdit").value(DEFAULT_CAN_EDIT.booleanValue()))
            .andExpect(jsonPath("$.canAdministrate").value(DEFAULT_CAN_ADMINISTRATE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRole() throws Exception {
        // Get the role
        restRoleMockMvc.perform(get("/api/roles/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRole() throws Exception {
        // Initialize the database
        roleRepository.saveAndFlush(role);

        // Update the role
        role.setName(UPDATED_NAME);
        role.setCanCreate(UPDATED_CAN_CREATE);
        role.setCanDiscuss(UPDATED_CAN_DISCUSS);
        role.setCanRate(UPDATED_CAN_RATE);
        role.setCanDelete(UPDATED_CAN_DELETE);
        role.setCanGrantAccess(UPDATED_CAN_GRANT_ACCESS);
        role.setCanEdit(UPDATED_CAN_EDIT);
        role.setCanAdministrate(UPDATED_CAN_ADMINISTRATE);
        restRoleMockMvc.perform(post("/api/roles")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(role)))
                .andExpect(status().isOk());

        // Validate the Role in the database
        List<Role> roles = roleRepository.findAll();
        assertThat(roles).hasSize(1);
        Role testRole = roles.iterator().next();
        assertThat(testRole.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRole.getCanCreate()).isEqualTo(UPDATED_CAN_CREATE);
        assertThat(testRole.getCanDiscuss()).isEqualTo(UPDATED_CAN_DISCUSS);
        assertThat(testRole.getCanRate()).isEqualTo(UPDATED_CAN_RATE);
        assertThat(testRole.getCanDelete()).isEqualTo(UPDATED_CAN_DELETE);
        assertThat(testRole.getCanGrantAccess()).isEqualTo(UPDATED_CAN_GRANT_ACCESS);
        assertThat(testRole.getCanEdit()).isEqualTo(UPDATED_CAN_EDIT);
        assertThat(testRole.getCanAdministrate()).isEqualTo(UPDATED_CAN_ADMINISTRATE);
    }

    @Test
    @Transactional
    public void deleteRole() throws Exception {
        // Initialize the database
        roleRepository.saveAndFlush(role);

        // Get the role
        restRoleMockMvc.perform(delete("/api/roles/{id}", role.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Role> roles = roleRepository.findAll();
        assertThat(roles).hasSize(0);
    }
}
