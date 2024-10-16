package com.vinilon.pipa.controller;

import com.vinilon.pipa.config.NotFoundException;
import com.vinilon.pipa.service.BodyDiesService;
import com.vinilon.pipa.utils.dto.BodyDiesRequest;
import com.vinilon.pipa.utils.dto.BodyDiesResponse;
import com.vinilon.pipa.utils.responseWrapper.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class BodyDiesController {

    private final BodyDiesService bodyDiesService;

    @PostMapping("/body-dies")
    public ResponseEntity<?> createBodyDies(@RequestBody BodyDiesRequest request) {
        return Response.renderJSON(
                bodyDiesService.create(request),
                "BodyDies Created!",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/body-dies")
    public ResponseEntity<?> getAllBodyDies() {
        return Response.renderJSON(bodyDiesService.getAll());
    }

    @GetMapping("/body-dies/{id}")
    public ResponseEntity<?> getBodyDiesById(@PathVariable Integer id) {
        return Response.renderJSON(bodyDiesService.getById(id));
    }

    @GetMapping("/body-dies/by-machine/{id}")
    public ResponseEntity<?> getBodyDiesByMachineId(@PathVariable Integer id) {
        try {
            List<BodyDiesResponse> responses = bodyDiesService.getAllByMachineId(id);
            return Response.renderJSON(responses);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/body-dies/{id}")
    public ResponseEntity<?> updateBodyDies(@PathVariable Integer id, @RequestBody BodyDiesRequest request) {
        return Response.renderJSON(
                bodyDiesService.updateById(id, request),
                "BodyDies Updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/body-dies/{id}")
    public ResponseEntity<?> deleteBodyDies(@PathVariable Integer id) {
        bodyDiesService.delete(id);
        return Response.renderJSON(null, "Success Deleting BodyDies", HttpStatus.OK);
    }
}

