package com.villamorvinzie.view.controller;

import com.villamorvinzie.view.dto.request.ActivityRequestDto;
import com.villamorvinzie.view.dto.response.ActivityResponseDto;
import com.villamorvinzie.view.exception.ActivityNotFoundException;
import com.villamorvinzie.view.service.ActivityService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<ActivityResponseDto> createActivity(@RequestBody ActivityRequestDto req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.createActivity(req));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ActivityResponseDto> updateActivity(
            @PathVariable String id, @RequestBody ActivityRequestDto req)
            throws ActivityNotFoundException {
        return ResponseEntity.ok().body(activityService.updateActivity(id, req));
    }

    @GetMapping
    ResponseEntity<List<ActivityResponseDto>> findAll(
            @RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate to) {
        List<ActivityResponseDto> response;
        if (from == null || to == null) {
            response = activityService.findAll();
        } else {
            response = activityService.findAllByCreatedBetween(from, to);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable String id)
            throws ActivityNotFoundException {
        activityService.deleteActivity(id);
        return ResponseEntity.ok().build();
    }
}
