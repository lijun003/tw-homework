package com.ifox.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ifox.service.SnapshotService;

@RestController
@EnableAutoConfiguration
public class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;

    @RequestMapping(value = "/snapshot", method = POST)
    public ResponseEntity<?> getSnapshot(@RequestBody com.ifox.domain.RequestBody requestBody) {

        return new ResponseEntity<>(snapshotService.getSnapshot(requestBody.getDatas(), requestBody.getId())
                , HttpStatus.OK);
    }
}
