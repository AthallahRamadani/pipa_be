package com.vinilon.pipa.controller;

import com.vinilon.pipa.service.CalibratorService;
import com.vinilon.pipa.utils.dto.CalibratorRequest;
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
public class CalibratorController {

    private final CalibratorService calibratorService;

    @PostMapping("/calibrators")
    public ResponseEntity<?> createCalibrator(@RequestBody CalibratorRequest request) {
        return Response.renderJSON(
                calibratorService.create(request),
                "Calibrator Created!",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/calibrators")
    public ResponseEntity<?> getAllCalibrators() {
        return Response.renderJSON(calibratorService.getAll());
    }

    @GetMapping("/calibrators/{id}")
    public ResponseEntity<?> getCalibratorById(@PathVariable Integer id) {
        return Response.renderJSON(calibratorService.getById(id));
    }

    @GetMapping("/calibrators/by-thickness/{thickness}")
    public ResponseEntity<?> getCalibratorsByThickness(@PathVariable Integer thickness) {
        return Response.renderJSON(calibratorService.getByThickness(thickness));
    }

    @PutMapping("/calibrators/{id}")
    public ResponseEntity<?> updateCalibrator(@PathVariable Integer id, @RequestBody CalibratorRequest request) {
        return Response.renderJSON(
                calibratorService.updateById(id, request),
                "Calibrator Updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/calibrators/{id}")
    public ResponseEntity<?> deleteCalibrator(@PathVariable Integer id) {
        calibratorService.delete(id);
        return Response.renderJSON(null, "Success Deleting Calibrator", HttpStatus.OK);
    }
}

