package com.hcmut.edu.vn.demo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by nqlong on 10-Jan-18.
 */
@Data
@Entity(name = "journey")
public class JourneryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ac_journey_id")
    private int id;

    @Column(name = "journey_code")
    private String journeyCode;

    @Column(name = "journey_type")
    private int journeyType;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    @Column(name = "receiving_time")
    private double receivingTime;

}
