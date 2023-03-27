package com.howechen.mudspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@RestController
@RequestMapping("hello")
public class DefaultControler {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultControler.class);
    @GetMapping("/world")
    @ResponseBody
    public String helloWorld() {
        return "Hello";
    }

    @GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(
            @RequestHeader Map<String, String> headers, HttpServletRequest request) {
        headers.forEach((key, value) -> {
            LOG.info(String.format("Header '%s' = %s", key, value));
        });

        LOG.info("{}", request.getHeader("Authorization"));
        LOG.info("{}", request.getHeader("Authorization-Customer"));

        return new ResponseEntity<String>(
                String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

}
