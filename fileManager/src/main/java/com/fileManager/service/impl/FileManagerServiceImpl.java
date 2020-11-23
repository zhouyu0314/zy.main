package com.fileManager.service.impl;

import com.fileManager.service.FileManagerService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class FileManagerServiceImpl implements FileManagerService {
    private static Map<String, String> typeList = new HashMap<>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        //图片类型格式为2
        typeList.put("jpg", "2");
        typeList.put("jpeg", "2");
        typeList.put("png", "2");
        typeList.put("gif", "2");
        typeList.put("tif", "2");
        typeList.put("bmp", "2");
        //网页文件格式为3
        typeList.put("html", "3");
        //压缩文件格式为4
        typeList.put("zip", "4");
        typeList.put("rar", "4");
        typeList.put("gz", "4");
        typeList.put("rpm", "4");
        typeList.put("iso", "4");
        typeList.put("deb", "4");
        typeList.put("7z", "4");
        //文档格式
        typeList.put("txt", "5");
        typeList.put("sql", "5");
        //office
        typeList.put("wps", "6");
        typeList.put("doc", "6");
        typeList.put("docx", "6");
        //xls
        typeList.put("xls", "7");
        typeList.put("et", "7");
        typeList.put("ett", "7");
        typeList.put("xlsx", "7");
        //ppt
        typeList.put("ppt", "8");
        typeList.put("ppts", "8");
        typeList.put("dps", "8");
        typeList.put("dpt", "8");
        //music
        typeList.put("MPEG", "9");
        typeList.put("MP3", "9");
        typeList.put("MPEG-4", "9");
        typeList.put("MIDI", "9");
        typeList.put("WMA", "9");
        typeList.put("APE", "9");
        typeList.put("FLAC", "9");
        //movie
        typeList.put("AVI", "10");
        typeList.put("mov", "10");
        typeList.put("rmvb", "10");
        typeList.put("rm", "10");
        typeList.put("FLV", "10");
        typeList.put("mp4", "10");
        typeList.put("3GP", "10");
        //执行文件
        typeList.put("exe", "11");
        typeList.put("msi", "11");
        //js
        typeList.put("js", "12");
        //vue
        typeList.put("vue", "13");
        //java
        typeList.put("java", "14");
    }

    @Override
    public List<Map<String, Object>> showAllFile(HashMap param) {
        List<Map<String, Object>> files = new ArrayList<>();
        Map<String, Object> params = null;
        File parentFile = new File(param.get("path").toString());
        for (File file : parentFile.listFiles()) {
            params = new HashMap<>();
            String name = file.getName();
            params.put("name", name);
            params.put("path", file.getAbsolutePath());
            if (file.isDirectory()) {
                params.put("isDirectory", 1);
                //params.put("size",null);
            } else if (file.isFile()) {
                //需要判断是什么类型的文件
                String type = name.substring(name.lastIndexOf(".") + 1);
                if (typeList.get(type) != null) {
                    params.put("isDirectory", typeList.get(type));
                } else {
                    params.put("isDirectory", 100);
                }
                //暂时不获取文件大小，如果太大会超时
                //params.put("size", FileUtils.sizeOf(file));
            }
            files.add(params);
        }


        return files;
    }

    @Override
    public Map<String, Object> showCapacity(HashMap param) {
        DecimalFormat df = new DecimalFormat("0.0000");
        Map<String, Object> params = new HashMap<>();
        File parentFile = new File(param.get("path").toString());
        params.put("allCapacity", parentFile.getTotalSpace());
        params.put("useCapacity", parentFile.getUsableSpace());
        long data = parentFile.getTotalSpace() - parentFile.getUsableSpace();
        String percentage = df.format((float) data / parentFile.getTotalSpace());
        params.put("percentage", Double.valueOf(percentage) * 100);
        return params;

    }

    @Override
    public Map<String, Object> showFileInfo(HashMap param) {
        File file = new File(param.get("path").toString());
        Map<String, Object> params = new HashMap<>();
        params.put("size", FileUtils.sizeOf(file));
        params.put("modifiedTime", sdf.format(file.lastModified()));
        try {
            Path path= Paths.get(param.get("path").toString());
            BasicFileAttributes attr= Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS ).readAttributes();
            params.put("createdTime",sdf.format( attr.creationTime().toMillis()));
        }catch (Exception e){

        }
        return params;
    }
}
