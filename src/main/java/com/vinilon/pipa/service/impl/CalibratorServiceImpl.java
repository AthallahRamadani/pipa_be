package com.vinilon.pipa.service.impl;

import com.vinilon.pipa.config.NotFoundException;
import com.vinilon.pipa.model.Calibrator;
import com.vinilon.pipa.repository.CalibratorRepository;
import com.vinilon.pipa.service.CalibratorService;
import com.vinilon.pipa.utils.dto.CalibratorRequest;
import com.vinilon.pipa.utils.dto.CalibratorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalibratorServiceImpl implements CalibratorService {
    private final CalibratorRepository calibratorRepository;

    @Override
    public CalibratorResponse create(CalibratorRequest request) {
        Calibrator calibrator = Calibrator.builder()
                .name(request.getName())
                .calibratorStatus(request.getCalibratorStatus())
                .thicknessMin(request.getThicknessMin())
                .thicknessMax(request.getThicknessMax())
                .build();
        calibratorRepository.save(calibrator);
        return mapToResponse(calibrator);
    }

    @Override
    public List<CalibratorResponse> getAll() {
        return calibratorRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CalibratorResponse getById(Integer id) {
        Calibrator calibrator = calibratorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Calibrator not found with ID: " + id));
        return mapToResponse(calibrator);
    }

    @Override
    public List<CalibratorResponse> getByThickness(Integer thickness) {
        List<Calibrator> calibrators = calibratorRepository.findAll().stream()
                .filter(c -> c.getThicknessMin() <= thickness && c.getThicknessMax() >= thickness)
                .toList();

        if (calibrators.isEmpty()) {
            throw new NotFoundException("No calibrators found with thickness: " + thickness);
        }

        return calibrators.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        Calibrator calibrator = calibratorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Calibrator not found with ID: " + id));
        calibratorRepository.delete(calibrator);
    }

    @Override
    public CalibratorResponse updateById(Integer id, CalibratorRequest req) {
        Calibrator existingCalibrator = calibratorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Calibrator not found with ID: " + id));

        if (req.getName() != null) {
            existingCalibrator.setName(req.getName());
        }
        if (req.getCalibratorStatus() != null) {
            existingCalibrator.setCalibratorStatus(req.getCalibratorStatus());
        }
        if (req.getThicknessMin() != null) {
            existingCalibrator.setThicknessMin(req.getThicknessMin());
        }
        if (req.getThicknessMax() != null) {
            existingCalibrator.setThicknessMax(req.getThicknessMax());
        }

        calibratorRepository.save(existingCalibrator);

        return mapToResponse(existingCalibrator);
    }

    private CalibratorResponse mapToResponse(Calibrator calibrator) {
        return CalibratorResponse.builder()
                .id(calibrator.getId())
                .name(calibrator.getName())
                .calibratorStatus(calibrator.getCalibratorStatus())
                .thicknessMin(calibrator.getThicknessMin())
                .thicknessMax(calibrator.getThicknessMax())
                .build();
    }
}
