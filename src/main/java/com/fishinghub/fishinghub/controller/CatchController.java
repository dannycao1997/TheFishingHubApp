package com.fishinghub.fishinghub.controller;
import com.fishinghub.fishinghub.entity.Catch;
import com.fishinghub.fishinghub.service.CatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/catches")
public class CatchController {

    @Autowired
    private CatchService catchService;

    @PostMapping
    public ResponseEntity<Catch> logCatch(@RequestBody Catch catchRecord) {
        Catch newCatch = catchService.logCatch(catchRecord);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCatch.getId())
                .toUri();
        return ResponseEntity.created(location).body(newCatch);
    }
    
    @GetMapping
    public ResponseEntity<List<Catch>> getAllCatches() {
        List<Catch> catches = catchService.getAllCatches();
        return ResponseEntity.ok(catches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catch> getCatchById(@PathVariable Long id) {
        Catch catchRecord = catchService.getCatchById(id);
        return ResponseEntity.ok(catchRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catch> updateCatch(@RequestBody Catch updatedCatch, @PathVariable Long id) {
        updatedCatch.setId(id);
        Catch savedCatch = catchService.updateCatch(id, updatedCatch);
        return ResponseEntity.ok(savedCatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCatch(@PathVariable Long id) {
        catchService.deleteCatch(id);
        return ResponseEntity.noContent().build();
    }
}
