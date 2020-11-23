package com.fileManager.controller;

import com.common.dto.Dto;
import com.common.dto.DtoUtil;
import com.fileManager.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/file")
public class FileManagerController {
    @Autowired
    private FileManagerService fileManagerService;

    /**
     * 显示所有文件
     * @return
     */
    @PostMapping("/showAllFile")
    public Dto showAllFile(@RequestBody HashMap param){
        List<Map<String, Object>> files = fileManagerService.showAllFile(param);
        return DtoUtil.returnDataSuccess(files);
    }

    /**
     * 显示系统容量信息
     * @return
     */
    @PostMapping("/showCapacity")
    public Dto showCapacity(@RequestBody HashMap param){
        Map<String, Object> capacity = fileManagerService.showCapacity(param);
        return DtoUtil.returnDataSuccess(capacity);
    }

    @PostMapping("/showFileInfo")
    public Dto showFileInfo(@RequestBody HashMap param){
        Map<String, Object> fileInfo = fileManagerService.showFileInfo(param);
        return DtoUtil.returnDataSuccess(fileInfo);

    }
}
