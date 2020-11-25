package com.communication.controller;

import com.communication.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/communication")
public class CommunicationController {
    @Autowired
    CommunicationService communicationService;


    @PostMapping("/getFileSize")
    public void getFileSize(@RequestBody HashMap param){
        communicationService.getFileSize(param);

    }

    @PostMapping("/tets/{a}")
    public void tets(@PathVariable String a){
        System.out.println("Feign调用---" + a);

    }

}
