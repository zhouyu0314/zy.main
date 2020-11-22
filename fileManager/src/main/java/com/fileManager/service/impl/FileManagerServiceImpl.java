package com.fileManager.service.impl;

import com.fileManager.service.FileManagerService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileManagerServiceImpl implements FileManagerService {

    @Override
    public List<Map<String,Object>> showAllFile(HashMap param) {
        List<Map<String,Object>> files = new ArrayList<>();
        Map<String,Object> params = null;
        File parentFile = new File(param.get("path").toString());
        for (File file : parentFile.listFiles()) {
            params = new HashMap<>();
            params.put("name",file.getName());
            params.put("path",file.getAbsolutePath());
            if (file.isDirectory()) {
                params.put("isDirectory",1);
                params.put("size",null);
            }else if(file.isFile()){
                params.put("isDirectory",2);
                params.put("size", FileUtils.sizeOf(file));
            }else{
                params.put("isDirectory",3);
                params.put("size", FileUtils.sizeOf(file));
            }
            files.add(params);
        }


        return files;
    }

    @Override
    public Map<String, Object> showCapacity(HashMap param) {
        DecimalFormat df = new DecimalFormat("0.0000");
        Map<String,Object> params = new HashMap<>();
        File parentFile = new File(param.get("path").toString());
        params.put("allCapacity",parentFile.getTotalSpace());
        params.put("useCapacity",parentFile.getUsableSpace());
        long data = parentFile.getTotalSpace() - parentFile.getUsableSpace();
        String percentage = df.format((float) data/ parentFile.getTotalSpace());
        params.put("percentage",Double.valueOf( percentage)*100);
        return params;

    }
}
