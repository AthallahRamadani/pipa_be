package com.vinilon.pipa.controller;

import com.vinilon.pipa.service.MachineService;
import com.vinilon.pipa.utils.dto.MachineRequest;
import com.vinilon.pipa.utils.responseWrapper.Response;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;

    @PostMapping("/machines")
    public ResponseEntity<?> createMachine(@RequestBody MachineRequest request) throws BadRequestException {
        return Response.renderJSON(
                machineService.create(request),
                "Machine Created!",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/machines")
    public ResponseEntity<?> getAllMachines() {
        return Response.renderJSON(machineService.getAll());
    }

    @GetMapping("/machines/{id}")
    public ResponseEntity<?> getMachineById(@PathVariable Integer id) {
        return Response.renderJSON(machineService.getById(id));
    }

    @GetMapping("/machines/by-diameter/{diameter}")
    public ResponseEntity<?> getMachineByDiameter(@PathVariable Integer diameter) {
        return Response.renderJSON(machineService.getByDiameter(diameter));
    }

    @PutMapping("/machines/{id}")
    public ResponseEntity<?> updateMachine(@PathVariable Integer id, @RequestBody MachineRequest request) throws BadRequestException {
        return Response.renderJSON(
                machineService.updateById(id, request),
                "Machine Updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/machines/{id}")
    public ResponseEntity<?> deleteMachine(@PathVariable Integer id) {
        machineService.delete(id);
        return Response.renderJSON(null, "Success Deleting Machine", HttpStatus.OK);
    }
}
