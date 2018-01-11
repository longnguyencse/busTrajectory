package com.hcmut.edu.vn.demo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by nqlong on 10-Jan-18.
 */
//@Data
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJourneyCode() {
        return journeyCode;
    }

    public void setJourneyCode(String journeyCode) {
        this.journeyCode = journeyCode;
    }

    public int getJourneyType() {
        return journeyType;
    }

    public void setJourneyType(int journeyType) {
        this.journeyType = journeyType;
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

    public double getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(double receivingTime) {
        this.receivingTime = receivingTime;
    }
}
