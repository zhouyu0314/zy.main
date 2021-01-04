package com.zy.controller;

import com.zy.dto.Dto;
import com.zy.dto.DtoUtil;
import com.zy.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Dto showFileInfo(@RequestBody HashMap param) {
        Map<String, Object> data = fileManagerService.showFileInfo(param);
                return DtoUtil.returnDataSuccess(data);
    }

    @PostMapping("/getFileOnline")
    public Dto getFileOnline(@RequestBody HashMap param) {
        try {
            String data = fileManagerService.getFileOnline(param);
            return DtoUtil.returnDataSuccess(data);
        } catch (Exception e) {
            return DtoUtil.returnFail(e.getMessage(),"201");
        }
    }

    @GetMapping("/textAxios/{num}")
    public Dto textAxios(@PathVariable String num){
        if ("1".equals(num)) {
            return DtoUtil.returnSuccess("成功");
        }else{
            try {
                throw new Exception("参数错误");
            } catch (Exception e) {
                return DtoUtil.returnFail(e.getMessage(),"0000");
            }

        }
    }


    @PostMapping("/textAxiosPost")
    public Dto textAxiosPost(@RequestBody HashMap params){
        return DtoUtil.returnSuccess();
    }

}
