package com.vinilon.pipa.service.impl;

import com.vinilon.pipa.config.NotFoundException;
import com.vinilon.pipa.model.BodyDies;
import com.vinilon.pipa.model.Pin;
import com.vinilon.pipa.repository.BodyDiesRepository;
import com.vinilon.pipa.repository.PinRepository;
import com.vinilon.pipa.service.PinService;
import com.vinilon.pipa.utils.dto.BodyDiesResponse;
import com.vinilon.pipa.utils.dto.PinRequest;
import com.vinilon.pipa.utils.dto.PinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PinServiceImpl implements PinService {
    private final PinRepository pinRepository;
    private final BodyDiesRepository bodyDiesRepository;

    @Override
    public PinResponse create(PinRequest request) {

        Pin pin = Pin.builder()
                .name(request.getName())
                .pinStatus(request.getPinStatus())
                .bodyDies(request.getBodyDies())
                .build();
        pinRepository.save(pin);
        return mapToResponse(pin);
    }

    @Override
    public List<PinResponse> getAll() {
        return pinRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PinResponse> getAllByBodyDiesId(Integer bodyDiesId) {
        BodyDies bodyDie = bodyDiesRepository.findById(bodyDiesId)
                .orElseThrow(() -> new NotFoundException("BodyDies not found with id: " + bodyDiesId));

        return pinRepository.findAll().stream()
                .filter(pin -> {
                    List<String> bodyDies = pin.getBodyDies();
                    return bodyDies != null && bodyDies.contains(bodyDie.getName());
                })
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public PinResponse getById(Integer id) {
        Pin pin = pinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pin not found with ID: " + id));
        return mapToResponse(pin);
    }

    @Override
    public void delete(Integer id) {
        Pin pin = pinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pin not found with ID: " + id));
        pinRepository.delete(pin);
    }

    @Override
    public PinResponse updateById(Integer id, PinRequest req) {
        Pin existingPin = pinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pin not found with ID: " + id));

        existingPin.setName(Optional.ofNullable(req.getName()).filter(name -> !name.isEmpty()).orElse(existingPin.getName()));
        if (req.getPinStatus() != null) {
            existingPin.setPinStatus(req.getPinStatus());
        }
        if (req.getBodyDies() != null) {
            existingPin.setBodyDies(req.getBodyDies());
        }

        pinRepository.save(existingPin);
        return mapToResponse(existingPin);
    }

    private PinResponse mapToResponse(Pin pin) {

        return PinResponse.builder()
                .id(pin.getId())
                .name(pin.getName())
                .pinStatus(pin.getPinStatus())
                .bodyDies(pin.getBodyDies())
                .build();
    }
}
