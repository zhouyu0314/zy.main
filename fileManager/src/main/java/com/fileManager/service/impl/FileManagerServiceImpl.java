package com.fileManager.service.impl;

import com.fileManager.config.FileConst;
import com.fileManager.service.FileManagerService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileManagerServiceImpl implements FileManagerService {
    @Autowired
    private FileConst fileConst;

    @Override
    public List<Map<String,Object>> showAllFile() {
        List<Map<String,Object>> files = new ArrayList<>();
        Map<String,Object> param = null;
        File parentFile = new File(fileConst.getPath());
        for (File file : parentFile.listFiles()) {
            param = new HashMap<>();
            param.put("name",file.getName());
            param.put("path",file.getAbsolutePath());
            //param.put("path1",file.getCanonicalPath());
            if (file.isDirectory()) {
                param.put("isDirectory",1);
                param.put("size",null);
            }else if(file.isFile()){
                param.put("isDirectory",2);
                param.put("size", FileUtils.sizeOf(file));
            }else{
                param.put("isDirectory",3);
                param.put("size", FileUtils.sizeOf(file));
            }
            files.add(param);
        }


        return files;
    }

    @Override
    public Map<String, Object> showCapacity() {
        Map<String,Object> param = new HashMap<>();
        File parentFile = new File(fileConst.getPath());
        param.put("allCapacity",parentFile.getTotalSpace());
        param.put("useCapacity",parentFile.getUsableSpace());
        return param;

    }
}
