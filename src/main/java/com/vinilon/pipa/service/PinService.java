package com.vinilon.pipa.service;

import com.vinilon.pipa.model.Machine;
import com.vinilon.pipa.model.Pin;
import com.vinilon.pipa.utils.dto.*;

import java.util.List;

public interface PinService {
    PinResponse create(PinRequest request);
    List<PinResponse> getAll();
    List<PinResponse> getAllByBodyDiesId(Integer bodyDiesId);
    PinResponse getById(Integer id);
    void delete(Integer id);
    PinResponse updateById(Integer id, PinRequest req);
}
