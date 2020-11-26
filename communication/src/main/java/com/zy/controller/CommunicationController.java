package com.zy.controller;

import com.zy.dto.Dto;
import com.zy.dto.DtoUtil;
import com.zy.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/communication")
public class CommunicationController {
    @Autowired
    CommunicationService communicationService;


    @PostMapping("/getFileSize")
    public void getFileSize(@RequestBody HashMap param){
        communicationService.getFileSize(param);

    }







    @PostMapping("/tets")
    public Dto tets(@RequestBody Map<String,Object> param){
        return DtoUtil.returnSuccess("asd");

    }

    @PostMapping("/tets2")
    public Dto tets2(@RequestBody Map<String,Object> param){
        return DtoUtil.returnSuccess("asd2");

    }

}
