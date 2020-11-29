package com.zy.controller;

import com.zy.dto.Dto;
import com.zy.dto.DtoUtil;
import com.zy.service.FileManagerService;
import com.zy.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
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

    @PostMapping("/downLoad")
    public Dto downLoad(String fileName,HttpServletRequest request, HttpServletResponse response){
        try{
            fileManagerService.downLoad(fileName,request,response);
            return DtoUtil.returnSuccess("开始下载");
        }catch (Exception e){
            return DtoUtil.returnSuccess("下载失败");
        }
    }




    @PostMapping("/showFileInfo")
    public Dto showFileInfo(@RequestBody HashMap param) throws IOException {
        Path path = Paths.get("D:/资料大全1.1.2.doc");
        byte[] bytes = FileUtil.fileToBytesByNio(path);
        String data = Base64Utils.encodeToString(bytes);
        return DtoUtil.returnSuccess(data);
    }



}
