package com.hcmut.edu.vn.demo.service.impl;

import com.hcmut.edu.vn.demo.model.JourneryModel;
import com.hcmut.edu.vn.demo.model.JourneryReponsitory;
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

    @Autowired
    JourneryReponsitory journeryReponsitory;

    @Override
    public List<RouteModel> findRouteByIdDirection(int id, int directionId) {
        return routeReponsitory.findAllByRouteIdAndStationDirectionAndRouteType(id, directionId, 0);
    }

    @Override
    public List<JourneryModel> findJourneyByJourneyCode(String journeyCode) {
        return journeryReponsitory.findAllByJourneyCode(journeyCode);
    }
}
