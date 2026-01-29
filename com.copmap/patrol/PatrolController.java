package com.copmap.patrol;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patrols")
public class PatrolController {

    private final PatrolService patrolService;

    public PatrolController(PatrolService patrolService) {
        this.patrolService = patrolService;
    }

    @PostMapping
    public Patrol create(@RequestBody Patrol patrol) {
        return patrolService.createPatrol(patrol);
    }

    @GetMapping
    public List<Patrol> getAll() {
        return patrolService.getAll();
    }

    @PostMapping("/{id}/assign/{officerId}")
    public void assignOfficer(@PathVariable Long id, @PathVariable Long officerId) {
        patrolService.assignOfficer(id, officerId);
    }
}
