package com.hcmut.edu.vn.demo.service;

import com.hcmut.edu.vn.demo.model.RouteModel;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public interface Service {
    List<RouteModel> findRouteByIdDirection(int id, int directionId);
}
