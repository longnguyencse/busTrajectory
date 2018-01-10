package com.hcmut.edu.vn.demo.api;

import com.hcmut.edu.vn.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by nqlong on 08-Jan-18.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    Service service;

    @GetMapping("api/hello")
    public String greet() {
        return "Hello from Spring ^.^";
    }

    @GetMapping("api/route/{routeId}/{directionId}")
    public ResponseEntity getRouteByIdDirection(@PathVariable("routeId") int routeId,
                                                @PathVariable("directionId") int directionId) {

        return new ResponseEntity(service.findRouteByIdDirection(routeId, directionId), HttpStatus.OK);
    }

    @GetMapping("api/journey/{journeyCode}")
    public ResponseEntity getJourneyByCode(@PathVariable("journeyCode") String journeyCode) {
        System.out.println(journeyCode);
        return new ResponseEntity(service.findJourneyByJourneyCode(journeyCode), HttpStatus.OK);
    }

}
