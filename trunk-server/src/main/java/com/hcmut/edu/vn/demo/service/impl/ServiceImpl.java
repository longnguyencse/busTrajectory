package com.hcmut.edu.vn.demo.service.impl;

import com.hcmut.edu.vn.demo.model.RouteModel;
import com.hcmut.edu.vn.demo.model.RouteReponsitory;
import com.hcmut.edu.vn.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by nqlong on 10-Jan-18.
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceImpl implements Service {
    @Autowired
    RouteReponsitory routeReponsitory;

    @Override
    public List<RouteModel> findRouteByIdDirection(int id, int directionId) {
        return routeReponsitory.findAllByRouteIdAndStationDirection(id, directionId);
    }
}