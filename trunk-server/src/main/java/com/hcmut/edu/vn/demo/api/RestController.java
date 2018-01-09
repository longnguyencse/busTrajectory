package com.hcmut.edu.vn.demo.api;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by nqlong on 08-Jan-18.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("api/hello")
    public String greet() {
        return "Hello from Spring ^.^";
    }
}
