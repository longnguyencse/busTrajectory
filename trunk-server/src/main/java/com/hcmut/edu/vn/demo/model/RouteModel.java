package com.hcmut.edu.vn.demo.model;

//import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by nqlong on 10-Jan-18.
 */
//@Data
@Entity(name = "route")
public class RouteModel {

    @Id
    @Column(name = "ac_route_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*@JsonProperty("id")*/
    private int id;

    @Column(name = "route_id")
    private int routeId;

    @Column(name = "station_id")
    private String stationId;

    @Column(name = "station_code")
    private String stationCode;

    @Column(name = "station_direction")
    private int stationDirection;

    @Column(name = "station_order")
    private String stationOrder;

    @Column(name = "station_address")
    private String stationAddress;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    @Column(name = "polyline", length = 5000)
    private String polyline;

    @Column(name = "distance")
    private double distance;

    @Column(name = "route_type")
    private int routeType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public int getStationDirection() {
        return stationDirection;
    }

    public void setStationDirection(int stationDirection) {
        this.stationDirection = stationDirection;
    }

    public String getStationOrder() {
        return stationOrder;
    }

    public void setStationOrder(String stationOrder) {
        this.stationOrder = stationOrder;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getRouteType() {
        return routeType;
    }

    public void setRouteType(int routeType) {
        this.routeType = routeType;
    }
}
