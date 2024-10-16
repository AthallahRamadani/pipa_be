package com.vinilon.pipa.service;


import com.vinilon.pipa.model.BodyDies;
import com.vinilon.pipa.utils.dto.BodyDiesRequest;
import com.vinilon.pipa.utils.dto.BodyDiesResponse;

import java.util.List;

public interface BodyDiesService{
    BodyDies create(BodyDiesRequest request);
    List<BodyDiesResponse> getAll();
    List<BodyDiesResponse> getAllByMachineId(Integer machineId);
    BodyDies getById(Integer id);
    void delete(Integer id);
    BodyDies updateById(Integer id, BodyDiesRequest req);
}
