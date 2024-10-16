package com.vinilon.pipa.service.impl;

import com.vinilon.pipa.config.NotFoundException;
import com.vinilon.pipa.model.BodyDies;
import com.vinilon.pipa.model.Machine;
import com.vinilon.pipa.repository.BodyDiesRepository;
import com.vinilon.pipa.repository.MachineRepository;
import com.vinilon.pipa.service.BodyDiesService;
import com.vinilon.pipa.utils.dto.BodyDiesRequest;
import com.vinilon.pipa.utils.dto.BodyDiesResponse;
import com.vinilon.pipa.utils.dto.MachineResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodyDiesServiceImpl implements BodyDiesService {
    private final BodyDiesRepository bodyDiesRepository;
    private final MachineRepository machineRepository;

    @Override
    public BodyDies create(BodyDiesRequest request) {
        BodyDies bodyDies = BodyDies.builder()
                .name(request.getName())
                .bodyDiesStatus(request.getBodyDiesStatus())
                .machines(request.getMachines())
                .build();
        bodyDiesRepository.save(bodyDies);
        return bodyDies;
    }

    @Override
    public List<BodyDiesResponse> getAll() {
        return bodyDiesRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BodyDiesResponse> getAllByMachineId(Integer machineId) {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new NotFoundException("Machine not found with id: " + machineId));

        return bodyDiesRepository.findAll().stream()
                .filter(bd -> {
                    List<String> machines = bd.getMachines();
                    System.out.println("BodyDie Machines: " + machines);
                    return machines != null && machines.contains(machine.getName());
                })
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public BodyDies getById(Integer id) {
        return bodyDiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BodyDies not found with ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        BodyDies bodyDies = bodyDiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BodyDies not found with ID: " + id));
        bodyDiesRepository.delete(bodyDies);
    }

    @Override
    public BodyDies updateById(Integer id, BodyDiesRequest req) {
        BodyDies bodyDies = bodyDiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BodyDies not found with ID: " + id));

        bodyDies.setName(Optional.ofNullable(req.getName()).filter(name -> !name.isEmpty()).orElse(bodyDies.getName()));

        if (req.getBodyDiesStatus() != null) {
            bodyDies.setBodyDiesStatus(req.getBodyDiesStatus());
        }

        if (req.getMachines() != null) {
            bodyDies.setMachines(req.getMachines());
        }

        bodyDiesRepository.save(bodyDies);
        return bodyDies;
    }


    private BodyDiesResponse mapToResponse(BodyDies bodyDies) {

        return BodyDiesResponse.builder()
                .id(bodyDies.getId())
                .name(bodyDies.getName())
                .bodyDiesStatus(bodyDies.getBodyDiesStatus())
                .machine(bodyDies.getMachines())
                .build();
    }
}
