package com.fishinghub.fishinghub.controller;
import com.fishinghub.fishinghub.entity.Catch;
import com.fishinghub.fishinghub.service.CatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catches")
public class CatchController {

    @Autowired
    private CatchService catchService;

    @PostMapping
    public ResponseEntity<Catch> logCatch(@RequestBody Catch catchRecord) {
        Catch newCatch = catchService.logCatch(catchRecord);
        return ResponseEntity.ok(newCatch);
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
    public ResponseEntity<Catch> updateCatch(@RequestBody Catch catchRecord, @PathVariable Long id) {
        catchRecord.setId(id);
        Catch updatedCatch = catchService.updateCatch(catchRecord);
        return ResponseEntity.ok(updatedCatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCatch(@PathVariable Long id) {
        catchService.deleteCatch(id);
        return ResponseEntity.noContent().build();
    }
}
