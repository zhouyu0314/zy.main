package com.fileManager.controller;

import com.common.dto.Dto;
import com.common.dto.DtoUtil;
import com.fileManager.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/file")
public class FileManagerController {
    @Autowired
    private FileManagerService fileManagerService;

    @GetMapping("/showAllFile")
    public Dto showAllFile(){
        List<Map<String, Object>> files = fileManagerService.showAllFile();
        return DtoUtil.returnDataSuccess(files);
    }

    @GetMapping("/showCapacity")
    public Dto showCapacity(){
        Map<String, Object> capacity = fileManagerService.showCapacity();
        return DtoUtil.returnDataSuccess(capacity);
    }
}
