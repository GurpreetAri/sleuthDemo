package com.demo.sleuth.sleuth;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SleuthController {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SleuthService sleuthService;


    @GetMapping("/")
    public String root() {

        LOGGER.debug("DEBUG - root was called");
        LOGGER.info("INFO - root was called");
        LOGGER.warn("WARN - root was called");
        LOGGER.trace("TRACE - root was called");
        LOGGER.error("ERROR - root was called");

//
//        System.setProperty("javax.net.ssl.trustStore", "<path to cert>");
//        System.setProperty("javax.net.ssl.trustStorePassword", "<password>");
//        System.setProperty("javax.net.ssl.trustStoreType", "JKS");

        return "Rooooooot";
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {

        LOGGER.info("********" + request != null ? request.getHeaderNames().toString() : null);

        LOGGER.debug("DEBUG - Hello was called");
        LOGGER.info("INFO - Hello was called");
        LOGGER.warn("WARN - Hello was called");
        LOGGER.trace("TRACE - Hello was called");
        LOGGER.error("ERROR - Hello was called");

        return "Helloooooooo";
    }

    @GetMapping("/rest")
    public String makeRestCall() {

        LOGGER.debug("DEBUG - makeRestCall was called");
        LOGGER.info("INFO - makeRestCall was called");
        LOGGER.warn("WARN - makeRestCall was called");
        LOGGER.trace("TRACE - makeRestCall was called");
        LOGGER.error("ERROR - makeRestCall was called");

        return restTemplate.getForObject("http://localhost:8080/hello", String.class);
    }

    @GetMapping("/newSpan")
    public String demoNewSpan() {

        LOGGER.debug("DEBUG - demoNewSpan was called");
        LOGGER.info("INFO - demoNewSpan was called");
        LOGGER.warn("WARN - demoNewSpan was called");
        LOGGER.trace("TRACE - demoNewSpan was called");
        LOGGER.error("ERROR - demoNewSpan was called");

        return sleuthService.newSpan();
    }

    @GetMapping("/sameSpan")
    public String demoSameSpan() {

        LOGGER.debug("DEBUG - demoSameSpan was called");
        LOGGER.info("INFO - demoSameSpan was called");
        LOGGER.warn("WARN - demoSameSpan was called");
        LOGGER.trace("TRACE - demoSameSpan was called");
        LOGGER.error("ERROR - demoSameSpan was called");

        return sleuthService.existingSpan();
    }

//    @GetMapping("/callps")
//    public String callPsV2() {
//
//        LOGGER.debug("DEBUG - callPsV2 was called");
//        LOGGER.info("INFO - callPsV2 was called");
//        LOGGER.warn("WARN - callPsV2 was called");
//        LOGGER.trace("TRACE - callPsV2 was called");
//        LOGGER.error("ERROR - callPsV2 was called");
//
//        String response =  restTemplate.getForObject("http://localhost:8090/healthcheck/up", String.class);
//
//        LOGGER.info("RECEIVED RESPONSE FROM PSV2 ***********");
//        return response;
//    }
}
