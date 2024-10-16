package com.vinilon.pipa.controller;

import com.vinilon.pipa.service.PinService;
import com.vinilon.pipa.utils.dto.PinRequest;
import com.vinilon.pipa.utils.responseWrapper.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class PinController {

    private final PinService pinService;

    @PostMapping("/pins")
    public ResponseEntity<?> createPin(@RequestBody PinRequest request) {
        return Response.renderJSON(
                pinService.create(request),
                "Pin Created!",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/pins")
    public ResponseEntity<?> getAllPins() {
        return Response.renderJSON(pinService.getAll());
    }

    @GetMapping("/pins/by-bodyDies/{id}")
    public ResponseEntity<?> getPinsByBodyDiesId(@PathVariable Integer id) {
        return Response.renderJSON(pinService.getAllByBodyDiesId(id));
    }

    @GetMapping("/pins/{id}")
    public ResponseEntity<?> getPinById(@PathVariable Integer id) {
        return Response.renderJSON(pinService.getById(id));
    }

    @PutMapping("/pins/{id}")
    public ResponseEntity<?> updatePin(@PathVariable Integer id, @RequestBody PinRequest request) {
        return Response.renderJSON(
                pinService.updateById(id, request),
                "Pin Updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/pins/{id}")
    public ResponseEntity<?> deletePin(@PathVariable Integer id) {
        pinService.delete(id);
        return Response.renderJSON(null, "Success Deleting Pin", HttpStatus.OK);
    }
}

