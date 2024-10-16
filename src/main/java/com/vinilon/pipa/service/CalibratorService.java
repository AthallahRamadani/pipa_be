package com.vinilon.pipa.service;

import com.vinilon.pipa.utils.dto.CalibratorRequest;
import com.vinilon.pipa.utils.dto.CalibratorResponse;

import java.util.List;

public interface CalibratorService {
    CalibratorResponse create(CalibratorRequest request);
    List<CalibratorResponse> getAll();
    CalibratorResponse getById(Integer id);
    List<CalibratorResponse> getByThickness(Integer thickness);
    void delete(Integer id);
    CalibratorResponse updateById(Integer id, CalibratorRequest req);
}
