package com.vinilon.pipa.service;

import com.vinilon.pipa.utils.dto.MachineRequest;
import com.vinilon.pipa.utils.dto.MachineResponse;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface MachineService {
    MachineResponse create(MachineRequest request) throws BadRequestException;
    List<MachineResponse> getAll();
    MachineResponse getById(Integer id);
    List<MachineResponse> getByDiameter(Integer diameter);
    void delete(Integer id);
    MachineResponse updateById(Integer id, MachineRequest req) throws BadRequestException;
}
