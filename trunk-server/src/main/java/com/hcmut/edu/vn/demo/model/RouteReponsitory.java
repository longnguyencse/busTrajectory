package com.hcmut.edu.vn.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteReponsitory extends JpaRepository<RouteModel, Integer> {

    List<RouteModel> findAllByRouteIdAndStationDirectionAndRouteType(int routeId, int directionId , int route);
}
