package com.hcmut.edu.vn.demo.model;

//import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by nqlong on 10-Jan-18.
 */
@Data
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

    @Column(name = "polyline", length = 1000)
    private String polyline;

    @Column(name = "distance")
    private int distance;

    @Column(name = "route_type")
    private int routeType;

}
