package de.uulm.llam.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.uulm.llam.domain.Role;
import de.uulm.llam.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Role.
 */
@RestController
@RequestMapping("/api")
public class RoleResource {

    private final Logger log = LoggerFactory.getLogger(RoleResource.class);

    @Inject
    private RoleRepository roleRepository;

    /**
     * POST  /roles -> Create a new role.
     */
    @RequestMapping(value = "/roles",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Role role) {
        log.debug("REST request to save Role : {}", role);
        roleRepository.save(role);
    }

    /**
     * GET  /roles -> get all the roles.
     */
    @RequestMapping(value = "/roles",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Role> getAll() {
        log.debug("REST request to get all Roles");
        return roleRepository.findAll();
    }

    /**
     * GET  /roles/:id -> get the "id" role.
     */
    @RequestMapping(value = "/roles/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Role> get(@PathVariable Long id) {
        log.debug("REST request to get Role : {}", id);
        return Optional.ofNullable(roleRepository.findOne(id))
            .map(role -> new ResponseEntity<>(
                role,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /roles/:id -> delete the "id" role.
     */
    @RequestMapping(value = "/roles/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Role : {}", id);
        roleRepository.delete(id);
    }
}
