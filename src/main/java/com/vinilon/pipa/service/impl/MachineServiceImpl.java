package com.vinilon.pipa.service.impl;

import com.vinilon.pipa.config.NotFoundException;
import com.vinilon.pipa.model.BodyDies;
import com.vinilon.pipa.model.Machine;
import com.vinilon.pipa.repository.BodyDiesRepository;
import com.vinilon.pipa.repository.MachineRepository;
import com.vinilon.pipa.service.MachineService;
import com.vinilon.pipa.utils.dto.MachineRequest;
import com.vinilon.pipa.utils.dto.MachineResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;
    private final BodyDiesRepository bodyDiesRepository;

    @Override
    public MachineResponse create(MachineRequest request) throws BadRequestException {
        BodyDies attachedBodyDies = null;

        if (request.getAttachedBodyDies() != null) {
            attachedBodyDies = bodyDiesRepository.findById(request.getAttachedBodyDies())
                    .orElseThrow(() -> new NotFoundException("BodyDies not found with ID: " + request.getAttachedBodyDies()));

            if (attachedBodyDies.getAttachedMachine() != null) {
                throw new BadRequestException("BodyDies is already attached to another machine: "
                        + attachedBodyDies.getAttachedMachine().getName());
            }

            boolean isContainsMachine = attachedBodyDies.getMachines().contains(request.getName());
            if (!isContainsMachine) {
                throw new BadRequestException("BodyDies does not match with machine: " + request.getName());
            }
        }

        Machine machine = Machine.builder()
                .name(request.getName())
                .machineStatus(request.getMachineStatus())
                .diameterMin(request.getDiameterMin())
                .diameterMax(request.getDiameterMax())
                .attachedBodyDies(attachedBodyDies)
                .build();
        machineRepository.save(machine);
        return mapToResponse(machine);
    }

    @Override
    public List<MachineResponse> getAll() {
        return machineRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MachineResponse getById(Integer id) {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Machine not found with ID: " + id));
        return mapToResponse(machine);
    }

    @Override
    public List<MachineResponse> getByDiameter(Integer diameter) {
        List<Machine> machines = machineRepository.findAll().stream()
                .filter(m -> m.getDiameterMin() <= diameter && m.getDiameterMax() >= diameter)
                .toList();

        if (machines.isEmpty()) {
            throw new NotFoundException("No machines found with diameter: " + diameter);
        }

        return machines.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Machine not found with ID: " + id));
        machineRepository.delete(machine);
    }

    @Override
    public MachineResponse updateById(Integer id, MachineRequest req) throws BadRequestException {
        Machine existingMachine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Machine not found with ID: " + id));

        if (req.getAttachedBodyDies() == null) {
            existingMachine.setAttachedBodyDies(null);
        } else {
            BodyDies newBodyDies = bodyDiesRepository.findById(req.getAttachedBodyDies())
                    .orElseThrow(() -> new NotFoundException("BodyDies not found with ID: " + req.getAttachedBodyDies()));

            if (newBodyDies.getAttachedMachine() != null && !newBodyDies.getAttachedMachine().getId().equals(id)) {
                throw new BadRequestException("BodyDies is already attached to another machine: "
                        + newBodyDies.getAttachedMachine().getName());
            }

            boolean isContainsMachine = newBodyDies.getMachines().contains(existingMachine.getName());

            if (!isContainsMachine) {
                throw new BadRequestException("BodyDies does not match with machine: " + existingMachine.getName());
            }

            existingMachine.setAttachedBodyDies(newBodyDies);
            newBodyDies.setAttachedMachine(existingMachine);
        }

        if (req.getName() != null) {
            existingMachine.setName(req.getName());
        }
        if (req.getMachineStatus() != null) {
            existingMachine.setMachineStatus(req.getMachineStatus());
        }
        if (req.getDiameterMin() != null) {
            existingMachine.setDiameterMin(req.getDiameterMin());
        }
        if (req.getDiameterMax() != null) {
            existingMachine.setDiameterMax(req.getDiameterMax());
        }

        machineRepository.save(existingMachine);

        return mapToResponse(existingMachine);
    }


    public MachineResponse mapToResponse(Machine machine) {
        return MachineResponse.builder()
                .id(machine.getId())
                .name(machine.getName())
                .machineStatus(machine.getMachineStatus())
                .diameterMin(machine.getDiameterMin())
                .diameterMax(machine.getDiameterMax())
                .attachedBodyDies(machine.getAttachedBodyDies())
                .build();
    }
}
