package ua.nure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.model.Group;
import ua.nure.service.GroupService;
import ua.nure.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for managing GardenGroup.
 */
@RestController
@RequestMapping("/groups")
public class GardenGroupResource {

    private final Logger log = LoggerFactory.getLogger(GardenGroupResource.class);

    private static final String ENTITY_NAME = "gardenGroup";

    private final GroupService gardenGroupService;

    public GardenGroupResource(GroupService gardenGroupService) {
        this.gardenGroupService = gardenGroupService;
    }

    /**
     * POST  /groups : Create a new gardenGroup.
     *
     * @param gardenGroup the gardenGroup to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gardenGroup, or with status 400 (Bad Request) if the gardenGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping
    public ResponseEntity<Group> createGardenGroup(@RequestBody Group gardenGroup) throws URISyntaxException {
        log.debug("REST request to save GardenGroup : {}", gardenGroup);
        Group result = gardenGroupService.save(gardenGroup);
        return ResponseEntity.created(new URI("/groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * PUT  /groups : Updates an existing gardenGroup.
     *
     * @param gardenGroup the gardenGroup to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gardenGroup,
     * or with status 400 (Bad Request) if the gardenGroup is not valid,
     * or with status 500 (Internal Server Error) if the gardenGroup couldn't be updated
     */
    @PutMapping
    public ResponseEntity<Group> updateGardenGroup(@RequestBody Group gardenGroup) {
        log.debug("REST request to update GardenGroup : {}", gardenGroup);
        Group result = gardenGroupService.save(gardenGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gardenGroup.getId()))
            .body(result);
    }

    /**
     * DELETE  /groups/:id : delete the "id" gardenGroup.
     *
     * @param id the id of the gardenGroup to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGardenGroup(@PathVariable String id) {
        log.debug("REST request to delete GardenGroup : {}", id);
        gardenGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
